package com.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AdminServiceIncovation implements InvocationHandler {
    private Object target;

    public AdminServiceIncovation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断权限");
        Object obj = method.invoke(target);
        System.out.println("记录操作日志");
        return obj;
    }
}
