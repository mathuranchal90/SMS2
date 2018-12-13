package com.sms.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sms.apigateway.payload.SignUpRequest;

@RestController
public class UserController {

	@Autowired
    RestTemplate restTemplate;
	
	@RequestMapping(value = "/api/user/signup", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
	 public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest)
    {
        System.out.println("Posting SIGNUP Request to UserService ");
  
        ResponseEntity<String> response=restTemplate.postForEntity( "http://user-service/api/user/signup", signUpRequest , String.class );
  
        System.out.println("Response Body " + response);
  
        return response;
    }
     
    public String  fallbackMethod(int employeeid){
         
        return "Fallback response:: Details Could Not Be Saved";
    }
  
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
