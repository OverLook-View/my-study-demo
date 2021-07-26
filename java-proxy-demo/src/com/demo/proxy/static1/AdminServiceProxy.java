package com.demo.proxy.static1;

import com.demo.proxy.AdminService;

/**
 * 静态代理
 */
public class AdminServiceProxy implements AdminService {
    private AdminService adminService;

    public AdminServiceProxy(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void update() {
        System.out.println("权限判断");
        adminService.update();
        System.out.println("记录update操作日志");
    }

    @Override
    public Object find() {
        System.out.println("权限判断");
        System.out.println("记录find操作日志");
        return adminService.find();
    }
}
