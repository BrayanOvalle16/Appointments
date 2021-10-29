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

import com.example.SistemaGestion3.entities.Apointment;
import com.example.SistemaGestion3.services.ApointmentServicesI;

@RestController
@RequestMapping("/api/apointment")
public class ApointmentController {

	@Autowired
	ApointmentServicesI apointmentServices;
	
	/**
	 * Se encarga de crear una cita. En caso de que la cita este fuera del rango del horario del médico, no
	 * permitirá crearla.
	 * 
	 * @param appointment Representa la información de la cita como por ejemplo el paciente y el médico respectivamente
	 *        encapsulado en un objeto de tipo Appointment.
	 *        
	 * @return La cita creada encapsulada en un objeto de tipo Appointment.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Apointment appointment) {
		Date newDate = new Date(appointment.getDate().getTime()+ (1000 * 60 * 60 * 24));
		appointment.setDate(newDate);
		appointment.setId((long) 0);
		
		if(apointmentServices.checkAvailability(appointment)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(apointmentServices.save(appointment));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value="id")Long appointmentId) {
		Optional<Apointment> appointment = apointmentServices.findById(appointmentId);
		
		if(!appointment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(appointment);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Apointment detailsCita, @PathVariable(value="id")Long appointmentId) {
		Optional<Apointment> appointment = apointmentServices.findById(appointmentId);
		
		if(!appointment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Date newDate = new Date(detailsCita.getDate().getTime()+ (1000 * 60 * 60 * 24));
		detailsCita.setDate(newDate);
		
		if(apointmentServices.checkAvailability(detailsCita)) {
			BeanUtils.copyProperties(detailsCita, appointment.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(apointmentServices.save(appointment.get()));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Apointment>> readAll() {
		List<Apointment> listaCitas = StreamSupport
									.stream(apointmentServices.findAll().spliterator(), false)
									.collect(Collectors.toList());
		
		return ResponseEntity.ok(listaCitas);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id")Long appointmentId) {
		Optional<Apointment> appointment = apointmentServices.findById(appointmentId);
		
		if(!appointment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		apointmentServices.deleteById(appointmentId);
		return ResponseEntity.ok().build();	
	}
}
