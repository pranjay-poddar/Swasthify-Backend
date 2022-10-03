package com.swasthify.Entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sun.istack.NotNull;
import com.swasthify.GenericErrors.ErrorCodeMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="hospitals")
public class Hospitals extends ErrorCodeMessages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotNull
	private String hospitalName;
	private String emailId;
	private String city;
	private String State;
	private long contact;
	private String pass;
	private String conPass;
	
	@OneToMany(targetEntity = HospitalServices.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "serviceId", referencedColumnName = "id")
	private List<HospitalServices> list;
}
