package com.demo.proxy;

import com.demo.proxy.cglib.AdminServiceCglibProxy;
import com.demo.proxy.jdk.AdminServiceDynamicProxy;
import com.demo.proxy.jdk.AdminServiceIncovation;
import com.demo.proxy.static1.AdminServiceProxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        AdminService adminService = new AdminServiceImpl();
        AdminServiceProxy proxy = new AdminServiceProxy(adminService);
        proxy.update();
        proxy.find();
    }

    public static void test2() {
        //方法一
        System.out.println("====方法一======");
        AdminService adminService = new AdminServiceImpl();
        System.out.println("代理的目标对象：" + adminService.getClass());

        AdminServiceIncovation adminServiceIncovation = new AdminServiceIncovation(adminService);
        AdminService proxy = (AdminService) new AdminServiceDynamicProxy(adminService, adminServiceIncovation).getPersonProxy();

        System.out.println("代理对象：" + proxy.getClass());
        Object obj = proxy.find();
        System.out.println("find返回对象：" + obj.getClass());
        proxy.update();
        System.out.println("----------------");

        System.out.println("====方法二======");
        AdminService target = new AdminServiceImpl();
        AdminServiceIncovation incovation = new AdminServiceIncovation(target);
        AdminService proxy2 = (AdminService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), incovation);

        Object obj2 = proxy2.find();
        System.out.println("find返回对象：" + obj2.getClass());
        proxy.update();
        System.out.println("----------------");

        System.out.println("====方法三======");
        AdminService target3 = new AdminServiceImpl();
        AdminService proxy3 = (AdminService) Proxy.newProxyInstance(target3.getClass().getClassLoader(), target3.getClass().getInterfaces(), (proxy1, method, args) -> {
            System.out.println("判断权限");
            Object invoke = method.invoke(target3, args);
            System.out.println("记录操作日志");
            return invoke;
        });


        Object obj3 = target3.find();
        System.out.println("find返回对象：" + obj3.getClass());
        proxy.update();
        System.out.println("----------------");
    }

    public static void test3() {
        AdminServiceImpl target = new AdminServiceImpl();
        AdminServiceCglibProxy proxyFactory = new AdminServiceCglibProxy(target);
        AdminServiceImpl proxy = (AdminServiceImpl) proxyFactory.getProxyInstance();

        System.out.println("代理对象：" + proxy.getClass());
        Object o = proxy.find();
        System.out.println("find返回对象：" + o.getClass());
        proxy.update();
        System.out.println("-----------------");
    }
}
