package com.revature.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "PME_RESTAURANTS")
@Component
public class Restaurant {

	@Id
	@Column(name = "RESTAURANT_ID")
	@SequenceGenerator(name = "PME_RES_SEQ_GEN", sequenceName = "PME_RES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME_RES_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int restaurantId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String location;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "IMAGE_REF")
	private List<RestaurantPhoto> imgRef;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, String location, List<RestaurantPhoto> imgRef) {
		super();
		this.name = name;
		this.location = location;
		this.imgRef = imgRef;
	}

	public Restaurant(String name, String location, String latitude, String longitude, String type,
			List<RestaurantPhoto> imgRef) {
		super();
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
		this.imgRef = imgRef;
	}

	public Restaurant(int restaurantId, String name, String location, String latitude, String longitude, String type,
			List<RestaurantPhoto> imgRef) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
		this.imgRef = imgRef;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RestaurantPhoto> getImgRef() {
		return imgRef;
	}

	public void setImgRef(List<RestaurantPhoto> imgRef) {
		this.imgRef = imgRef;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", location=" + location + ", latitude="
				+ latitude + ", longitude=" + longitude + ", type=" + type + ", imgRef=" + imgRef + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
