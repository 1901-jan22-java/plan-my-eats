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
@Table(name = "PME_RECIPE")
@Component
public class Recipe {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "PME_REC_SEQ_GEN", sequenceName = "PME_REC_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME_REC_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int id;
	
}
