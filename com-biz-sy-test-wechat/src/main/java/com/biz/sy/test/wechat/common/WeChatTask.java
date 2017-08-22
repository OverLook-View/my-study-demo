package com.biz.sy.test.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.biz.sy.test.wechat.utils.HttpUtils;

import net.sf.json.JSONObject;

/**
 * ClassName: WeChatTask
 * 
 * @Description: 微信两小时定时任务体
 * @author dapengniao
 * @date 2016 年 3 月 10 日 下午 1:42:29
 */
@Component("weChatTask")
public class WeChatTask {

	@Value("${AppId}")
	private String AppId;
	@Value("${AppSecret}")
	private String AppSecret;
	@Value("${tokenUrl}")
	private String tokenUrl;
	@Value("${ticketUrl}")
	private String ticketUrl;

	public static String access_token;
	public static String jsapi_ticket;

	/**
	 * @Description: 任务执行体
	 * @param @throws Exception
	 * @author dapengniao
	 * @date 2016 年 3 月 10 日 下午 2:04:37
	 */
	public void getToken_getTicket() throws Exception {
		Map<String, String> params = new HashMap<String, String>();

		// 获取 token 执行体
		params.put("grant_type", "client_credential");
		params.put("appid", AppId);
		params.put("secret", AppSecret);
		String jstoken = HttpUtils.sendGet(tokenUrl, params);
		String access_token = JSONObject.fromObject(jstoken).getString("access_token"); // 获取到token并赋值保存
		if (access_token != null) {
			this.access_token = access_token;
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "token 为==============================" + access_token);
		} else {
			System.out.println("jstoken=" + jstoken + "\ntokenUrl=" + tokenUrl + "\nparams=" + params);
		}

		// 获取 jsticket 的执行体
		params.clear();
		params.put("access_token", access_token);
		params.put("type", "jsapi");
		String jsticket = HttpUtils.sendGet(ticketUrl, params);
		String jsapi_ticket = JSONObject.fromObject(jsticket).getString("ticket");

		if (access_token != null) {
			this.jsapi_ticket = jsapi_ticket; // 获取到js-SDK的ticket并赋值保存
			System.out.println("jsapi_ticket================================================" + jsapi_ticket);
		} else {
			System.out.println("jsticket=" + jsticket);
		}
	}
}
