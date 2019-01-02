package com.sms.profileservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sms.profileservice.model.UserProfile;
import com.sms.profileservice.repository.UserProfileRepository;
import com.sms.profileservice.service.UserProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	@Autowired
    private UserProfileRepository userProfileRepository;
	
	@Autowired
    public static UserProfileService userProfileService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	
	 @GetMapping("/{user_Id}")
	    public UserProfile getProfileById(@PathVariable Long userId) {
	        return userProfileService.getProfileById(userId);
	    }
	 
	/** @PostMapping("/saveImage")
	    public static void saveImage(@RequestParam("file") MultipartFile File) {
		 
		 userProfileService.saveImage(File);
	     System.out.println("Image Saved");   
	      
	    }**/
	 

}
