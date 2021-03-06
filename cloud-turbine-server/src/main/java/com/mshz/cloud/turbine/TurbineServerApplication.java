package com.mshz.cloud.turbine;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableHystrixDashboard
@SpringBootApplication
@EnableEurekaClient
public class TurbineServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TurbineServerApplication.class).web(true).run(args);
	}
}
