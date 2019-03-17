package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RESTAURANTS")
public class Restaurant 
{
	@Id
	@Column(name="RESTAURANT_ID")
	@SequenceGenerator(name="RES_SEQ_GEN", sequenceName="RES_SEQ", allocationSize=1)
	@GeneratedValue(generator="RES_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	private int restaurantId;

	private String name;
	
	@Column(name="ADDRESS")
	private String location;

	@Column(name="TYPE")
	private String type;
	
	@Column(name="IMAGE_REF")
	private String imgRef;
	
	@Column(name="LOCATION")
	private String latlng;

	public Restaurant() { }
	
	public Restaurant(String name, String location, String type) {
		this.name = name;
		this.location = location;
		this.type = type;
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

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImgRef() {
		return imgRef;
	}

	public void setImgRef(String imgRef) {
		this.imgRef = imgRef;
	}
	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	@Override
	public String toString() {
		return "Restuarant [restaurantId=" + restaurantId + ", name=" + name + ", location=" + location + ", type="
				+ type + "]";
	}
}
