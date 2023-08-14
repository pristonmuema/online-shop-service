package com.pristonit.utils;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeneratorUtil {

	private GeneratorUtil() {
	}

	private static final java.util.Random random;
	private static final HexUtils hexUtils = new HexUtils();

	static {
		try {
			random = java.security.SecureRandom.getInstanceStrong();
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Generates a unique UUID string
	 *
	 * @return the generated UUID
	 */
	public static String generateCode(Integer length) {
		if (length == null) {
			length = 16;
		}
		java.util.UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString()
		           .replaceAll("-", "")
		           .substring(0, length);
	}

	/**
	 * Generates a unique UUID string
	 *
	 * @return the generated UUID
	 */
	public static String generateOtp(Integer length) {
		if (length == null) {
			length = 4;
		}
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(random.nextInt(10));
		}
		return builder.toString();
	}

	public static String generate(Integer length) {
		String x = generateRandomAlphabetString(length);
		String y = generateRandomDigits(length);
		return x + hexUtils.toHex(java.time.LocalDateTime.now().getDayOfYear()) + "-" + y;
	}

	/**
	 * Generate random Alphabet String of length 4
	 *
	 * @return The generated String.
	 */
	public static String generateRandomAlphabetString(Integer length) {
		if (length == null) {
			length = 4;
		}
		boolean useLetters = true;
		boolean useNumbers = false;

		return org.apache.commons.lang3.RandomStringUtils.random(length, useLetters, useNumbers)
		                                                 .toUpperCase();
	}

	/**
	 * Generates a 4 digit code.
	 *
	 * @return Returns the generated code
	 */
	public static String generateRandomDigits(Integer x) {
		if (x == null) {
			x = 4;
		}

		java.util.Random random = new java.util.Random();

		return String.format("%0" + x + "d", random.nextInt(10000));
	}

	// Should not pass 10
	public static String generateProductId() {
		return "P-" + generateCode(8);
	}
}