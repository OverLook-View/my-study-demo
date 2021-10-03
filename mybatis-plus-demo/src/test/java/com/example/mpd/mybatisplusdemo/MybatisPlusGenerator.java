package com.example.mpd.mybatisplusdemo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class MybatisPlusGenerator {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.username}")
    private String datasourceUsername;
    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Test
    public void generate() {
        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create(
                datasourceUrl,
                datasourceUsername,
                datasourcePassword
        ).globalConfig(builder -> {
            builder.author("baomidou")// 设置作者
//                    .enableSwagger()// 开启 swagger 模式
                    .fileOverride()// 覆盖已生成文件
                    .outputDir(projectPath+"/src/main/java");// 指定输出目录
        }).packageConfig(builder -> {
            builder.parent("com.example.mpd")// 设置父包名
                    .moduleName("mybatisplusdemo")// 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath+"/src/main/resources/mapper"));// 设置mapperXml生成路径
        }).strategyConfig(builder -> {
            builder.addInclude("user")// 设置需要生成的表名
                    .addTablePrefix("");// 设置过滤表前缀
        }).templateEngine(new FreemarkerTemplateEngine())// 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
