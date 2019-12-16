package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

public class Constants {
	private static final String FORMATTING_CHARACTER = "    \t";
	
	private static final String DATETIME_STRING_FORMAT = "uuuu-MM-dd HH:mm:ss.SSSS";
	
	private static final String EXECUTION_DIRECTORY = "user.dir";
	
	
	private static final int INPUT_ARGUMENT_EXPECTED_VALUE = 1;
	
	private static final int INPUT_ARGUMENT_CONFIGURATION_FILE_INDEX = 0;
	
	
	private static final int INPUT_ARGUMENT_ERROR_CODE = 10;

	public static String getFormattingCharacter() {
		return FORMATTING_CHARACTER;
	}

	public static String getDatetimeStringFormat() {
		return DATETIME_STRING_FORMAT;
	}

	public static String getExecutionDirectory() {
		return EXECUTION_DIRECTORY;
	}

	public static int getInputArgumentErrorCode() {
		return INPUT_ARGUMENT_ERROR_CODE;
	}

	public static int getInputArgumentExpectedValue() {
		return INPUT_ARGUMENT_EXPECTED_VALUE;
	}

	public static int getInputArgumentConfigurationFileIndex() {
		return INPUT_ARGUMENT_CONFIGURATION_FILE_INDEX;
	}
}
