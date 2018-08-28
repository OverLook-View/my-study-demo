package example;

import mypackage.HelloWorld;
import mypackage.HelloWorldService;

public class HelloWorldClient {
    /**
     * 在dos命令下输入 wsimport -s “src目录” -p “生成类所在包名” -keep “wsdl发布地址”
     * 示例：wsimport -s C:\User\admin\Desktop\src -p com.sy.test -keep http://127.0.0.1:9000/HelloWorld?wsdl
     */
    public static void main(String[] argv) {
        HelloWorld helloWorldPort = new HelloWorldService().getHelloWorldPort();
        //invoke business method
        String s = helloWorldPort.sayHelloWorldFrom("client");
        System.out.println(s);
    }
}
