package railo.extension.io.cache.redis;

import railo.commons.io.cache.Cache;
import railo.commons.io.cache.CacheEntry;
import railo.commons.io.cache.CacheEntryFilter;
import railo.commons.io.cache.CacheKeyFilter;
import railo.extension.util.Functions;
import railo.loader.engine.CFMLEngine;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.config.Config;
import railo.runtime.exp.PageException;
import railo.runtime.type.Struct;
import railo.runtime.util.Cast;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RedisCache implements Cache {

    public Functions func = new Functions();
    CFMLEngine engine = CFMLEngineFactory.getInstance();
    Cast caster = engine.getCastUtil();
    String namespace;


    public void init(String cacheName, Struct arguments) throws IOException {
        RedisConnection.init(arguments);
    }

    public void init(Config config, String[] cacheName, Struct[] arguments) {
        //Not used at the moment
    }

    public void init(Config config, String cacheName, Struct arguments) {
        try {
            init(cacheName, arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public CacheEntry getCacheEntry(String key) throws IOException {

        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        RedisCacheItem item;

        try {
            String k = RedisCacheUtils.formatKey(key);
            List<String> val = conn.hmget(k, "value", "hitCount");
            if (val.get(0) == null) {
                throw (new IOException("Cache key [" + k + "] does not exists"));
            }
            Integer count = caster.toInteger(conn.hincrBy(k, "hitCount", 1));
            item = new RedisCacheItem(k, val.get(0), count);

            return new RedisCacheEntry(item);

        }catch (PageException e){
            e.printStackTrace();
            return null;
        } finally {
            pool.returnResource(conn);
        }
    }

    public Object getValue(String key) throws IOException {
        try {
            return getCacheEntry(key).getValue();
        } catch (IOException e) {
            return null;
        }
    }

    public CacheEntry getCacheEntry(String key, CacheEntry cacheEntry) {
        try {
            return getCacheEntry(key);
        } catch (IOException e) {
            return cacheEntry;
        }
    }

    public Object getValue(String key, Object o) {
        try {
            return getValue(key);
        } catch (Exception e) {
            return o;
        }
    }

    public void put(String key, Object val, Long idle, Long expire) {
        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        try {
            Integer exp = 0;
            if (expire != null) {
                exp = caster.toInteger(expire / 1000);
            }

            String k = RedisCacheUtils.formatKey(key);
            String value = func.serialize(val);

            HashMap<String, String> fields = new HashMap<String, String>();
            fields.put("value", value);
            fields.put("hitCount", "0");
            conn.hmset(k, fields);

            if (exp > 0) {
                conn.expire(k, exp);
            }
        } catch (PageException e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(conn);
        }
    }

    public boolean contains(String key) {
        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        try {
            return conn.exists(RedisCacheUtils.formatKey(key));
        } finally {
            pool.returnResource(conn);
        }
    }

    public boolean remove(String key) {
        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        try {
            Long res = conn.del(RedisCacheUtils.formatKey(key));
            return res == 1;
        } finally {
            pool.returnResource(conn);
        }
    }

    public int remove(CacheKeyFilter cacheKeyFilter) {
        int removed = 0;
        List keys = keys(cacheKeyFilter);
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            boolean res = remove(it.next());
            if (res) {
                removed++;
            }
        }
        return removed;
    }

    public int remove(CacheEntryFilter cacheEntryFilter) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List keys() {
        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        try {
            ArrayList res = new ArrayList(conn.keys(RedisConnection.NAMESPACE + '*'));
            return sanitizeKeys(res);
        } finally {
            pool.returnResource(conn);
        }
    }

    public List keys(CacheKeyFilter cacheKeyFilter) {
        List keys = keys();
        ArrayList res = new ArrayList();
        Iterator<String> it = keys.iterator();
        CacheKeyFilter filter = null;

        while (it.hasNext()) {
            String key = it.next();
            if (cacheKeyFilter.accept(key)) {
                res.add(key);
            }
        }

        return res;
    }

    public List keys(CacheEntryFilter cacheEntryFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List values() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List values(CacheKeyFilter cacheKeyFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List values(CacheEntryFilter cacheEntryFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List entries() {
        return entriesList(keys());
    }

    public List entries(CacheKeyFilter cacheKeyFilter) {
        return entriesList(keys(cacheKeyFilter));
    }

    public List entries(CacheEntryFilter cacheEntryFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long hitCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long missCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Struct getCustomInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private List entriesList(List keys) {
        JedisPool pool = RedisConnection.getInstance();
        Jedis conn = pool.getResource();
        try {
            ArrayList<RedisCacheEntry> res = new ArrayList<RedisCacheEntry>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String k = it.next();
                res.add(new RedisCacheEntry(new RedisCacheItem(k, conn.get(k))));
            }

            return res;

        } finally {
            pool.returnResource(conn);
        }
    }

    private List sanitizeKeys(List keys) {
        for (int i = 0; i < keys.size(); i++) {
            try {
                keys.set(i, RedisCacheUtils.removeNamespace(caster.toString(keys.get(i))));
            } catch (PageException e) {
                e.printStackTrace();
            }
        }
        return keys;
    }

}
