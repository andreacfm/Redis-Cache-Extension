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
import java.util.ArrayList;
import java.util.Iterator;
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
        String val = conn.get(key.toLowerCase());
        if(val == null){
            throw(new IOException("Cache key [" + key +"] does not exists"));
        }
        RedisCacheItem item = new RedisCacheItem(key, val);
        return new RedisCacheEntry(item);
    }

    public Object getValue(String key) throws IOException {
        try{
            CacheEntry entry = getCacheEntry(key);
            return getCacheEntry(key).getValue();
        }catch (IOException e){
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

    public void put(String key, Object val, Long expire, Long idle) {
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
        Jedis conn = RedisConnection.getInstance();
        Long res = conn.del(key.toLowerCase());
        if(res == 1){
            return true;
        }
        return false;
    }

    public int remove(CacheKeyFilter cacheKeyFilter) {
        int removed = 0;
        List keys = keys(cacheKeyFilter);
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            boolean res = remove(it.next());
            if(res){
                removed++;
            }
        }
        return removed;
    }

    public int remove(CacheEntryFilter cacheEntryFilter) {
        System.out.println("entryfilter");
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List keys() {
        Jedis conn = RedisConnection.getInstance();
        ArrayList res = new ArrayList(conn.keys("*"));
        return res;
    }

    public List keys(CacheKeyFilter cacheKeyFilter) {
        List keys = keys();
        ArrayList res = new ArrayList();
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            if(cacheKeyFilter.accept(key)){
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
