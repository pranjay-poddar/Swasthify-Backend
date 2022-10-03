package com.swasthify.GenericErrors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCodeMessages {
	/*
	 * 00 - success
	 * 01 - fail
	 */
	int code;
	String message;
}
