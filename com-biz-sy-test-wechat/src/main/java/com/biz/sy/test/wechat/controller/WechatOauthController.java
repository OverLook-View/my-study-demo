package com.biz.sy.test.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.sy.test.wechat.utils.OauthCode_GetUseInfo;

/**
 * @author : sy
 * @date: 创建时间：2017年7月11日 上午10:33:08
 * @version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("weixinOauth")
public class WechatOauthController {

	@Autowired
	private OauthCode_GetUseInfo oauthCode_GetUseInfo;

	@RequestMapping("")
	public void weixinOauth(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code, @RequestParam(value = "state", required = true) String state) {

		System.out.println("Code=============" + code + "==========state=======" + state);
		try {
			// 用code取得微信用户的基本信息
			//			OauthCode_GetUseInfo weixin = new OauthCode_GetUseInfo(code);
			Map<String, String> wmap = oauthCode_GetUseInfo.getUserInfo(code);
			System.out.println("用户昵称=================================" + wmap.get("nickname"));
			System.out.println(wmap);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
