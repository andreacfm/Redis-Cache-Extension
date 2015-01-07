package railo.extension.io.cache.redis;

public class RedisCacheItem{

    Integer hitCount;
    String key;
    String value;
    
    private String cacheName;

    public RedisCacheItem(String key, String value, String cacheName) {
        setKey(key);
        setValue(value);
        setHitCount(0);
        
        this.cacheName = cacheName;
    }

    public RedisCacheItem(String key, String value, Integer hitCount, String cacheName) {
        setKey(key);
        setValue(value);
        setHitCount(hitCount);
        
        this.cacheName = cacheName;
    }


    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    public String getCacheName() {
    	return cacheName;
    }
}
