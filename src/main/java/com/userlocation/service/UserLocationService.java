package com.userlocation.service;

import java.util.List;

import com.userlocation.model.UserLocation;
import com.userlocation.model.UserLocationRequest;

public interface UserLocationService {
	List<UserLocation> getAllUsers(int num);

	void createUserLocation(UserLocationRequest userLocations);

	UserLocation updateUserLocation(UserLocationRequest request);
}
