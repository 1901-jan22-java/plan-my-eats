package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "PME_RESTAURANT_PHOTOS")
public class RestaurantPhoto {

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "PME_RES_PHOTO_SEQ_GEN", sequenceName = "PME_PHOTO_RES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME_RES_PHOTO_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "reference")
	private String reference;

	public RestaurantPhoto() {
		super();
	}

	public RestaurantPhoto(String reference) {
		super();
		this.reference = reference;
	}

	public RestaurantPhoto(int id, String reference) {
		super();
		this.id = id;
		this.reference = reference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "RestaurantPhotos [id=" + id + ", reference=" + reference + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		RestaurantPhoto other = (RestaurantPhoto) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

}
