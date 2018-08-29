package com.sy.test.wechat.config;

import com.google.gson.JsonObject;
import com.sy.test.wechat.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeChatConfig {
    // 公众号网页授权配置和公众号配置 appId
    @Value("${appId}")
    private String appId;
    // 公众号网页授权配置和公众号配置 appSecret
    @Value("${appSecret}")
    private String appSecret;
    // 配置的凭证token
    @Value("${token}")
    private String token;

    //公众号的调用凭据access_token
    private String accessToken;
    // 凭证有效时间，单位：秒
    private int expiresIn;
    // 生成时间
    private Long generateTime;

    //
    @Value("${get_access_token}")
    private String getAccessTokenUrl;

    private synchronized void getWechatAccessToken() {
        if (System.currentTimeMillis() > (generateTime + expiresIn * 1000)) {
            String requestUrl = getAccessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
            JsonObject jsonObject = null;
            try {
                jsonObject = HttpClientUtils.httpRequest(requestUrl, "GET", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != jsonObject) {
                this.accessToken = jsonObject.get("access_token").getAsString();
                this.expiresIn = jsonObject.get("expires_in").getAsInt();
                this.generateTime = System.currentTimeMillis();
            }
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        if (System.currentTimeMillis() > (generateTime + expiresIn * 1000)) {
            getWechatAccessToken();
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Long generateTime) {
        this.generateTime = generateTime;
    }
}
