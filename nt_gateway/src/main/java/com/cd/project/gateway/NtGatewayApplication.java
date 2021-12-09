package com.cd.project.gateway;

import com.cd.project.config.LocalConfigInfoProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class NtGatewayApplication {

    public static void main(String[] args) {
        LocalConfigInfoProcessor.init();
        SpringApplication.run(NtGatewayApplication.class, args);
    }

}
