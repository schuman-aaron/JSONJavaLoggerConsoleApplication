package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Aaron Schuman
 * <p>
 * A Java method logger object uses the {@link Logger Java logger} to log the entry and exit of a method.
 *
 */

public class JavaMethodLogger implements AutoCloseable {

	private Logger logger;
	private String methodName;
	private String className;
	
	public JavaMethodLogger(Logger newLogger, String newClassName, String newMethodName) {
		super();
		logger = newLogger;
		methodName = newMethodName;
		className = newClassName;
	}
	
	/**
	 * Logs that the application has exited the method 
	 * @throws Exception
	 */
	public void close() throws Exception {
		logger.exiting(className, methodName);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Logs that the application has entered the method
	 */
	public void logEntry() {
		logger.entering(className, methodName);
	}

}
