package com.userlocation.service.impl;

import com.userlocation.model.UserLocation;
import com.userlocation.model.UserLocationRequest;
import com.userlocation.repository.UserLocationRepository;
import com.userlocation.service.UserLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserLocationServiceImpl implements UserLocationService {

	@Autowired
	private UserLocationRepository userLocationRepository;

	@Override
	public List<UserLocation> getAllUsers(int limit) {
		List<UserLocation> userLocationList = (List<UserLocation>) userLocationRepository.findNonExcludedUsers("N");
		List<UserLocation> finalList = new ArrayList<>();
		Map<Double, UserLocation> hypUserMap = new TreeMap<>();

		userLocationList.stream().filter(user -> "N".equals(user.getExcluded())).forEach(userLoc -> {
			double hypotenuse = Math.sqrt((userLoc.getLatitude() * userLoc.getLatitude())
					+ (userLoc.getLongitude() * userLoc.getLongitude()));
			hypUserMap.put(hypotenuse, userLoc);
		});

		for (Double key : hypUserMap.keySet()) {
			finalList.add(hypUserMap.get(key));
			if (limit-- == 1)
				break;
		}
		return finalList;
	}

	@Override
	public void createUserLocation(UserLocationRequest request) {
		UserLocation userLocation = mapToUserLocation(request);
		userLocationRepository.save(userLocation);
	}

	private UserLocation mapToUserLocation(UserLocationRequest request) {
		UserLocation userLocation = new UserLocation();
		userLocation.setId(request.getId());
		userLocation.setExcluded(request.getExcluded());
		userLocation.setLatitude(request.getLatitude());
		userLocation.setLongitude(request.getLongitude());
		userLocation.setName(request.getName());
		return userLocation;
	}

	@Override
	public UserLocation updateUserLocation(UserLocationRequest request) {
		UserLocation user = userLocationRepository.findByName(request.getName());
		if (null != user) {
			UserLocation userLocation = mapToUserLocation(request);
			userLocationRepository.save(userLocation);
		}
		return user;
	}
}
