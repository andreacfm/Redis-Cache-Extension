package railo.extension.io.cache.redis;

import railo.commons.io.cache.Cache;
import railo.commons.io.cache.CacheEntry;
import railo.commons.io.cache.CacheEntryFilter;
import railo.commons.io.cache.CacheKeyFilter;
import railo.runtime.type.Struct;

import java.io.IOException;
import java.util.List;

public class RedisCache implements Cache{

    public void init(String s, Struct struct) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public CacheEntry getCacheEntry(String s) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getValue(String s) throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CacheEntry getCacheEntry(String s, CacheEntry cacheEntry) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getValue(String s, Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void put(String s, Object o, Long aLong, Long aLong1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean contains(String s) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean remove(String s) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int remove(CacheKeyFilter cacheKeyFilter) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int remove(CacheEntryFilter cacheEntryFilter) {
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
