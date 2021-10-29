package com.example.SistemaGestion3.services;

import com.example.SistemaGestion3.repository.PatientRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGestion3.entities.Patient;

@Service
public class PatientServices implements PatientServicesI {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Transactional(readOnly=true)
	public Iterable<Patient> findAll() {
		return patientRepository.findAll();
	}
	
	@Transactional
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}
	
	@Transactional(readOnly=true)
	public Optional<Patient> findById(Long id) {
		return patientRepository.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		patientRepository.deleteById(id);
	}
}
