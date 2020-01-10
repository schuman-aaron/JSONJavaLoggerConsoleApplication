package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;

public abstract class JavaLoggerJsonConfigurationConsoleApplication extends HierarchicalConfigurationConsoleApplication<JSONConfiguration> {
	
	private Handler logHandler;
	private Logger logger;

	protected abstract void startActivity();

	@Override
	public int run(String[] args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void initializeLogger() {
		logger = LoggerProxy.getLogger(Constants.getLoggerName());
	}

	@Override
	protected void initializeBuilder() {
		setBuilder(new FileBasedConfigurationBuilder<JSONConfiguration>(JSONConfiguration.class));
	}

	
	protected void initializeHandler() throws Exception {
		try {
			logHandler = new FileHandler(getApplicationConfiguration().getString(Constants.getLogFullFilePath()) + LocalDateTimeProxy.now().format(DateTimeFormatterProxy.ofPattern(Constants.getDatetimeStringFormat())) + "-" + getApplicationConfiguration().getString(Constants.getLogFileName()) ,true);
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
}
