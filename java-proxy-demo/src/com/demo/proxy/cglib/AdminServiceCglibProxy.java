package com.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AdminServiceCglibProxy implements MethodInterceptor {
    private Object target;

    public AdminServiceCglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        //工具类
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("判断权限");
        Object obj = method.invoke(target);
        System.out.println("记录操作日志");
        return obj;
    }
}
