package com.biz.sy.test.wechat.menu;

import com.biz.sy.test.wechat.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MenuMain {

	public static void main(String[] args) {

		ClickButton cbt = new ClickButton();
		cbt.setKey("image");
		cbt.setName("回复图片");
		cbt.setType("click");

		ViewButton vbt = new ViewButton();
		vbt.setUrl("http://sydeceshi.imwork.net/wechat/test");
		vbt.setName("博客");
		vbt.setType("view");

		JSONArray sub_button = new JSONArray();
		sub_button.add(cbt);
		sub_button.add(vbt);

		JSONObject buttonOne = new JSONObject();
		buttonOne.put("name", "菜单");
		buttonOne.put("sub_button", sub_button);

		JSONArray button = new JSONArray();
		button.add(vbt);
		button.add(buttonOne);
		button.add(cbt);

		JSONObject menujson = new JSONObject();
		menujson.put("button", button);
		System.out.println(menujson);
		// 这里为请求接口的 url +号后面的是 token，这里就不做过多对 token 获取的方法解释
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + "RQwsQPz_HqUzpuyVRHCyo4Wdo5ajPRQxNOQykcB2ZpH6MICbMauvSuAM3Fh1Jw1LZGMH4d1ldYtOb8chLwLeiQphsGkn_c2eQOHOEPpCl43swXpvnuPscOWATC3bvkWfJOJfADAZEZ";

		try {
			String rs = HttpUtils.sendPostBuffer(url, menujson.toString());
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("请求错误！");
		}

	}
}
