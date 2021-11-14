package org.example.test.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType carType;
    private int age;
    private int height;
    private int weight;
    private Date createTime;
    private double price;
    private double rebate;
    private String intact;

    public enum CarType {
        AAA(1, "aaa"),
        BBB(2, "bbb");

        private Integer code;
        private String desc;

        CarType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
}
