package com.sy.study.demo.enumtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : sy
 * @date: 创建时间：2017年11月21日 上午10:12:14
 * @version: 1.0
 * @Description:
 */

public enum TestEnum {

	test1("test1", "this is the test1"), test2("test2", "this is the test2");

	/** 枚举值 */
	private String value;

	/** 描述 */
	private String desc;

	private TestEnum(String desc, String value) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static TestEnum getEnum(String value) {
		TestEnum resultEnum = null;
		TestEnum[] enumAry = TestEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue() == value) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		TestEnum[] ary = TestEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", ary[num].getValue());
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		TestEnum[] ary = TestEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", ary[i].toString());
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}

}
