package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PME_USER_PREFERENCE")
public class Preference {

	@Id
	@Column(name = "PREF_ID")
	@SequenceGenerator(name = "PME_PREF_SEQ_GEN", sequenceName = "PME_PREF_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME_PREF_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int prefId;

	@Column(name = "NAME")
	private String name;

	public Preference() {
	}

	public Preference(String name) {
		super();
		this.name = name;
	}

	public int getPrefId() {
		return prefId;
	}

	public void setPrefId(int prefId) {
		this.prefId = prefId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Preferences [prefId=" + prefId + ", name=" + name + "]";
	}

}
