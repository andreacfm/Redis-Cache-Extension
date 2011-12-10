package railo.extension.io.cache.redis;

import railo.commons.io.cache.CacheEntry;
import railo.runtime.type.Struct;

import java.util.Date;

public class RedisCacheEntry implements CacheEntry{
    public Date lastHit() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date lastModified() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date created() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hitCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getKey() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long liveTimeSpan() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long idleTimeSpan() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Struct getCustomInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
