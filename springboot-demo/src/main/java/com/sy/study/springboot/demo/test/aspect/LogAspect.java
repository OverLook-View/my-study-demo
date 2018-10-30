package com.sy.study.springboot.demo.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-10-30 10:56
 **/
@Aspect
@Component
public class LogAspect {

    private Logger logger=LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.sy.study.springboot.demo.test.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("before log-----");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        logger.info("url={}", request.getRequestURI());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //method
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //param
        logger.info("args={}", joinPoint.getArgs());
    }

//    @After("execution(* com.sy.study.springboot.demo.test.controller.*.*(..))")
//    @Pointcut("@annotation(com.sy.study.springboot.demo.test.aspect.Log)")
    @After("@annotation(com.sy.study.springboot.demo.test.aspect.Log)")
    public void after(JoinPoint joinPoint) {
        System.out.println("@Log after log-----");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //请求的方法名
        String methodName = signature.getName();
        //获取方法中的注解
        Log log = method.getAnnotation(Log.class);
        // 请求的参数
        Object[] args = joinPoint.getArgs();

        System.out.println(log);
    }

    @Around("execution(* com.sy.study.springboot.demo.test.controller.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around log-----");
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
    @AfterReturning("execution(* com.sy.study.springboot.demo.test.controller.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("afterReturning log-----");
    }
}
