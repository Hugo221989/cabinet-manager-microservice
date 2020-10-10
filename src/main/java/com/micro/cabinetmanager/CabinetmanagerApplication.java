package com.micro.cabinetmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableHystrix
@EnableCaching
@EnableFeignClients
public class CabinetmanagerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CabinetmanagerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CabinetmanagerApplication.class, args);
	}
	
//	@LoadBalanced
//	@Bean
//	RestTemplate restTemplate() {
//	      return new RestTemplate();
//	  }
//	
//	@Bean
//	public RestTemplateCustomizer readTimeoutCustomizer() {
//	    return restTemplate -> {
//	        HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
//	        clientRequestFactory.setReadTimeout(30000);
//	        restTemplate.setRequestFactory(clientRequestFactory);
//	    };
//	}

}
