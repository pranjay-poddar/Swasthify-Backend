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

import com.swasthify.DTOs.CumulativeServices;
import com.swasthify.DTOs.HospitalsDto;
import com.swasthify.DTOs.changePassword;
import com.swasthify.Entities.Beneficiaries;
import com.swasthify.Entities.Orders;
import com.swasthify.Entities.Patients;
import com.swasthify.Exception.ResourceNotFound;
import com.swasthify.Service.BeneficiaryService;
import com.swasthify.Service.PatientService;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin
public class PatientController {
	@Autowired
	private PatientService patientSer;
	@Autowired
	private BeneficiaryService benSer;
	//register patient
	@PostMapping("/register-patient")
	public ResponseEntity<Patients> regPatient(@RequestBody Patients patient) {
		return patientSer.checkForNewUser(patient);
	}
	//sign in patient
	@PostMapping("/login-patient")
	public ResponseEntity<Patients> loginPatient(@RequestBody Patients patient) {
		return patientSer.checkForLogin(patient);
	}
	
//	@GetMapping("/all-services")
//	public CumulativeServices totServices() {
//		return patientSer.totServices();
//	}
//	@GetMapping("/all-services/{city}")
//	public CumulativeServices totServicesByCity(@PathVariable String city) {
//		return patientSer.totServicesByCity(city);
//	}
//	@GetMapping("/details/{city}")
//	public List<HospitalsDto> getAllHospitalsByCity(@PathVariable String city){
//		return patientSer.getAllHospitals(city);
//	}
//	@GetMapping("/all-hospitals/{city}")
//	public List<HospitalsDto> getALLHospitals(@PathVariable String city){
//		return patientSer.getHospitals(city);
//	}
//	//get details of patient
//	@GetMapping("/patient-details/{id}")
//	public ResponseEntity<Patients> getDetailsOfPatient(@PathVariable Long id) {
//		return patientSer.getDetailsOfPatient(id);
//	}
	// Change Password
	@PutMapping("/password-patient/{id}")
    public ResponseEntity<Patients> changePassword(@PathVariable Long id, @RequestBody changePassword updatePatientPassword) throws Exception{     
		return patientSer.changePasswordToNewPassword(id, updatePatientPassword);
	}
	// Delete Patient
	@DeleteMapping("/delete-patient/{id}")
	public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id){
		return patientSer.deletePatient(id);
	}
	
	//frontend left-----
	//Beneficiary part for booking a service
	@PostMapping("/add-beneficiary/{id}")
	public ResponseEntity<List<Beneficiaries>> addNewBeneficiary(@RequestBody Beneficiaries user, @PathVariable Long id) {
		return patientSer.addNewBen(id, user);
	}
	//get beneficiary details
	@GetMapping("/get-beneficiaries/{id}")
	public ResponseEntity<List<Beneficiaries>> getAllBeneficiaries(@PathVariable Long id){
		return patientSer.getAll(id);
	}
	//add order of beneficiary
	@PutMapping("/book-order/{id}")
	public ResponseEntity<Orders> addNewOrder(@PathVariable Long id, @RequestBody Orders order){
		return benSer.addNewOrder(id, order);
	}
	//reschedule booking date
	@PutMapping("reschedule-order/{id}/{date}")
	public ResponseEntity<Orders> rescheduleOrder(@PathVariable Long id, @PathVariable String date){
		return benSer.resOrder(id, date);
	}
	
	

}
