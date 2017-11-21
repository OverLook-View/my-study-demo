package com.sy.study.demo.classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : sy
 * @date: 创建时间：2017年11月20日 下午3:13:03
 * @version: 1.0
 * @Description:
 */

public class MyInvocationHandler implements InvocationHandler {

	private ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		String methodName = method.getName();
		if (args!=null) {
			String s=(String)args[0];
			if (!"OK".equals(s)) {
				System.out.println("no,you are faker");
				return null;
			}
		}
		Object res = method.invoke(methodName, args);
		return res;
	}

}
