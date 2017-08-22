package com.biz.sy.test.wechat.utils;

import java.io.Writer;

import com.biz.sy.test.wechat.entity.resp.Article;
import com.biz.sy.test.wechat.entity.resp.ImageMessage;
import com.biz.sy.test.wechat.entity.resp.MusicMessage;
import com.biz.sy.test.wechat.entity.resp.NewsMessage;
import com.biz.sy.test.wechat.entity.resp.TextMessage;
import com.biz.sy.test.wechat.entity.resp.VideoMessage;
import com.biz.sy.test.wechat.entity.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtil {
	
	@SuppressWarnings("unused")
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有 xml 节点的转换都增加 CDATA 标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * @Description: 文本消息对象转换成 xml
	 * @param @param textMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 8 日 下午 4:13:22
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * @Description: 图文消息对象转换成 xml
	 * @param @param newsMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 8 日 下午 4:14:09
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * @Description: 图片消息对象转换成 xml
	 * @param @param imageMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 9 日 上午 9:25:51
	 */
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * @Description: 语音消息对象转换成 xml
	 * @param @param voiceMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 9 日 上午 9:27:26
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 * @Description: 视频消息对象转换成 xml
	 * @param @param videoMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 9 日 上午 9:31:09
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 * @Description: 音乐消息对象转换成 xml
	 * @param @param musicMessage
	 * @param @return
	 * @author dapengniao
	 * @date 2016 年 3 月 8 日 下午 4:13:36
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
}
