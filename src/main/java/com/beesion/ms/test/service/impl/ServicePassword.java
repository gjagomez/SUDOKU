package com.beesion.ms.test.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;

import com.beesion.ms.test.dto.PasswordDto;
import com.beesion.ms.test.dto.PolicyDomainDto;
import com.beesion.ms.test.service.IServicePassword;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicePassword implements IServicePassword {

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String SPECIAL_CHAR = "!#.";

	private static SecureRandom random = new SecureRandom();

	@Override
	public PasswordDto generatePassword(PolicyDomainDto policy) {
		String pass = generate(policy);

		PasswordDto password = new PasswordDto();
		password.setPassword(pass);
		password.setCreatedTimestamp(LocalDate.now().toString());

		return password;
	}

	private static String generate(PolicyDomainDto policy) {

		if (policy.getMinLength() < 4)
			throw new IllegalArgumentException("La longitud de la contraseña debe ser al menos 4 caracteres.");

		StringBuilder password = new StringBuilder(policy.getMinLength());
		String passwordAllowBase = "";

		if (policy.getRestrictMinLowerCaseLetters()) {
			password.append(randomChars(CHAR_LOWER, policy.getMinLowerCaseLetters()));
			passwordAllowBase = passwordAllowBase + CHAR_LOWER;
		}
		if (policy.getRestrictMinUpperCaseLetters()) {
			password.append(randomChars(CHAR_UPPER, policy.getMinUpperCaseLetters()));
			passwordAllowBase = passwordAllowBase + CHAR_UPPER;
		}
		if (policy.getRestrictMinDigits()) {
			password.append(randomChars(NUMBER, policy.getMinDigits()));
			passwordAllowBase = passwordAllowBase + NUMBER;
		}
		if (policy.getRestrictMinNonAlphanumericCharacters()) {
			password.append(randomChars(SPECIAL_CHAR, policy.getMinNonAlphanumericCharacters()));
			passwordAllowBase = passwordAllowBase + SPECIAL_CHAR;
		}

		if (password.length() == 0)
			throw new IllegalArgumentException("Cadena vacia");

		// Genera el resto de la contraseña
		for (int i = password.length(); i < policy.getMinLength(); i++) {
			password.append(randomChar(passwordAllowBase));
		}

		// Mezcla la contraseña
		for (int i = 0; i < policy.getMinLength(); i++) {
			int randomIndex = random.nextInt(policy.getMinLength());
			char temp = password.charAt(i);
			password.setCharAt(i, password.charAt(randomIndex));
			password.setCharAt(randomIndex, temp);
		}

		return password.toString();
	}

	private static String randomChars(String source, int count) {
		StringBuilder result = new StringBuilder(count);
		for (int i = 0; i < count; i++) {
			result.append(randomChar(source));
		}
		return result.toString();
	}

	private static char randomChar(String source) {
		int randomIndex = random.nextInt(source.length());
		return source.charAt(randomIndex);
	}

}
