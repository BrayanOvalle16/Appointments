package com.example.SistemaGestion3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGestion3.entities.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
}
