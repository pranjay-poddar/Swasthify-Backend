package com.swasthify.Entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
@Table(name = "ServiceList")
@Entity
public class HospitalServices extends ErrorCodeMessages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String serviceName;
	private int qty;
	private double price;
	private String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm").format(Calendar.getInstance().getTime());

}
