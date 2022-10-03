package com.swasthify.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swasthify.DTOs.HospitalOrders;
import com.swasthify.DTOs.HospitalsDto;
import com.swasthify.DTOs.changePassword;
import com.swasthify.Entities.HospitalServices;
import com.swasthify.Entities.Hospitals;
import com.swasthify.Entities.Orders;
import com.swasthify.Exception.ResourceNotFound;
import com.swasthify.Repository.HospitalRepo;
import com.swasthify.Service.HospitalsService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class HospitalController {

	@Autowired
	private HospitalsService hospService;
	//sign up hospital
	@PostMapping("/register-hospital")
	public ResponseEntity<Hospitals> registerHospital(@RequestBody Hospitals hosp) {
		return hospService.saveHospital(hosp);
	}
	//login hospital
	@PostMapping("/login-hospital")
	public ResponseEntity<Hospitals> loginHospital(@RequestBody Hospitals hosp) {
		return hospService.loginUser(hosp);
	}
	// Change Password
	@PutMapping("/password-hospital/{id}")
	public ResponseEntity<Hospitals> changePassword(@PathVariable Long id, @RequestBody changePassword updateHospitalPassword) throws Exception{     
		return hospService.changePasswordToNewPassword(id, updateHospitalPassword);
	}
	// Delete hospital	
	@DeleteMapping("/hospital/{id}")	
	public ResponseEntity<Map<String,Boolean>> deleteHospital(@PathVariable Long id){
		return hospService.deleteHospital(id);
	}
	//add a new service
	@PutMapping("/add-service/{id}")
	public ResponseEntity<HospitalServices> addANewService(@PathVariable Long id, @RequestBody HospitalServices newService){
		return hospService.addNewService(id, newService);
	}
	//get service list
	@GetMapping("/services/{id}")
	public ResponseEntity<List<HospitalServices>> getServiceList(@PathVariable Long id){
		return hospService.getServiceListByHospId(id);
	}
	//update a service
	@PutMapping("/update-service/{id}")
	public ResponseEntity<HospitalServices> updateService(@PathVariable Long id, @RequestBody HospitalServices serviceObj){
		return hospService.updateService(id, serviceObj);
	}
	
}
