package railo.extension.io.cache.redis;

import java.util.Hashtable;

import railo.loader.engine.CFMLEngine;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.type.Struct;
import railo.runtime.util.Cast;
import railo.runtime.exp.PageException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnection {

    private static final Hashtable<String, JedisPool> instance = new Hashtable<String, JedisPool>();
    private static final Hashtable<String, String> namespace = new Hashtable<String, String>();

    private RedisConnection() {}

    public static JedisPool init(String cacheName, Struct arguments){

        CFMLEngine engine = CFMLEngineFactory.getInstance();
        Cast caster = engine.getCastUtil();

        if(instance != null && instance.contains(cacheName)){
            return getInstance(cacheName);
        }

        try{
        	namespace.put(cacheName, caster.toString(arguments.get("namespace")));

            String hosts = caster.toString(arguments.get("hosts"));
            String host = hosts.split(":")[0];

            Integer port = caster.toInteger(hosts.split(":")[1]);

            JedisPoolConfig config = new JedisPoolConfig();
            config.setTestOnBorrow(true);
            
            instance.put(cacheName, new JedisPool(config, host, port));

        } catch (PageException e) {
            e.printStackTrace();
        }

        return instance.get(cacheName);
    }

    public static JedisPool getInstance(String cacheName){
        return instance.get(cacheName);
    }
    
    public static String getNamespace(String cacheName){
    	return namespace.get(cacheName);
    }

}
