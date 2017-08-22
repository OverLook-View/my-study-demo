package com.biz.sy.test.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @author : sy
 * @date: 创建时间：2017年8月22日 上午10:25:38 
 * @version: 1.0   
 * @Description:   
 */
@Controller
@RequestMapping("webSocket")
public class WebSocketController {

	@RequestMapping("")
	public String webSocket() {
		return "webSocket";
	}
}
