package com.example.SistemaGestion3.services;

import java.util.Optional;

import com.example.SistemaGestion3.entities.Patient;

public interface PatientServicesI {
	
	public Iterable<Patient> findAll();
	
	public Patient save(Patient Patient);
	
	public Optional<Patient> findById(Long id);
	
	public void deleteById(Long id);
}
