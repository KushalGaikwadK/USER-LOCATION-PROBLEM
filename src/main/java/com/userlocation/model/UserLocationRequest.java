package com.userlocation.model;

import org.springframework.stereotype.Component;

@Component
public class UserLocationRequest {

	private Integer id;
	private String userRole;
	private String name;
	private Double latitude;
	private Double longitude;
	private String excluded;
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getExcluded() {
		return excluded;
	}
	public void setExcluded(String excluded) {
		this.excluded = excluded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
