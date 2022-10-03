package com.swasthify.DTOs;
import com.swasthify.GenericErrors.ErrorCodeMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class changePassword extends ErrorCodeMessages{
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;
}
