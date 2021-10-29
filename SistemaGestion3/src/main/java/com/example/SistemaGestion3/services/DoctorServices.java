package com.example.SistemaGestion3.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGestion3.entities.Doctor;
import com.example.SistemaGestion3.repository.DoctorRepository;


@Service
public class DoctorServices implements DoctorServicesI {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Transactional(readOnly=true)
	public Iterable<Doctor> findAll() {
		return doctorRepository.findAll();
	}
	
	@Transactional
	public Doctor save(Doctor medico) {
		return doctorRepository.save(medico);
	}
	
	@Transactional(readOnly=true)
	public Optional<Doctor> findById(Long id) {
		return doctorRepository.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
	}
}


