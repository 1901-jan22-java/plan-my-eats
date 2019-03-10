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
	private int restuarantId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String location;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="IMAGE_REF")
	private String imgRef;
	
	public Restaurant() { }
	
	public Restaurant(String name, String location, String type) {
		this.name = name;
		this.location = location;
		this.type = type;
	}
	public int getRestuarantId() {
		return restuarantId;
	}
	public void setRestuarantId(int restuarantId) {
		this.restuarantId = restuarantId;
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
	@Override
	public String toString() {
		return "Restuarant [restuarantId=" + restuarantId + ", name=" + name + ", location=" + location + ", type="
				+ type + "]";
	}
	
	
	
}
