package com.biz.sy.test.wechat.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.biz.sy.test.wechat.common.WeChatTask;

/**
 * 定时触发器
 * 
 * @author sy
 * @date 2017-2-21 10:33:38
 */
@Component
public class TaskJob {

	private static Logger logger = Logger.getLogger(TaskJob.class);

	@Autowired
	private WeChatTask weChatTask;

	public void workForToken() {
		try {
			weChatTask.getToken_getTicket();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
	}
}
