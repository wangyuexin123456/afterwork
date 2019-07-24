package com.baizhi.cache;

import com.baizhi.util.SerializeUtils;
import com.baizhi.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MyCache implements Cache {
    private String id;

    public MyCache(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("向缓存加值");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.opsForHash().put(id,SerializeUtils.serialize(key),SerializeUtils.serialize(value));
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("进缓存取值");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        String s= (String) stringRedisTemplate.opsForHash().get(id,SerializeUtils.serialize(key));
        if(s==null){
            System.out.println("没取到值");
            return null;
        }else{
            System.out.println("取到了值");
            Object object = SerializeUtils.serializeToObject(s);
            return object;
        }
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清除缓存");
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.opsForHash().delete(id);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
