package com.example.SistemaGestion3.repository;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SistemaGestion3.entities.Apointment;

@Repository
public interface ApointmentRepository extends JpaRepository<Apointment, Long> {
	
	@Query(value = "SELECT count(*) " +
				   "FROM DOCTOR " +
				   "WHERE EXTRACT(HOUR FROM start_attention_time)<=EXTRACT(HOUR FROM :start_apointment_time) AND " +
				   "      EXTRACT(HOUR FROM final_attention_time)>EXTRACT(HOUR FROM:start_apointment_time) AND " +
				   "      id=:idDoctor", 
		nativeQuery = true
		)
	int checkDoctorSchedule(@Param("start_apointment_time")Timestamp start_apointment_time, @Param("idDoctor")Long idDoctor); 
	
	@Query(
			value = "SELECT count(*) FROM apointment where doctor_id=:doctorId and date_apointment=:dateApointment and EXTRACT(HOUR FROM start_time)=EXTRACT(HOUR FROM :start_time) and id<>:id",
			nativeQuery = true
			)
	int checkDisponibility(@Param("doctorId")Long doctorId, @Param("dateApointment")Date dateApointment, @Param("start_time")Timestamp start_time, @Param("id")Long id);
	
	@Query(
			value = "SELECT count(*) FROM apointment where patient_id=:patient_id and EXTRACT(MONTH FROM date_apointment)= EXTRACT(MONTH FROM :monthApointment) and EXTRACT(YEAR FROM date_apointment)= EXTRACT(YEAR FROM :monthApointment) and id<>:id  and doctor_id=:idDoctor",
			nativeQuery = true
			)
	int checkMonthlyAppointments(@Param("monthApointment")Date monthApointment, @Param("patient_id")Long patient_id,@Param("idDoctor")Long idDoctor, @Param("id")Long id);
	

}
