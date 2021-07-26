package com.demo.proxy;

public class AdminServiceImpl implements AdminService{
    @Override
    public void update() {
        System.out.println("修改数据");
    }

    @Override
    public Object find() {
        System.out.println("查看数据");
        return new Object();
    }
}
