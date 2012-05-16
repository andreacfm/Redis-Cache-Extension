package railo.extension.io.cache.redis;

import railo.commons.io.cache.CacheEntry;
import railo.extension.util.Functions;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.exp.PageException;
import railo.runtime.type.Struct;

import java.util.Date;
import java.util.HashMap;

public class RedisCacheEntry implements CacheEntry{

    RedisCacheItem item;
    Functions func = new Functions();
    
    public RedisCacheEntry(RedisCacheItem redisCacheItem) {
        item = redisCacheItem;
    }

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
        return item.getHitCount();
    }

    public String getKey() {
        return RedisCacheUtils.removeNamespace(item.getKey());
    }

    public Object getValue() {
        try {
            String val = item.getValue();
            return func.evaluate(item.getValue());
        } catch (PageException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
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
        Struct metadata = CFMLEngineFactory.getInstance().getCreationUtil().createStruct();
        try{
            metadata.set("hits", hitCount());
        }catch (PageException e){
            e.printStackTrace();
        }
        return metadata;
    }

}
