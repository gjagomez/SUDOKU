package com.beesion.ms.test.dto;


import lombok.Data;

@Data
public class PolicyDomainDto {
	private String rulesApplyTo;
	private Boolean allowWebAddExternalAuthenticationUsers;
	private Boolean restrictMinDigits;
	private Integer minDigits;
	private Boolean restrictMinUpperCaseLetters;
	private Integer minUpperCaseLetters;
	private Boolean restrictMinLowerCaseLetters;
	private Integer minLowerCaseLetters;
	private Boolean restrictMinNonAlphanumericCharacters;
	private Integer minNonAlphanumericCharacters;
	private Integer minLength;
}
