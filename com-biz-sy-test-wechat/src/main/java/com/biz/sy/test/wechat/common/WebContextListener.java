package com.biz.sy.test.wechat.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ClassName: InterfaceUrlIntiServlet
 * 
 * @Description: 项目启动初始化 servlet
 * @author dapengniao
 * @date 2016 年 3 月 10 日 下午 4:08:43
 */
@Component
public class WebContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private WeChatTask weChatTask;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			if (weChatTask.access_token == null) {
				weChatTask.getToken_getTicket();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
