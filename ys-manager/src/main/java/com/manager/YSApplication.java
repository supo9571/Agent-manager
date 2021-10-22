package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author marvin 2021/10/22
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = { "com.manager"})
public class YSApplication {

    public static void main(String[] args) {
        SpringApplication.run(YSApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  银商后台启动成功!!!   ლ(´ڡ`ლ)ﾞ");
    }
}
