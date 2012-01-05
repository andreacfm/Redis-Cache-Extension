package railo.extension.io.cache.redis;

public class RedisCacheUtils {

    private RedisCacheUtils() {
    }

    public static String formatKey(String key){
        if(key.contains(RedisConnection.NAMESPACE)){
            return key;
        }
        String res = RedisConnection.NAMESPACE + ':' + key;
        return res.toLowerCase();
    }

    public static  String removeNamespace(String key){
        return key.replace(RedisConnection.NAMESPACE + ":", "");

    }

}
