package com.userlocation.controller;

import com.userlocation.model.UserLocation;
import com.userlocation.model.UserLocationRequest;
import com.userlocation.service.UserLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/base_url")
public class UserLocationController {

	@Autowired
	private UserLocationService userLocationService;

	@PostMapping(path = "/create_data", consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUserLocation(@RequestBody UserLocationRequest request) {
		if(request.getUserRole().equalsIgnoreCase("ADMIN")) {
			userLocationService.createUserLocation(request);
			return ResponseEntity.ok("User details added");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User don't have permission to create User");
		
	}

	@PutMapping("/update_data")
	public ResponseEntity<String> updateUserLocation(@RequestBody UserLocationRequest request) {
		if(request.getUserRole().equalsIgnoreCase("ADMIN")) {
			UserLocation user = userLocationService.updateUserLocation(request);
			if(null!=user) {
				return ResponseEntity.ok("User details updated successfully");
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not available to update");
			}
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User don't have permission to update User");
	}

	@GetMapping(path = "/get_users/{limit}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserLocation>> getAllUsers(@PathVariable("limit") Integer limit) {
		return ResponseEntity.ok().body(this.userLocationService.getAllUsers(limit));
	}
}
