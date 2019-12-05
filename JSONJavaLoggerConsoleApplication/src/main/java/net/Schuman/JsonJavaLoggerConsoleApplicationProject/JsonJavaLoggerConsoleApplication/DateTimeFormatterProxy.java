package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterProxy {

	private DateTimeFormatterProxy()
	{
		throw new AssertionError();
	}
	
	public static DateTimeFormatter ofPattern(String pattern)
	{
		return DateTimeFormatter.ofPattern(pattern);
	}
}
