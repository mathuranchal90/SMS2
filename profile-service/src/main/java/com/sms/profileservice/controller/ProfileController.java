package com.sms.profileservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

import com.netflix.discovery.EurekaClient;
import com.sms.profileservice.model.UserProfile;
import com.sms.profileservice.payload.UserProfileResponse;
import com.sms.profileservice.repository.UserProfileRepository;
import com.sms.profileservice.service.UserProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
    private UserProfileRepository userProfileRepository;
	
	@Autowired
    private UserProfileService userProfileService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	
	 @GetMapping("/{user_Id}")
	    public UserProfile getProfileById(@PathVariable Long userId) {
	        return userProfileService.getProfileById(userId);
	    }
	 
	 @PostMapping("/saveImage")
	    public void saveImage(@RequestParam("file") MultipartFile File) {
		 
		 userProfileService.saveImage(File);
	     System.out.println("Image Saved");   
	      
	    }
	 

}
