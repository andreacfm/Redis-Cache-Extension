package railo.extension.io.cache.redis;

public class RedisCacheItem {

    String value;
    String key;

    public RedisCacheItem(String key,String value) {
        setKey(key);
        setValue(value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
