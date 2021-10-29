package com.example.SistemaGestion3.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="doctor")
@Getter@Setter
public class Doctor implements Serializable {
	
	private static final long serialVersionUID = 2162364503406574281L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, length=40)
	private String firstName;
	
    private String lastName;
    
    private String identification; 
    
    private String typeIdentification;
    
    private String professionalCardNumber;
    
    private int yearsOfExperience;
    
    private String specialism;
    
    private Timestamp startAttentionTime;
    
    private Timestamp finalAttentionTime;
 
}
