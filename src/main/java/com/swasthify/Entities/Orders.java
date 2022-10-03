package com.swasthify.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.swasthify.GenericErrors.ErrorCodeMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders extends ErrorCodeMessages{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String date;
	private int vax1;
	private int vax2;
	private int isolationBeds;
	private int icuBeds;
	private int oxygenCylinders;
	private boolean isConfirm;
	private boolean reschedule;
	private String hospEmailId;

}
