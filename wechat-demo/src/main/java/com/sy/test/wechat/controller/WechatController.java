package com.sy.test.wechat.controller;

import com.google.gson.JsonObject;
import com.sy.test.wechat.config.WeChatConfig;
import com.sy.test.wechat.utils.HttpClientUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : sy
 * @date: 创建时间：2017年7月11日 上午10:33:08
 * @version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("wechat")
public class WechatController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeChatConfig weChatConfig;
    @Value("${code_get_user_info}")
    private String code_get_user_info;
    @Value("${code_get_user_detail}")
    private String code_get_user_detail;
    @Value("${redirect_authorize}")
    private String redirect_authorize;

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void wechatCallback(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 配置微信公众号校验
        //自定义凭证
        String token = weChatConfig.getToken();
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        //将token、timestamp、nonce三个参数进行字典序排序
        ArrayList<String> list = new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add(token);
        Collections.sort(list);

        //将三个参数字符串拼接成一个字符串进行sha1加密
        String shaHex = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));

        if (!signature.equals(shaHex)) {
            return;
        }

        PrintWriter out = response.getWriter();
        out.print(echostr);
        out.flush();
        out.close();
    }


    @RequestMapping("/oauth")
    public String oauthRedirect(Model model, HttpServletRequest request, String code, String state) {
        // 获取微信授权token
        String getUserInfoOauthUrl = code_get_user_info.replaceAll("APPID", weChatConfig.getAppId())
                .replaceAll("SECRET", weChatConfig.getAppSecret())
                .replaceAll("CODE", code);
        JsonObject jsonObject = null;
        try {
            jsonObject = HttpClientUtils.httpRequest(getUserInfoOauthUrl, "GET", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("从微信获取用户id异常：" + e);
        }
        if (jsonObject.get("errcode") != null) {
            String openid = jsonObject.get("openid").getAsString();
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUserId(userId);
//            userInfo = userInfoService.queryOne(userInfo);
//            if (userInfo == null) {
            String redirectTarget = request.getRequestURL().toString().replaceAll(request.getContextPath(), "/wechat/register");
            String redirectUrl = redirect_authorize.replaceAll("APPID", weChatConfig.getAppId())
                    .replaceAll("REDIRECT_URI", redirectTarget)
                    .replaceAll("SCOPE", "snsapi_userinfo")
                    .replaceAll("state", "state");
            return "redirect:" + redirectUrl;
//            } else {
//                request.getSession().setAttribute("userInfo", userInfo);
//                return "redirect:" + "../sell/list";
//            }
        } else {
            logger.error("从微信获取用户id失败：" + jsonObject);
        }
        model.addAttribute("tittle", "系统出错了，请稍后再试");
        model.addAttribute("error", "微信授权登陆失败");
        return "error";
    }

    @RequestMapping("register")
    public String register(Model model, HttpServletRequest request, String code, String state) {
        // 获取userId
        String getUserInfoOauth2 = code_get_user_info.replaceAll("ACCESS_TOKEN", weChatConfig.getAccessToken())
                .replaceAll("CODE", code);
        JsonObject jsonObject = null;
        try {
            jsonObject = HttpClientUtils.httpRequest(getUserInfoOauth2, "GET", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("从微信获取用户id异常：" + e);
        }
        //System.out.println("register1" + jsonObject);
        if (jsonObject.get("errcode") != null) {
            String openid = jsonObject.get("openid").getAsString();
            String accessToken = jsonObject.get("access_token").getAsString();
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUserId(userId);
//            userInfo = userInfoService.queryOne(userInfo);
//            if (userInfo == null) {
//                // 获取userId
            String getUserDetail = code_get_user_detail.replaceAll("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openid);
            JsonObject userDetailJson = null;
            try {
                userDetailJson = HttpClientUtils.httpRequest(getUserDetail, "GET", null);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("从微信获取用户详情异常：" + e);
            }
            if (userDetailJson.get("errcode") != null) {
//                    userInfo = new UserInfo();
//                    userId = jsonObject1.get("userid").getAsString();
//                    userInfo.setUserId(jsonObject1.get("userid").getAsString());
//                    userInfo.setUserName(jsonObject1.get("name").getAsString());
//                    int gender = jsonObject1.get("gender").getAsInt();
//                    String sex = null;
//                    if (gender == 1) {
//                        sex = "男";
//                    } else if (gender == 2) {
//                        sex = "女";
//                    } else {
//                        sex = "未知";
//                    }
//                    userInfo.setSex(sex);
//                    userInfo.setPhone(jsonObject1.get("mobile").getAsString());
//                    userInfo.setRole("3");
//                    if ("13817512017".equals(userId) || "15721348209".equals(userId)) {
//                        userInfo.setRole("1");
//                    }
//                    userInfo.setCreateTime(new Date());
//                    userInfo.setCreateUser(jsonObject1.get("userid").getAsString());
//                    Integer insertSelective = userInfoService.insertSelective(userInfo);
//                    userInfo = userInfoService.queryOne(userInfo);
//                } else {
//                    logger.error("从微信获取用户详情失败：" + jsonObject1);
//                }
            }
//            request.getSession().setAttribute("userInfo", userInfo);
            return "redirect:" + "../sell/list";
        } else {
            logger.error("从微信获取用户id失败：" + jsonObject);
        }
        model.addAttribute("tittle", "系统出错了，请稍后再试");
        model.addAttribute("error", "微信授权登陆注册失败");
        return "error";
    }
}
