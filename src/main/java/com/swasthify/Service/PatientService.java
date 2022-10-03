package com.swasthify.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swasthify.DTOs.CumulativeServices;
import com.swasthify.DTOs.HospitalsDto;
import com.swasthify.DTOs.changePassword;
import com.swasthify.Entities.Beneficiaries;
import com.swasthify.Entities.Hospitals;
import com.swasthify.Entities.Patients;
import com.swasthify.Exception.ResourceNotFound;
import com.swasthify.Repository.HospitalRepo;
import com.swasthify.Repository.PatientRepo;

@Service
public class PatientService {
	@Autowired
	private PatientRepo patientRep;
	@Autowired
	private HospitalRepo hosRepo;
	@Autowired 
	private BeneficiaryService benSer;
	
	//register user
	public ResponseEntity<Patients> checkForNewUser(Patients patient){
		String emailId = patient.getEmailId();
		Patients newPatient = patient;
		String pass = patient.getPass();
		String conPass = patient.getConPass();
		if(emailId != null && !" ".equals(emailId)) {
			Patients obj = fetchByEmailId(emailId);
			if(obj != null) {
				newPatient.setCode(01);
				newPatient.setMessage("User already exists");
			}
			else if(pass.compareTo(conPass) != 0) {
				newPatient.setCode(01);
				newPatient.setMessage("Password and confirm password are not same");
			}
			else {
				newPatient = registerUser(newPatient);
				newPatient.setCode(00);
				newPatient.setMessage("Patient successfully registered");
			}
		}
		else {
			newPatient.setCode(01);
			newPatient.setMessage("Enter a valid email id");
		}
		return ResponseEntity.ok(newPatient);
	}
	//save correct user
	public Patients registerUser(Patients patient) {
		return patientRep.save(patient);
	}
	//check for login of user
	public ResponseEntity<Patients> checkForLogin(Patients patient){
		String emailId = patient.getEmailId();
		String pass = patient.getPass();
		Patients newPatient = fetchByEmailId(emailId);;
		if(emailId != null && !" ".equals(emailId)) {
			if(newPatient == null) {
				newPatient = new Patients();
				newPatient.setCode(01);
				newPatient.setMessage("User with email id doesn't exist");
			}
			else if(pass == null || pass.compareTo(newPatient.getPass()) != 0) {
				newPatient.setCode(01);
				newPatient.setMessage("Enter a valid password");
			}
			else {
				newPatient.setCode(00);
				newPatient.setMessage("Patient successfully logged in!");
			}
		}
		else {
			newPatient.setCode(01);
			newPatient.setMessage("Enter a valid Email id");
		}
		
		return ResponseEntity.ok(newPatient);
	}
	
	
	public Patients fetchByEmailId(String emailId) {
		return patientRep.findByEmailId(emailId);
	}

	//change password
	public ResponseEntity<Patients> changePasswordToNewPassword(Long id, changePassword updatePatientPassword) {
		boolean check = patientRep.existsById(id);
		Patients patient = new Patients();
		if(check == false) {
			patient.setCode(01);
			patient.setMessage("Patient not found");
		}
		else {
			patient = patientRep.findById(id).orElseThrow(() -> new ResourceNotFound("Patient Not Found"));
			String old_Pass = patient.getConPass();
			String new_OldPass = updatePatientPassword.getCurrentPassword();
			String new_Pass = updatePatientPassword.getNewPassword();
			String new_ConPass = updatePatientPassword.getConfirmNewPassword();
			if(old_Pass.compareTo(new_OldPass) != 0) {
				patient.setCode(01);
				patient.setMessage("Old Password incorrect");
			}
			else if(new_Pass.compareTo(new_ConPass) != 0) {
				patient.setCode(01);
				patient.setMessage("Password and Confirm Password not same");
			}
			else {
				patient.setPass(updatePatientPassword.getNewPassword());
				patient.setConPass(updatePatientPassword.getNewPassword());
				patientRep.save(patient);
				patient.setCode(00);
				patient.setMessage("Password updated successfully!");
			}
		}
		return ResponseEntity.ok(patient);
	}
	//delete patient
	public ResponseEntity<Map<String, Boolean>> deletePatient(Long id) {
		Patients obj = patientRep.findById(id).orElseThrow(()-> new ResourceNotFound("Patient does not exist"));
		patientRep.delete(obj);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	public CumulativeServices totServices() {
//		List<CumulativeServices> allSer = new ArrayList<>();
//		allSer = hosRepo.getAllServices();
//		CumulativeServices obj = new CumulativeServices();
//		for(CumulativeServices o : allSer) {
//			int icuBeds = o.getIcuBeds();
//			int isolationBeds = o.getIsolationBeds();
//			int oxygenCyl = o.getOxygenCylinders();
//			int vac1 = o.getVaccine1();
//			int vac2 = o.getVaccine2();
//			obj.setIcuBeds(obj.getIcuBeds()+icuBeds);
//			obj.setIsolationBeds(obj.getIsolationBeds() + isolationBeds);
//			obj.setOxygenCylinders(obj.getOxygenCylinders() + oxygenCyl);
//			obj.setVaccine1(obj.getVaccine1() + vac1);
//			obj.setVaccine2(obj.getVaccine2() + vac2);
//		}
//		return obj;
//	}
//	public List<HospitalsDto> getAllHospitals(String city){
//		return hosRepo.fetchAllHospitals(city);
//	}
//	public CumulativeServices totServicesByCity(String city) {
//		List<CumulativeServices> allSer = new ArrayList<>();
//		allSer = hosRepo.getAllServicesByCity(city);
//		CumulativeServices obj = new CumulativeServices();
//		for(CumulativeServices o : allSer) {
//			int icuBeds = o.getIcuBeds();
//			int isolationBeds = o.getIsolationBeds();
//			int oxygenCyl = o.getOxygenCylinders();
//			int vac1 = o.getVaccine1();
//			int vac2 = o.getVaccine2();
//			obj.setIcuBeds(obj.getIcuBeds()+icuBeds);
//			obj.setIsolationBeds(obj.getIsolationBeds() + isolationBeds);
//			obj.setOxygenCylinders(obj.getOxygenCylinders() + oxygenCyl);
//			obj.setVaccine1(obj.getVaccine1() + vac1);
//			obj.setVaccine2(obj.getVaccine2() + vac2);
//		}
//		return obj;
//	}
//	public List<HospitalsDto> getHospitals(String city) {
//		return hosRepo.getAllHospitalsByService(city);
//	}
//	public ResponseEntity<Patients> getDetailsOfPatient(Long id) {
//		// TODO Auto-generated method stub
//		boolean check = patientRep.existsById(id);
//		Patients obj = new Patients();
//		if(check == false) {
//			obj.setCode(01);
//			obj.setMessage("User not found");
//		}
//		else {
//			obj = patientRep.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
//			obj.setCode(00);
//			obj.setMessage("User details found");
//		}
//		return ResponseEntity.ok(obj);
//	}	
//	
	//beneficiary part--------------

	//add a beneficiary--
	public ResponseEntity<List<Beneficiaries>> addNewBen(Long id, Beneficiaries user) {
		Patients obj = patientRep.findById(id).orElseThrow(() -> new ResourceNotFound("patient not found"));
		List<Beneficiaries> list = obj.getBeneficiary();
		if(list.size() < 5) {
			list.add(user);
			patientRep.save(obj);
		}
		return ResponseEntity.ok(list);
	}
	//get all beneficiaries
	public ResponseEntity<List<Beneficiaries>> getAll(Long id){
		List<Beneficiaries> list = patientRep.findById(id).orElseThrow(() -> new ResourceNotFound("patient not found")).getBeneficiary();
		if(list == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
		}
		else {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		}
	}	
}
