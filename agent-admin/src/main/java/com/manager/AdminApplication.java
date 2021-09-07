package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 启动程序
 * @author marvin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  运营后台系统启动成功!!!   ლ(´ڡ`ლ)ﾞ");
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        servletWebServerFactory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars","|{}[]"));
        return servletWebServerFactory;
    }
}
