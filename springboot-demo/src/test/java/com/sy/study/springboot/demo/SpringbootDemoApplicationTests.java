package com.sy.study.springboot.demo;

import com.sy.study.springboot.demo.bean.Demo;
import com.sy.study.springboot.demo.service.AsyncServicce;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootDemoApplicationTests {

    @LocalServerPort
    private int port;
    private URL url;
    @Autowired
    private TestRestTemplate template;

    @Before
    public void init() throws MalformedURLException {
        this.url = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void contextLoads() {
        ResponseEntity<String> responseEntity = template.getForEntity(url.toString(), String.class);
        System.out.println(responseEntity.getBody());
    }

    @Autowired
    private AsyncServicce asyncServicce;
    @Test
    public void test1() throws InterruptedException {
        List<Demo> user = asyncServicce.findUser("123");
        System.out.println(user);
    }

}
