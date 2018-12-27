package com.sms.profileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.sms.profileservice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sms.profileservice.model.UserProfile;
import com.sms.profileservice.payload.UploadFileResponse;
import com.sms.profileservice.payload.UserProfileResponse;
import com.sms.profileservice.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserProfileService {
	
	@Autowired
    private UserProfileRepository userProfileRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(UserProfileService.class);
	
	 public UserProfile getProfileById(Long userId) {
	        UserProfile profile = userProfileRepository.findById(userId).orElseThrow(
	                () -> new ResourceNotFoundException("User", "user_id", userId));
	        
	        return profile;
	    }
	 
	 public String getImgByUserId(Long userId) {
	        String imgUrl = userProfileRepository.getImgByUserId(userId);
	        
	        return imgUrl;
	    }
	 
	 public static void saveImage(MultipartFile file) {
		 
		 final String uri = "http://localhost:8183/uploadFile";
		 UploadFileResponse newFile = new UploadFileResponse(uri, uri, uri, 0);
		 
		 RestTemplate restTemplate = new RestTemplate();
		 UploadFileResponse result= restTemplate.postForObject( uri, newFile, UploadFileResponse.class);
	       

	        System.out.println(result);
	    }



}
