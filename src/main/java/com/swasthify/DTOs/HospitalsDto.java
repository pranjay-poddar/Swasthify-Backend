package com.swasthify.DTOs;

import java.util.List;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HospitalsDto {
	
	private Long id;
	private String hospitalName;
	private String emailId;
	private String city;
	private String State;
	private long contact;
	private int icuBeds;
	private int isolationBeds;
	private int oxygenCylinders;
	private int vaccine1;
	private int vaccine2;

}
