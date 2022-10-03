package com.swasthify.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swasthify.DTOs.HospitalOrders;
import com.swasthify.Entities.Beneficiaries;
import com.swasthify.Entities.Orders;

@Repository
public interface BeneficiariesRepo extends JpaRepository<Beneficiaries, Long>{

	@Query("select new com.swasthify.DTOs.HospitalOrders(ben.name, ben.emailId, ben.age, ben.contactNo, ord.id, ord.date, ord.vax1, ord.vax2, ord.isolationBeds, ord.icuBeds, ord.oxygenCylinders, ord.isConfirm, ord.reschedule) from Beneficiaries ben join Orders ord on ben.id = ord.id where ord.hospEmailId = ?1 and ord.isConfirm = false")
	List<HospitalOrders> getAllHospitalOrders(String emailId);

}
