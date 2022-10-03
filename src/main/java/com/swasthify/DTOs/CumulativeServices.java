package com.swasthify.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CumulativeServices {

	private int icuBeds;
	private int isolationBeds;
	private int oxygenCylinders;
	private int vaccine1;
	private int vaccine2;
}
