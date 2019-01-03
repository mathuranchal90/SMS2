package com.sms.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.sms.zuulgateway.filters.pre.SimpleFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
	
	 @Bean
	  public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	  }

}

