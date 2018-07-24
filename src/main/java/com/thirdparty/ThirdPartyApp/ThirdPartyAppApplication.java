package com.thirdparty.ThirdPartyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@SpringBootApplication(scanBasePackages = "com.thirdparty")
@EnableFeignClients(basePackages = "com.thirdparty.service")
@EnableAspectJAutoProxy
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ThirdPartyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdPartyAppApplication.class, args);
	}
}
