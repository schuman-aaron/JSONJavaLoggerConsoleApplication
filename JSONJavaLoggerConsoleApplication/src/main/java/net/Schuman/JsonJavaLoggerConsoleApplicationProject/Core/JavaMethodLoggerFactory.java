package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.util.logging.Logger;

/**
 * 
 * @author Aaron Schuman
 * 
 * A factory that creates {@link JavaMethodLogger Java method loggers}.
 *
 */
public class JavaMethodLoggerFactory implements IMethodLoggerFactory<JavaMethodLogger> {

	private Logger logger;
	private String className;
	
	public JavaMethodLoggerFactory(Logger logger, String className) {
		super();
		this.logger = logger;
		this.className = className;
	}

	/**
	 * Creates a method logger
	 */
	public JavaMethodLogger createMethodLogger(String methodName) {
		return new JavaMethodLogger(logger, className, methodName);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
