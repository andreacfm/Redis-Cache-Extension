package railo.extension.io.cache.redis;

import redis.clients.jedis.Jedis;

public class RedisConnection {

    private Jedis instance;

    private RedisConnection() {}

    public Jedis init(){

        if(instance != null){
            return instance;
        }

        instance = new Jedis("host", 1111);

        return instance;
    }

    public Jedis getInstance(){
        return instance;
    }


}
