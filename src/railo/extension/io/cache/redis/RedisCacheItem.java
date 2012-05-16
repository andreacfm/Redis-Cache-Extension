package railo.extension.io.cache.redis;

public class RedisCacheItem{

    Integer hitCount;
    String key;
    String value;

    public RedisCacheItem(String key, String value) {
        setKey(key);
        setValue(value);
        setHitCount(0);
    }

    public RedisCacheItem(String key, String value, Integer hitCount) {
        setKey(key);
        setValue(value);
        setHitCount(hitCount);
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
}
