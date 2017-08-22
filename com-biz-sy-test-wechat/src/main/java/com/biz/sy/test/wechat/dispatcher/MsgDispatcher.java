package com.biz.sy.test.wechat.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.biz.sy.test.wechat.common.GetUseInfo;
import com.biz.sy.test.wechat.entity.resp.Article;
import com.biz.sy.test.wechat.entity.resp.Image;
import com.biz.sy.test.wechat.entity.resp.ImageMessage;
import com.biz.sy.test.wechat.entity.resp.NewsMessage;
import com.biz.sy.test.wechat.utils.HttpPostUploadUtil;
import com.biz.sy.test.wechat.utils.MessageUtil;

/**
 * ClassName: MsgDispatcher
 * 
 * @Description: 消息业务处理分发器
 * @author dapengniao
 * @date 2016 年 3 月 7 日 下午 4:04:21
 */
public class MsgDispatcher {

	public static String processMessage(Map<String, String> map) throws Exception {

		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			System.out.println("==============这是文本消息！");
			// 普通文本消息
			// TextMessage txtmsg = new TextMessage();
			// txtmsg.setToUserName(openid);
			// txtmsg.setFromUserName(mpid);
			// txtmsg.setCreateTime(new Date().getTime());
			// txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			//
			// String msg = "你好，这里是sy个人账号！";
			// if (map.get("Content").indexOf("1") != -1) {
			// msg = "包含数字1！";
			// } else {
			//
			// }
			// txtmsg.setContent(msg);
			// return MessageUtil.textMessageToXml(txtmsg);
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

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			System.out.println("==============这是图片消息！");
			// 对图文消息
			NewsMessage newmsg = new NewsMessage();
			newmsg.setToUserName(openid);
			newmsg.setFromUserName(mpid);
			newmsg.setCreateTime(new Date().getTime());
			newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

			Map<String, String> userinfo = GetUseInfo.Openid_userinfo(openid);

			Article article = new Article();
			article.setDescription("这是图文消息 1"); // 图文消息的描述
			article.setPicUrl("http://sydeceshi.imwork.net/img/user-icon.jpg"); // 图文消息图片地址
			article.setTitle("图文消息 1," + userinfo.get("nickname") + "同志你好"); // 图文消息标题
			article.setUrl("http://sydeceshi.imwork.net"); // 图文 url 链接
			Article article1 = new Article();
			article.setDescription("这是图文消息 1"); // 图文消息的描述
			article.setPicUrl("http://sydeceshi.imwork.net/img/user-icon.jpg"); // 图文消息图片地址
			article.setTitle("图文消息 1," + userinfo.get("nickname") + "同志你好"); // 图文消息标题
			article.setUrl("http://www.jd.com"); // 图文 url 链接
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
			list.add(article1);
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			System.out.println("==============这是链接消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
			System.out.println("==============这是视频消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！");
		}

		return "";
	}
}
