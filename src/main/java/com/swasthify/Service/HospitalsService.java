package com.swasthify.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swasthify.DTOs.HospitalOrders;
import com.swasthify.DTOs.HospitalsDto;
import com.swasthify.DTOs.changePassword;
import com.swasthify.Entities.HospitalServices;
import com.swasthify.Entities.Hospitals;
import com.swasthify.Entities.Orders;
import com.swasthify.Exception.ResourceNotFound;
import com.swasthify.Repository.BeneficiariesRepo;
import com.swasthify.Repository.HospitalRepo;
import com.swasthify.Repository.HospitalServiceRepo;
import com.swasthify.Repository.OrdersRepo;

@Service
public class HospitalsService {
	@Autowired
	private HospitalRepo hospitalRepo;
	@Autowired
	private BeneficiariesRepo benRepo;
	@Autowired
	private OrdersRepo orderRep;
	@Autowired
	private HospitalServiceRepo serviceRepo;
	//fetch by email id
	public Hospitals fetchByEmailId(String email) {
		return hospitalRepo.findByemailId(email);
	}
	//save hospital
	public ResponseEntity<Hospitals> saveHospital(Hospitals hospital) {
		String emailId = hospital.getEmailId();
		String pass = hospital.getPass();
		String conPass = hospital.getConPass();
		Hospitals hosp = hospital;
		if(emailId != null && !" ".equals(emailId)) {
			Hospitals obj = fetchByEmailId(emailId);
			if(obj != null) {
				hosp.setCode(01);
				hosp.setMessage("Hospital with email id already exists");
			}
			else if(pass.compareTo(conPass) != 0) {
				hosp.setCode(01);
				hosp.setMessage("Password and confirm password doesn't match");
			}
			else {
				hosp.setCode(00);
				hosp.setMessage("Hospital successfully registered");
				hospitalRepo.save(hosp);
			}
		}
		return ResponseEntity.ok(hosp);
	}
	//login user
	public ResponseEntity<Hospitals> loginUser(Hospitals hospital){
		String emailId = hospital.getEmailId();
		String pass = hospital.getPass();
		Hospitals hosp = new Hospitals();
		if(fetchByEmailId(emailId) == null) {
			hosp.setCode(01);
			hosp.setMessage("hospital doesn't exist");
		}
		else if(pass.compareTo(fetchByEmailId(emailId).getPass()) != 0) {
			hosp.setCode(01);
			hosp.setMessage("Incorrect password");
		}
		else {
			hosp = fetchByEmailId(emailId);
			hosp.setCode(00);
			hosp.setMessage("Successfully logged in");
		}
		return ResponseEntity.ok(hosp);
	}
	//change password
	public ResponseEntity<Hospitals> changePasswordToNewPassword(Long id, changePassword updateHospitalPassword) {
		boolean check = hospitalRepo.existsById(id);
		Hospitals hosp = new Hospitals();
		if(check == false) {
			hosp.setCode(01);
			hosp.setMessage("Hospital not found");
		}
		else {
			hosp = hospitalRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Hospital Not Found"));
			String old_Pass = hosp.getConPass();
			String new_OldPass = updateHospitalPassword.getCurrentPassword();
			String new_Pass = updateHospitalPassword.getNewPassword();
			String new_ConPass = updateHospitalPassword.getConfirmNewPassword();
			if(old_Pass.compareTo(new_OldPass) != 0) {
				hosp.setCode(01);
				hosp.setMessage("Old Password incorrect");
			}
			else if(new_Pass.compareTo(new_ConPass) != 0) {
				hosp.setCode(01);
				hosp.setMessage("Password and Confirm Password not same");
			}
			else {
				hosp.setPass(updateHospitalPassword.getNewPassword());
				hosp.setConPass(updateHospitalPassword.getNewPassword());
				hosp.setCode(00);
				hosp.setMessage("Password updated successfully!");
			}
		}
		return ResponseEntity.ok(hosp);
	}
	//delete user--
	public ResponseEntity<Map<String, Boolean>> deleteHospital(Long id) {
		Hospitals obj = hospitalRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Hospital does not exist"));
		hospitalRepo.delete(obj);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	//get details by id
	public ResponseEntity<Hospitals> getDetails(Long id) {
		Hospitals obj = hospitalRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Hospital not found"));
		return ResponseEntity.ok(obj);
	}
	//add a new service in hospital
	public ResponseEntity<HospitalServices> addNewService(Long id, HospitalServices newService) {
		boolean check = hospitalRepo.existsById(id);
		if(check == false) {
			newService.setCode(01);
			newService.setMessage("Hospital with this id doesn't exist");
		}
		else {
			Hospitals hospital = hospitalRepo.findById(id).orElseThrow(() -> new ResourceNotFound("hospital with id doesn't exist"));
			List<HospitalServices> list = hospital.getList();
		    list.add(newService);
		    newService.setCode(00);
		    newService.setMessage("Service successfully added");
		    hospitalRepo.save(hospital);
		}
		return ResponseEntity.ok(newService);
	}
	//get service list
	public ResponseEntity<List<HospitalServices>> getServiceListByHospId(Long id) {
		Hospitals hosp = hospitalRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Hospital not found"));
		List<HospitalServices> list = hosp.getList();
		return ResponseEntity.ok(list);
	}
	//update hospital service by service id
	public ResponseEntity<HospitalServices> updateService(Long id, HospitalServices serviceObj) {
		HospitalServices service = serviceRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Service doesn't exist"));
		if(service == null) {
			service.setCode(01);
			service.setMessage("Mentioned service doesn't exist");
		}
		else {
			service.setQty(serviceObj.getQty());
			service.setPrice(serviceObj.getPrice());
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm").format(Calendar.getInstance().getTime());
			service.setTimeStamp(timeStamp);
			serviceRepo.save(service);
			service.setCode(00);
			service.setMessage("Service updated successfully");
		}
		return ResponseEntity.ok(service);
	}
	
	
}
