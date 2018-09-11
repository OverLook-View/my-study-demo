package com.sy.springboot.demo2.springbootdemo2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共响应体传输类
 * @param <T>
 */
public class CommonResponseDto<T> implements Serializable {
    private String state;
    private String code;
    private String msg;
    private T data;
    private Date currentTime;

    public CommonResponseDto() {
    }

    public CommonResponseDto(T data) {

        this.state = "0";
        this.code = "0";
        this.msg = "success";
        this.data = data;
        this.currentTime = new Date();
    }

    public CommonResponseDto(String msg, T data) {

        this.state = "0";
        this.code = "0";
        this.msg = msg;
        this.data = data;
        this.currentTime = new Date();
    }

    public CommonResponseDto(String code, String msg, T data) {

        this.state = "0";
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.currentTime = new Date();
    }
    public CommonResponseDto(String code, String msg) {

        this.state = "0";
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.currentTime = new Date();
    }



    @Override
    public String toString() {
        return "CommonResponseDto{" +
                "state='" + state + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", currentTime=" + currentTime +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
