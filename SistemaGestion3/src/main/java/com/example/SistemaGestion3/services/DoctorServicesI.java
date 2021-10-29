package com.example.SistemaGestion3.services;

import java.util.Optional;

import com.example.SistemaGestion3.entities.Doctor;

public interface DoctorServicesI {

	public Iterable<Doctor> findAll();
	
	public Doctor save(Doctor doctor);
	
	public Optional<Doctor> findById(Long id);
	
	public void deleteById(Long id);
}
