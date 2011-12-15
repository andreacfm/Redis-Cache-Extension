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

import java.io.IOException;
import java.util.List;

public class RedisCache implements Cache{

    public Functions func = new Functions();
    CFMLEngine engine = CFMLEngineFactory.getInstance();
    Cast caster = engine.getCastUtil();


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
        Jedis conn = RedisConnection.getInstance();
        try {
            return new RedisCacheEntry(new RedisCacheItem(key,conn.get(key.toLowerCase())));
        } catch (Exception e) {
            throw(new IOException("Cache key" + key + "has not been found"));
        }
    }

    public Object getValue(String key) throws IOException {
        return getCacheEntry(key).getValue();
    }

    public CacheEntry getCacheEntry(String key, CacheEntry cacheEntry) {
        try {
            return getCacheEntry(key);
        } catch (Exception e) {
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

    public void put(String key, Object val, Long aLong, Long aLong1) {
        Jedis conn = RedisConnection.getInstance();
        try {
            String value = func.serialize(val);
            conn.set(key.toLowerCase(),value);
        } catch (PageException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String key) {
        Jedis conn = RedisConnection.getInstance();
        return conn.exists(key.toLowerCase());
    }

    public boolean remove(String key) {
        System.out.print("key");

        Jedis conn = RedisConnection.getInstance();

        try {
            conn.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int remove(CacheKeyFilter cacheKeyFilter) {
        System.out.print("one");
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int remove(CacheEntryFilter cacheEntryFilter) {
        System.out.print("two");
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List keys() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List keys(CacheKeyFilter cacheKeyFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List entries(CacheKeyFilter cacheKeyFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
}
