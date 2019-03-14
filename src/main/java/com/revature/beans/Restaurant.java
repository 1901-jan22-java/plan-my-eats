package com.revature.beans;

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

	@Column(name = "TYPE")
	private String type;

	@Column(name = "IMAGE_REF")
	private String imgRef;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, String location, String type) {
		super();

		this.name = name;
		this.location = location;
		this.type = type;
	}

	public Restaurant(int restaurantId, String name, String location, String type) {
		super();

		this.restaurantId = restaurantId;
		this.name = name;
		this.location = location;
		this.type = type;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestuarantId(int restaurantId) {
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

	@Override
	public String toString() {
		return "Restuarant [restuarantId=" + restaurantId + ", name=" + name + ", location=" + location + ", type="
				+ type + "]";
	}

}
