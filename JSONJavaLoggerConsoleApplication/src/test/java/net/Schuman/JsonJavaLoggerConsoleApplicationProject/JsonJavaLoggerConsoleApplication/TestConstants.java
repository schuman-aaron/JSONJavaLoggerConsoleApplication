package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

import java.time.format.DateTimeFormatter;

public class TestConstants {
	private static final String CONSTANT_DATETIME = "2019-01-01 00:00:00.0000";
	
	private static final String DATETIME_STRING_FORMAT = "uuuu-MM-dd HH:mm:ss.SSSS";

	private static final String DEFAULT_STRING = "Default String";
	
	private static final int DEFAULT_INT = 2;
	
	private static final String FORMATTING_CHARACTER = "    \t";
	
	public static String getConstantDatetime() {
		return CONSTANT_DATETIME;
	}

	public static DateTimeFormatter getDateTimeFormat() {
		return DateTimeFormatter.ofPattern(DATETIME_STRING_FORMAT);
	}

	public static String getDefaultString() {
		return DEFAULT_STRING;
	}

	public static int getDefaultInt() {
		return DEFAULT_INT;
	}

	public static String getFormattingCharacter() {
		return FORMATTING_CHARACTER;
	}

	public static String getDateTimeStringFormat() {
		return DATETIME_STRING_FORMAT;
	}
}
