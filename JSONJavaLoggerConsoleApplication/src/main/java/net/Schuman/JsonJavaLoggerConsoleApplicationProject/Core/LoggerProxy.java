package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.util.logging.Logger;

public class LoggerProxy {
	public static Logger getLogger(String name) {
		return Logger.getLogger(name);
	}
}
