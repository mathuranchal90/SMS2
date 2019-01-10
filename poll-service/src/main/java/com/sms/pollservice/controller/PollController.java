package com.sms.pollservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sms.pollservice.model.Poll;
import com.sms.pollservice.payload.ApiResponse;
import com.sms.pollservice.payload.PollRequest;
import com.sms.pollservice.service.PollService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/polls")
public class PollController {
	
	 

	    @Autowired
	    private PollService pollService;

	    private static final Logger logger = LoggerFactory.getLogger(PollController.class);
	    
	   
	    
	    @PostMapping
	    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
	        Poll poll = pollService.createPoll(pollRequest);

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest().path("/{pollId}")
	                .buildAndExpand(poll.getId()).toUri();

	        return ResponseEntity.created(location)
	                .body(new ApiResponse(true, "Poll Created Successfully"));
	    }

}
