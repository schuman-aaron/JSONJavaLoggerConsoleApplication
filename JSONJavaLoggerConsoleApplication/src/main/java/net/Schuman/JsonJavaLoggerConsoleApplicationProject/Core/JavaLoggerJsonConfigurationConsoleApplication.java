package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * 
 * @author Aaron Schuman 
 * <p>
 * A JavaLoggerJsonConfigurationConsoleApplication object is used to instantiate and configure the {@link Logger}, {@link Handler}, 
 * {@link JavaMethodLoggerFactoryFactory}, and {@link JavaMethodLoggerFactory} objects; initialize the {@link FileBasedConfigurationBuilder} object;
 * and then execute the remaining application.
 * <p>
 * In the JSON configuration file, that was specified to the {@link #run(java.lang.String) run} method, the following names must exist with
 * their corresponding string values: <pre>
 *     Java Logger Name: Based on the Logger best practices the value is normally based on
 *     the package name or class name of the logged component, such as java.net or javax.swing.
 *     
 *     Log Full File Path: The relative or absolute path to the directory where the log files should be created.
 *     
 *     Log File Name: The value to be concatenated to the end of the log file name
 *     
 *     Log Date Time String Format: The format of the date time the output log file will use</pre>
 * <p>
 * The meaning of the return codes are specified in the {@link Constants} class.
 */

public abstract class JavaLoggerJsonConfigurationConsoleApplication extends HierarchicalConfigurationConsoleApplication<JSONConfiguration> {
	
	private Handler logHandler;
	private Logger logger;
	private JavaMethodLoggerFactoryFactory methodLoggerFactoryFactory;
	private JavaMethodLoggerFactory methodLoggerFactory;

	protected abstract void startActivity() throws Exception;

	/**
	 * Executes the application in its entirety. This method is intended to be the first method ran in the main method.
	 * @param args			A string array with only one element. 
	 * 						The value of the element is the relative or absolute path to the JSON configuration file.
	 * @return The return code
	 *  
	 */
	@Override
	public int run(String[] args) {
		try {
			initializeParameters();
			initializeBuilder();
			configureBuilder(args);
			initializeConfiguration();
			initializeHandler();
			configureHandler();
			initializeLogger();
			InitializeMethodLoggerFactoryFactory();
			configureLogger();
			startActivity();
		} catch (Exception e) {
			if(getReturnCode() == Constants.getSuccessCode())
				return Constants.getUnkownErrorCode();
		}
		return getReturnCode();
	}

	@Override
	protected void initializeLogger() {
		try {
			logger = LoggerProxy.getLogger(getApplicationConfiguration().getString(Constants.getLoggerName()));
		} catch (NullPointerException e) {
			setReturnCode(Constants.getNullPointerErrorCode());
			throw e;
		}
	}
	
	protected void configureLogger() {
		try {
			methodLoggerFactory = methodLoggerFactoryFactory.createMethodLoggerFactory(this.getClass().getName());
			logger.addHandler(logHandler);
			logger.setLevel(Level.FINER);
		} catch(SecurityException e) {
			setReturnCode(Constants.getSecurityErrorCode());
			throw e;
		}
	}

	@Override
	protected void initializeBuilder() {
		setBuilder(new FileBasedConfigurationBuilder<JSONConfiguration>(JSONConfiguration.class));
	}

	
	protected void initializeHandler() throws Exception {
		try {
			logHandler = new FileHandler(
					getApplicationConfiguration().getString(Constants.getLogFullFilePath()) + 
					LocalDateTimeProxy.now().format(DateTimeFormatterProxy.ofPattern(getApplicationConfiguration().getString(Constants.getLogFileDateTimeStringFormat()))) + 
					"-" + getApplicationConfiguration().getString(Constants.getLogFileName()) ,true
					);
		} catch (SecurityException e) {
			setReturnCode(Constants.getSecurityErrorCode());
			throw e;
		} catch (DateTimeException e) {
			setReturnCode(Constants.getDateTimeErrorCode());
			throw e;
		}catch (IllegalArgumentException e) {
			setReturnCode(Constants.getIllegalArgumentErrorCode());
			throw e;
		} catch (RuntimeException e) {
			setReturnCode(Constants.getIoErrorCode());
			throw e;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	protected void configureHandler() {
		try {
		logHandler.setFormatter(new BaseFormatter());
		} catch(SecurityException e) {
			setReturnCode(Constants.getSecurityErrorCode());
			throw e;
		}
	}
	
	public void InitializeMethodLoggerFactoryFactory() {
		methodLoggerFactoryFactory = new JavaMethodLoggerFactoryFactory(logger);
	}

	public Handler getLogHandler() {
		return logHandler;
	}

	protected void setLogHandler(Handler logHandler) {
		this.logHandler = logHandler;
	}

	public Logger getLogger() {
		return logger;
	}

	protected void setLogger(Logger logger) {
		this.logger = logger;
	}

	public JavaMethodLoggerFactoryFactory getMethodLoggerFactoryFactory() {
		return methodLoggerFactoryFactory;
	}

	protected void setMethodLoggerFactoryFactory(JavaMethodLoggerFactoryFactory methodLoggerFactoryFactory) {
		this.methodLoggerFactoryFactory = methodLoggerFactoryFactory;
	}

	public JavaMethodLoggerFactory getMethodLoggerFactory() {
		return methodLoggerFactory;
	}

	protected void setMethodLoggerFactory(JavaMethodLoggerFactory methodLoggerFactory) {
		this.methodLoggerFactory = methodLoggerFactory;
	}
}
