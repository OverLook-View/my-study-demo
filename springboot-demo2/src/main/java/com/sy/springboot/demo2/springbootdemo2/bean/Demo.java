package com.sy.springboot.demo2.springbootdemo2.bean;

import javax.persistence.Id;

/**
 * @description:
 * @author: OverlookView
 * @create: 2018-09-11 16:32
 **/
public class Demo extends BaseBean {

    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
