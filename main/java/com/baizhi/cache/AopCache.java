package com.baizhi.cache;

import com.baizhi.util.SerializeUtils;
import com.baizhi.util.SpringContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/*@Configuration
@Aspect*/
public class AopCache {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Around("execution(* com.baizhi.service.impl.*.query*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("进入缓存");
        String id = proceedingJoinPoint.getTarget().getClass().getName();
        String method = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        System.out.println(id+"+++");
        System.out.println(method+"+++");
        System.out.println(builder+"+++");
        for (Object arg : args) {
            builder.append(arg);
        }
        String key = builder.toString();
        String s  = (String) stringRedisTemplate.opsForHash().get(id, key);
        if(s==null){
            System.out.println("没值添加");
            Object proceed = proceedingJoinPoint.proceed();
            stringRedisTemplate.opsForHash().put(id,key, SerializeUtils.serialize(proceed));
            return proceed;
        }else{
            System.out.println("有值返回");
            return SerializeUtils.serializeToObject(s);
        }
    }
    @AfterReturning("execution(* com.baizhi.service.impl.*.*(..))&& !execution(* com.baizhi.service.impl.*.query*(..))")
    public void after(JoinPoint joinPoint){
        System.out.println("删除缓存");
        String id=joinPoint.getTarget().getClass().getName();
        stringRedisTemplate.delete(id);
    }
}
