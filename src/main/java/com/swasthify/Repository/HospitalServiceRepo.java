package com.swasthify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swasthify.Entities.HospitalServices;

@Repository
public interface HospitalServiceRepo extends JpaRepository<HospitalServices, Long>{

}
