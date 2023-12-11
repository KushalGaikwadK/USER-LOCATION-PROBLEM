package com.userlocation.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlocation.model.UserLocation;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

	UserLocation findByName(String name);

	@Query(value = "SELECT * FROM user_location where user_location.excluded =?", nativeQuery = true)
	Collection<UserLocation> findNonExcludedUsers(String excluded);
}
