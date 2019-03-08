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
@Table(name = "RESTUARANTS")
@Component
public class Restuarant {
	@Id
	@Column(name = "RESTUARANT_ID")
	@SequenceGenerator(name = "RES_SEQ_GEN", sequenceName = "RES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "RES_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int restuarantId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "LOCATION")
	private String location;

	public Restuarant() {
	}

	public Restuarant(int restuarantId, String name, String location) {
		super();
		this.restuarantId = restuarantId;
		this.name = name;
		this.location = location;
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

	@Override
	public String toString() {
		return "Restuarant [restuarantId=" + restuarantId + ", name=" + name + ", location=" + location + "]";
	}

}
