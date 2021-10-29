package com.example.SistemaGestion3.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="APPOINTMENT")
@Getter@Setter
public class Apointment implements Serializable {

	private static final long serialVersionUID = -4243708434815027384L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne()
	private Patient patient;
	
	@OneToOne()
    private Doctor doctor;
	
	
    private Timestamp startTime;
	
	@Column(name="dateApointment")
	private Date date;
	
	
}
