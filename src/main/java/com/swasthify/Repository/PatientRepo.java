package com.swasthify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swasthify.Entities.Patients;

@Repository
public interface PatientRepo extends JpaRepository<Patients, Long>{

	Patients findByEmailId(String emailId);
	
}
