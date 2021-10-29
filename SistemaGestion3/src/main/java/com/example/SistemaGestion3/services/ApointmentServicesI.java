package com.example.SistemaGestion3.services;

import java.util.Optional;

import com.example.SistemaGestion3.entities.Apointment;

public interface ApointmentServicesI {

	public Iterable<Apointment> findAll();
	
	public Apointment save(Apointment appointment);
	
	public Optional<Apointment> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean checkAvailability(Apointment appointment);
}
