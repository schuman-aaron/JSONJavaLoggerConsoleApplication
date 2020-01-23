package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.util.logging.Logger;

/**
 * 
 * @author Aaron Schuman
 * <p>
 * A factory that creates {@link JavaMethodLoggerFactory Java method logger factories}.
 *
 */
public class JavaMethodLoggerFactoryFactory implements IMethodLoggerFactoryFactory<JavaMethodLoggerFactory, JavaMethodLogger> {

	private Logger logger;
	
	public JavaMethodLoggerFactoryFactory(Logger logger) {
		super();
		this.logger = logger;
	}

	/**
	 * Creates a method logger factory.
	 */
	public JavaMethodLoggerFactory createMethodLoggerFactory(String className) {
		return new JavaMethodLoggerFactory(logger, className);
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
