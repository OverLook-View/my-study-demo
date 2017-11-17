package com.sy.study.demo.classloader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author : sy
 * @date: 创建时间：2017年11月16日 下午4:43:14
 * @version: 1.0
 * @Description:
 */

public class ClassLoaderDemo {

	public static void main(String[] args) throws Exception {
		ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
		System.out.println(classLoader);
		ClassLoader parent = classLoader.getParent();
		System.out.println(parent);
		ClassLoader parent2 = parent.getParent();
		System.out.println(parent2);
		getProperty();
		getClassInfo();
		getPrimitiveClass();
		getMethod();
	}

	public static void getProperty() throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = ClassLoaderDemo.class.getResourceAsStream("/test.cfg");
		properties.load(inputStream);
		System.out.println(properties);
	}

	public static void getClassInfo() throws Exception {
		Class cla = String.class;
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getName());
		cla = "123".getClass();
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getName());
		cla = Class.forName("java.lang.String");
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getName());
	}

	public static void getPrimitiveClass() {
		Class cla = int.class;
		System.out.println(cla);
		System.out.println(cla.isPrimitive());
		System.out.println(Integer.class == cla);
		System.out.println(Integer.TYPE == cla);
	}

	public static void getMethod() {
		Class cla = ClassLoaderDemo.class;
		Constructor[] constructors = cla.getConstructors();
		for (Constructor constructor : constructors) {
			System.out.println(constructor);
		}
		Method[] methods = cla.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}
}
