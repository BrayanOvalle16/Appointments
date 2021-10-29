package com.example.SistemaGestion3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGestion3.entities.Apointment;
import com.example.SistemaGestion3.repository.ApointmentRepository;

@Service
public class ApointmentServices implements ApointmentServicesI {
	
	@Autowired
	ApointmentRepository apointmentRepository;
	
	@Transactional(readOnly=true)
	public Iterable<Apointment> findAll() {
		return apointmentRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Optional<Apointment> findById(Long id) {
		return apointmentRepository.findById(id);
	}
	
	@Transactional
	public Apointment save(Apointment appointment) {
		return apointmentRepository.save(appointment);
	}
	
	@Transactional
	public void deleteById(Long id) {
		apointmentRepository.deleteById(id);
	}
	public boolean checkAvailability(Apointment appointment) {
		int checkDoctorSchedule = apointmentRepository.checkDoctorSchedule(appointment.getStartTime(),  appointment.getDoctor().getId());
		int checkDisponibility = apointmentRepository.checkDisponibility(appointment.getDoctor().getId(),appointment.getDate(), appointment.getStartTime(),appointment.getId());
		int checkMonthlyAppointments = apointmentRepository.checkMonthlyAppointments(appointment.getDate(), appointment.getPatient().getId(), appointment.getDoctor().getId(), appointment.getId());
		return (checkDoctorSchedule>0 && checkDisponibility==0 && checkMonthlyAppointments<2);
	}


}