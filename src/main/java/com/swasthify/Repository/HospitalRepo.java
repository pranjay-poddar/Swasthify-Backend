package com.swasthify.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swasthify.DTOs.CumulativeServices;
import com.swasthify.DTOs.HospitalsDto;
import com.swasthify.Entities.Hospitals;

@Repository
public interface HospitalRepo extends JpaRepository<Hospitals, Long>{

	Hospitals findByemailId(String email);
//	@Query("select new com.swasthify.DTOs.HospitalsDto(hos.id, hos.hospitalName, hos.emailId, hos.city, hos.State, hos.contact, hService.icuBeds) from Hospitals hos join hos.hospServices hService where hos.id = ?1")
//	public List<HospitalsDto> findHospitalsById(Long id);
//
//	@Query("select new com.swasthify.DTOs.CumulativeServices(h.icuBeds, h.isolationBeds, h.oxygenCylinders, h.vaccine1, h.vaccine2) from Hospitals h")
//	List<CumulativeServices> getAllServices();
//
//	@Query("select new com.swasthify.DTOs.HospitalsDto(h.id, h.hospitalName, h.emailId, h.city, h.State, h.contact, h.icuBeds, h.isolationBeds, h.oxygenCylinders, h.vaccine1, h.vaccine2) from Hospitals h where h.city = ?1")
//	List<HospitalsDto> fetchAllHospitals(String city);
//
//	@Query("select new com.swasthify.DTOs.CumulativeServices(h.icuBeds, h.isolationBeds, h.oxygenCylinders, h.vaccine1, h.vaccine2) from Hospitals h where h.city = ?1")
//	List<CumulativeServices> getAllServicesByCity(String city);
//	
//	@Query("select new com.swasthify.DTOs.HospitalsDto(h.id, h.hospitalName, h.emailId, h.city, h.State, h.contact, h.icuBeds, h.isolationBeds, h.oxygenCylinders, h.vaccine1, h.vaccine2) from Hospitals h where h.city = ?1")
//	List<HospitalsDto> getAllHospitalsByService(String city);
//	
//	@Query("select new com.swasthify.DTOs.HospitalsDto(h.id, h.hospitalName, h.emailId, h.city, h.State, h.contact, h.icuBeds, h.isolationBeds, h.oxygenCylinders, h.vaccine1, h.vaccine2) from Hospitals h where h.id = ?1")
//	HospitalsDto findByHospitalId(Long id);




}
