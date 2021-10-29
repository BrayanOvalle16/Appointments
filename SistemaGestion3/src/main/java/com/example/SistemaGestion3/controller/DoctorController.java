package com.example.SistemaGestion3.controller;

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

import com.example.SistemaGestion3.entities.Doctor;
import com.example.SistemaGestion3.services.DoctorServices;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorServices doctorServices;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Doctor doctor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorServices.save(doctor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")Long doctorId) {
		Optional<Doctor> doctor= doctorServices.findById(doctorId);
		
		if(!doctor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(doctor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Doctor detailsDoctor, @PathVariable(value="id")Long doctorId) {
		Optional<Doctor> doctor = doctorServices.findById(doctorId);
		
		if(!doctor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(detailsDoctor, doctor.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorServices.save(doctor.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")Long doctorId) {
		Optional<Doctor> doctor = doctorServices.findById(doctorId);
		
		if(!doctor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		doctorServices.deleteById(doctorId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Doctor> readAll() {
		List<Doctor> Doctor = StreamSupport.stream(doctorServices.findAll().spliterator(), false).collect(Collectors.toList());
		return Doctor;
	}
}
