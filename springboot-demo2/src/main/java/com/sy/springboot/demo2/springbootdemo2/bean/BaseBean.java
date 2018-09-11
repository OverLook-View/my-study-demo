package com.sy.springboot.demo2.springbootdemo2.bean;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.Transient;
import java.io.Serializable;

public class BaseBean implements Serializable {

    //页码
    @Transient
    protected Integer pageNum;

    //每页条数
    @Transient
    protected Integer pageSize;

    @JsonUnwrapped
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @JsonUnwrapped
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
