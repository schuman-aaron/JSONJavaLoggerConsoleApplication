package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeProxy {

	private static LocalDateTime proxy;

	private LocalDateTimeProxy() {	}
	
	//TODO: Make this return LocalDateTimeProxy, add format, add getter/setter
	@SuppressWarnings("static-access")
	public static LocalDateTimeProxy now() {
		proxy = proxy.now();
		return new LocalDateTimeProxy();
	}
	
	public static String format(DateTimeFormatter formatter) {
		return proxy.format(formatter);
	}
}
