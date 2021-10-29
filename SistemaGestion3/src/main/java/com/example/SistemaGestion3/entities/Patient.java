package com.example.SistemaGestion3.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Patient")
@Getter@Setter
public class Patient implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 	private long id;
	
	@Column(length=50, nullable=false)
    private String fullName;
	
	@Column(name="DATE_OF_BIRTH")
    private Date dateOfBirth;
	
    private String identification;
    
    private String tipoidentification;
    
    private String healthCareProvider;
    
    private String clinicHistory;
 
	private static final long serialVersionUID = -2744243821334856358L;
}
