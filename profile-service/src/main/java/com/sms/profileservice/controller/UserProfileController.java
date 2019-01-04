package com.sms.profileservice.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;



import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
	
	  @Autowired
	    private RestTemplate restTemplate;
	
	  @Autowired
	    private EurekaClient eurekaClient;
	   
	    @Value("${service.upload-service.serviceId}")
	    private String uploadServiceServiceId;
	    
	    
	    @PostMapping("/saveImage")
	    public String  saveImage(@RequestParam("file") MultipartFile File) throws Exception {
		
		 String fileName= StringUtils.cleanPath(File.getOriginalFilename());
		 
		 try {
				//Check if the file's name contain invalid characters
				if(fileName.contains("..")) {
					throw new Exception("Sorry!! Filename contains invalid path sequence"+ fileName);
				}
				
				
				 MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
			        bodyMap.add("file", getUserFileResource(File));
			        HttpHeaders headers = new HttpHeaders();
			        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

			        Application application = eurekaClient.getApplication(uploadServiceServiceId);
					InstanceInfo instanceInfo = application.getInstances().get(0);
					String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"uploadFile";
					System.out.println("URL" + url);
			        ResponseEntity<String> response = restTemplate.exchange(url,
			                HttpMethod.POST, requestEntity, String.class);
			        System.out.println("response status: " + response.getStatusCode());
			        System.out.println("response body: " + response.getBody());
				
				System.out.println("Image Saved");
				
				return response.getStatusCode()+"\n"+response.getBody();
				
			}catch(IOException ex) {
				throw new Exception("Could not store file"+ fileName +".Please try again!");
			}
	      
	    }
	    
	    public static FileSystemResource getUserFileResource(MultipartFile File) throws IOException {
	        //todo replace tempFile with a real file
	        Path tempFile = Files.createTempFile(File.getName(),".jpeg");
	        Files.write(tempFile, File.getBytes());
	        System.out.println("uploading: " + tempFile);
	        File file = tempFile.toFile();
	        //to upload in-memory bytes use ByteArrayResource instead
	        return  new FileSystemResource(file);
	    }

}
