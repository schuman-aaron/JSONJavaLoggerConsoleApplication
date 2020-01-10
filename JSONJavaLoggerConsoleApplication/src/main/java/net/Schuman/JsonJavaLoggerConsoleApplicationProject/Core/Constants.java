package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

public class Constants {
	private static final String FORMATTING_CHARACTER = "    \t";
	
	private static final String DATETIME_STRING_FORMAT2 = "uuuu-MM-dd-HH.mm.ss.SSSS";
	
	private static final String EXECUTION_DIRECTORY = "user.dir";
	
	private static final String LOG_FULL_FILE_PATH = "Log Full File Path";
	
	private static final String LOG_FILE_NAME = "Log File Name";
	
	private static final String LOGGER_NAME = "Java Logger";
	
	
	private static final int INPUT_ARGUMENT_EXPECTED_VALUE = 1;
	
	private static final int INPUT_ARGUMENT_CONFIGURATION_FILE_INDEX = 0;
	
	// [start] Error Codes
	
	private static final int SUCCESS_CODE = 0;
	
	private static final int INPUT_ARGUMENT_ERROR_CODE = 10;
	
	private static final int INVALID_PATH_ERROR_CODE = 20;
	
	private static final int UNSUPPORTED_OPERATION_ERROR_CODE = 30;
	
	private static final int SECURITY_ERROR_CODE = 40;
	
	private static final int NULL_POINTER_ERROR_CODE = 50;
	
	private static final int ILLEGAL_ARGUMENT_ERROR_CODE = 60;
	
	private static final int CONFIGURATION_ERROR_CODE = 70;
	
	private static final int IO_ERROR_CODE = 80;
	
	private static final int DATE_TIME_ERROR_CODE = 90;
	
	// [end] Error Codes

	public static String getFormattingCharacter() {
		return FORMATTING_CHARACTER;
	}

	public static String getDatetimeStringFormat() {
		return DATETIME_STRING_FORMAT2;
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

	public static int getConfigurationErrorCode() {
		return CONFIGURATION_ERROR_CODE;
	}

	public static int getInvalidPathErrorCode() {
		return INVALID_PATH_ERROR_CODE;
	}

	public static int getUnsupportedOperationErrorCode() {
		return UNSUPPORTED_OPERATION_ERROR_CODE;
	}

	public static int getSecurityErrorCode() {
		return SECURITY_ERROR_CODE;
	}

	public static int getIllegalArgumentErrorCode() {
		return ILLEGAL_ARGUMENT_ERROR_CODE;
	}

	public static int getNullPointerErrorCode() {
		return NULL_POINTER_ERROR_CODE;
	}

	public static int getSuccessCode() {
		return SUCCESS_CODE;
	}

	public static String getLogFullFilePath() {
		return LOG_FULL_FILE_PATH;
	}

	public static String getLogFileName() {
		return LOG_FILE_NAME;
	}

	public static int getIoErrorCode() {
		return IO_ERROR_CODE;
	}

	public static int getDateTimeErrorCode() {
		return DATE_TIME_ERROR_CODE;
	}

	public static String getLoggerName() {
		return LOGGER_NAME;
	}
}
