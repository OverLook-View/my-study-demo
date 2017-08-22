package com.biz.sy.test.wechat.dispatcher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.biz.sy.test.wechat.entity.resp.Image;
import com.biz.sy.test.wechat.entity.resp.ImageMessage;
import com.biz.sy.test.wechat.utils.HttpPostUploadUtil;
import com.biz.sy.test.wechat.utils.MessageUtil;

/**
 * ClassName: EventDispatcher
 * 
 * @Description: 事件消息业务分发器
 * @author dapengniao
 * @date 2016 年 3 月 7 日 下午 4:04:41
 */
public class EventDispatcher {

	public static String processEvent(Map<String, String> map) {

		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			System.out.println("==============这是关注事件！");

			ImageMessage imgmsg = new ImageMessage();
			imgmsg.setToUserName(openid);
			imgmsg.setFromUserName(mpid);
			imgmsg.setCreateTime(new Date().getTime());
			imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);

			Image img = new Image();
			HttpPostUploadUtil util = new HttpPostUploadUtil();
			String filepath = "D:\\user-icon.jpg";
			Map<String, String> textMap = new HashMap<String, String>();
			textMap.put("name", "testname");
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("userfile", filepath);
			String mediaidrs = util.formUpload(textMap, fileMap);
			System.out.println(mediaidrs);
			String mediaid = JSONObject.fromObject(mediaidrs).getString("media_id");
			img.setMediaId(mediaid);
			imgmsg.setImage(img);
			return MessageUtil.imageMessageToXml(imgmsg);
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			System.out.println("==============这是取消关注事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
			System.out.println("==============这是扫描二维码事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
			System.out.println("==============这是位置上报事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单
																	// View 事件
			System.out.println("==============这是自定义菜单 View 事件！");
		}

		return "";
	}
}
