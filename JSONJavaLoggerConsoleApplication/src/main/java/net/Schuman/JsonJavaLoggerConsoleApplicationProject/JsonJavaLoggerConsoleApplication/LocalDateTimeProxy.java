package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

import java.time.LocalDateTime;

public class LocalDateTimeProxy {

		private LocalDateTimeProxy() {
			throw new AssertionError();
		}
		
		//TODO: Make this return LocalDateTimeProxy, add format, add getter/setter
		public static LocalDateTime now() {
			return LocalDateTime.now();
		}
}
