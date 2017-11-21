package com.sy.study.demo.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * @author : sy
 * @date: 创建时间：2017年11月20日 下午2:47:17 
 * @version: 1.0   
 * @Description:   
 */

public class ProxyTest {

	public static void main(String[] args) throws Exception {
		Class c = Proxy.getProxyClass(ProxyTest.class.getClassLoader(), ClassLoaderDemo.class.getInterfaces());
		System.out.println(c);
		Constructor[] declaredConstructors = c.getDeclaredConstructors();
		for (Constructor constructor : declaredConstructors) {
			System.out.println(constructor);
		}
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
		Constructor constructor = c.getConstructor(InvocationHandler.class);
		constructor.newInstance(new MyInvocationHandler());
	}
}
