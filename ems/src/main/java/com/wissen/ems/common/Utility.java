package com.wissen.ems.common;

public final class Utility {
	private Utility() {
	}

	public static String assertNonNullEmptyOrBlank(String input, String exceptionMessage) {
		if (input == null || input.isEmpty() || input.isBlank()) {
			throw new IllegalArgumentException(exceptionMessage);
		}

		return input;
	}
}
