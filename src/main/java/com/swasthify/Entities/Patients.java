package com.swasthify.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.swasthify.GenericErrors.ErrorCodeMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patients extends ErrorCodeMessages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String emailId;
	private String contact;
	private String pass;
	private String conPass;
	
	@OneToMany(targetEntity = Beneficiaries.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "AccountUserId", referencedColumnName = "id")
	private List<Beneficiaries> beneficiary;

}
