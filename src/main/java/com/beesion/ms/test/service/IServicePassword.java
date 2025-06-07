package com.beesion.ms.test.service;

import com.beesion.ms.test.dto.PasswordDto;
import com.beesion.ms.test.dto.PolicyDomainDto;

public interface IServicePassword {
	
	PasswordDto generatePassword(PolicyDomainDto policy);

}
