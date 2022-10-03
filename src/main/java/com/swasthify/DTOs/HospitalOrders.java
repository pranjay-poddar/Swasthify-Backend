package com.swasthify.DTOs;

import java.util.List;

import com.swasthify.Entities.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOrders {
	private String name;
	private String emailId;
	private int age;
	private long contactNo;
	private long id;
	private String date;
	private int vax1;
	private int vax2;
	private int isolationBeds;
	private int icuBeds;
	private int oxygenCylinders;
	private boolean isConfirm;
	private boolean reschedule; 
}
