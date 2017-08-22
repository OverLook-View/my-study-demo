package com.biz.sy.test.wechat.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sy.test.wechat.common.JSSDK_Config;

/**
 * ClassName: WeChatController
 * 
 * @Description: 前端用户微信配置获取
 * @author dapengniao
 * @date 2016 年 3 月 19 日 下午 5:57:36
 */
@Controller
@RequestMapping("/wechatconfig")
public class WeChatController {

	/**
	 * @Description: 前端获取微信 JSSDK 的配置参数
	 * @param @param response
	 * @param @param request
	 * @param @param url
	 * @param @throws Exception
	 * @author dapengniao
	 * @date 2016 年 3 月 19 日 下午 5:57:52
	 */
	@RequestMapping("jssdk")
	@ResponseBody
	public Map<String, String> JSSDK_config(@RequestParam(value = "url", required = true) String url) {
		try {
			System.out.println(url);
			Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
			System.out.println(configMap);
			return configMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
