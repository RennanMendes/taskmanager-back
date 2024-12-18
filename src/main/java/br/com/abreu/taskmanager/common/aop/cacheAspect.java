package br.com.abreu.taskmanager.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@Aspect
public class cacheAspect {

    @Before("@annotation(br.com.abreu.taskmanager.common.aop.CacheAnnotation)")
    public void logBefore(){
        System.out.println("Funciona!!!!!");
    }

    @Around("@annotation(br.com.abreu.taskmanager.common.aop.CacheAnnotation)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around: " + Arrays.stream(joinPoint.getArgs()).toList());
        Object object = joinPoint.proceed();

        System.out.println("\n Este Objetc \n\n " + object);
        return object;
    }

}
