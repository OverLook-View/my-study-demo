package com.test.designpattern.create;

public class BuilderPattern {
    /**
     * 意图：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
     * <p>
     * 主要解决：主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。
     * <p>
     * 何时使用：一些基本部件不会变，而其组合经常变化的时候。
     * <p>
     * 如何解决：将变与不变分离开。
     * <p>
     * 关键代码：建造者：创建和提供实例，导演：管理建造出来的实例的依赖关系。
     * <p>
     * 应用实例： 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 2、JAVA 中的 StringBuilder。
     * <p>
     * 优点： 1、建造者独立，易扩展。 2、便于控制细节风险。
     * <p>
     * 缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
     * <p>
     * 使用场景： 1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。
     * <p>
     * 注意事项：与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
     */

    public static void main(String[] args) {
        CustomObject customObject = new CustomObject.CustomBuilder().setParam1("111").setParam2("222").setParam3("333").setParam4(444).build();
    }
}

class CustomObject {
    private String param1;
    private String param2;
    private String param3;
    private Integer param4;

    static class CustomBuilder {
        private String param1 = null;
        private String param2 = null;
        private String param3 = null;
        private Integer param4 = null;

        public CustomBuilder setParam1(String param1) {
            this.param1 = param1;
            return this;
        }

        public CustomBuilder setParam2(String param2) {
            this.param2 = param2;
            return this;
        }

        public CustomBuilder setParam3(String param3) {
            this.param3 = param3;
            return this;
        }

        public CustomBuilder setParam4(Integer param4) {
            this.param4 = param4;
            return this;
        }

        public CustomObject build() {
            return new CustomObject(this);
        }
    }

    public CustomObject(CustomBuilder customBuilder) {
        this.param1 = customBuilder.param1;
        this.param2 = customBuilder.param2;
        this.param3 = customBuilder.param3;
        this.param4 = customBuilder.param4;
    }
}