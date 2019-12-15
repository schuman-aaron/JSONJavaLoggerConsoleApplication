package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.util.logging.Logger;

public class JavaMethodLoggerFactoryFactory implements IMethodLoggerFactoryFactory<JavaMethodLoggerFactory, JavaMethodLogger> {

	private Logger logger;
	
	public JavaMethodLoggerFactoryFactory(Logger logger) {
		super();
		this.logger = logger;
	}

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
