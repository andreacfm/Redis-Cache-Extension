package railo.extension.io.cache.redis;

import railo.loader.engine.CFMLEngine;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.type.Struct;
import railo.runtime.util.Cast;
import railo.runtime.exp.PageException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnection {

    private static JedisPool instance;
    public static String NAMESPACE;

    private RedisConnection() {}

    public static JedisPool init(Struct arguments){

        CFMLEngine engine = CFMLEngineFactory.getInstance();
        Cast caster = engine.getCastUtil();

        if(instance != null){
            return instance;
        }

        try{
            NAMESPACE = caster.toString(arguments.get("namespace"));

            String hosts = caster.toString(arguments.get("hosts"));
            String host = hosts.split(":")[0];

            Integer port = caster.toInteger(hosts.split(":")[1]);

            JedisPoolConfig config = new JedisPoolConfig();
            config.setTestOnBorrow(true);

            instance = new JedisPool(config, host, port);

        } catch (PageException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public static JedisPool getInstance(){
        return instance;
    }


}
