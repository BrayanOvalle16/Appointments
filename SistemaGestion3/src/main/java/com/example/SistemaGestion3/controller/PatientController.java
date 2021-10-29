package com.example.SistemaGestion3.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaGestion3.entities.Patient;
import com.example.SistemaGestion3.services.PatientServicesI;


@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientServicesI pacientServices;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Patient pacient) {
		Date newDate = new Date(pacient.getDateOfBirth().getTime()+ (1000 * 60 * 60 * 24));
		pacient.setDateOfBirth(newDate);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacientServices.save(pacient));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")Long patientId) {
		Optional<Patient> pacient = pacientServices.findById(patientId);
		
		if(!pacient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pacient);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Patient detailsPatient, @PathVariable(value="id")Long patientId) {
		Optional<Patient> pacient= pacientServices.findById(patientId);
		Date newDate = new Date(detailsPatient.getDateOfBirth().getTime()+ (1000 * 60 * 60 * 24));
		detailsPatient.setDateOfBirth(newDate);
		
		if(!pacient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(detailsPatient, pacient.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(pacientServices.save(pacient.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")Long patientId) {
		Optional<Patient> pacient= pacientServices.findById(patientId);
		
		if(!pacient.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		pacientServices.deleteById(patientId);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public List<Patient> readAll() {
		List<Patient> listPacients= StreamSupport.stream(pacientServices.findAll().spliterator(), false).collect(Collectors.toList());
		return listPacients;
	}
	
}
