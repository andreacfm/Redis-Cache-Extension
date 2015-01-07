package railo.extension.io.cache.redis;

public class RedisCacheUtils {

    private RedisCacheUtils() {
    }

    public static String formatKey(String cacheName, String key){
        if(key.contains(RedisConnection.getNamespace(cacheName))){
            return key;
        }
        String res = RedisConnection.getNamespace(cacheName) + ':' + key;
        return res.toLowerCase();
    }

    public static  String removeNamespace(String cacheName, String key){
        return key.replace(RedisConnection.getNamespace(cacheName) + ":", "");

    }

}
