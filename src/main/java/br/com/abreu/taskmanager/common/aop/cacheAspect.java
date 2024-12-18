package br.com.abreu.taskmanager.common.aop.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class cacheAspect {

    @Autowired
    private RedisCache redisCache;

    @Pointcut("@annotation(com.cm.common.aop.CacheAnnotation)")
    private void cachePointCut() {
    }

    @Around("cachePointCut()")
    public Object around(ProceedingJoinPoint pt) throws Throwable {
        long beginTime = System.currentTimeMillis();

//        获取方法签名：方法签名包含 注解+controller的整个方法
//        获取该方法上的CacheAnnotation注解对象
        CacheAnnotation cacheAnnotation = getCacheAnnotation(pt);

        Object[] args = pt.getArgs();       //默认第一个参数为id
        String key = cacheAnnotation.key() + "" + args[0];

        //        先去获取缓存，如果能查到就不用调用方法了
        Object cacheObject = redisCache.getCacheObject(key);
        if (Objects.nonNull(cacheObject)) {
            return cacheObject;
        }
//        缓存不存在，再去查调用业务方法
        Object result = pt.proceed();
        redisCache.setCacheObject(key, result, (int) cacheAnnotation.ttl(), cacheAnnotation.timeUnit());
        long time = System.currentTimeMillis() - beginTime;
        log.info(cacheAnnotation+"方法=》Time expended       : {}ms", time);
        return result;
    }


    public CacheAnnotation getCacheAnnotation(ProceedingJoinPoint joinPoint) {
//        获取Signature的实现类，目的接口是接在方法上的，因此获取方法类型的实现类MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();    //获取被增强的方法
        return method.getAnnotation(CacheAnnotation.class);//获取被增强方法的注解对象
    }
}
