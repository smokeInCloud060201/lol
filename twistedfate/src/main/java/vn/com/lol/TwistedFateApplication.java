package vn.com.lol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Ngoc-Khanh
 * @project Default (Template) Project
 */

@EnableEurekaServer
@SpringBootApplication
public class TwistedFateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwistedFateApplication.class, args);
    }
}