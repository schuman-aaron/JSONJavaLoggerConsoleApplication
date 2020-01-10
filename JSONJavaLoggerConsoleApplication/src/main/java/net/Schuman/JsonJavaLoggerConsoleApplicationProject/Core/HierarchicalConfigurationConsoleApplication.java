package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.Constants.*;

import java.nio.file.InvalidPathException;

import javax.naming.ConfigurationException;

public abstract class HierarchicalConfigurationConsoleApplication <T extends FileBasedConfiguration> implements IConsoleApplication {

	private FileBasedConfigurationBuilder<T> builder;
	private Parameters parameters;
	private T applicationConfiguration;
	private int returnCode = getSuccessCode();
	
	protected abstract void startActivity();
	public abstract int run(String[] args);
	protected abstract void initializeLogger();
	protected abstract void initializeBuilder();

	public HierarchicalConfigurationConsoleApplication() {
		super();
	}
	
	protected void initializeParameters() {
		parameters = new Parameters();
	}
	
	protected void configureBuilder(String[] args) throws ConfigurationException {
		if(args.length != 1)
		{
			returnCode = getInputArgumentErrorCode();
			throw new ConfigurationException("Unexpected number of input arguments was provided\n Expected: " + getInputArgumentExpectedValue() + "\nActual: " + args.length);
		}
		try {
			builder.configure(parameters.hierarchical().setFile(PathsProxy.get(SystemProxy.getProperty(getExecutionDirectory())).resolve(args[getInputArgumentConfigurationFileIndex()]).normalize().toFile()));
		} catch(InvalidPathException e) {
			returnCode = getInvalidPathErrorCode();
			throw e;
		} catch(UnsupportedOperationException e) {
			returnCode = getUnsupportedOperationErrorCode();
			throw e;
		} catch(SecurityException e) {
			returnCode = getSecurityErrorCode();
			throw e;
		} catch(IllegalArgumentException e) {
			returnCode = getIllegalArgumentErrorCode();
			throw e;
		} catch(NullPointerException e) {
			returnCode =  getNullPointerErrorCode();
			throw e;
		}
	}
	
	protected void initializeConfiguration() throws org.apache.commons.configuration2.ex.ConfigurationException {
		try {
			applicationConfiguration = builder.getConfiguration();
		} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
			returnCode = getConfigurationErrorCode();
			throw e;
		}
	}
	
	public FileBasedConfigurationBuilder<T> getBuilder() {
		return builder;
	}
	
	protected void setBuilder(FileBasedConfigurationBuilder<T> builder) {
		this.builder = builder;
	}
	
	public Parameters getParameters() {
		return parameters;
	}
	
	protected void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	public T getApplicationConfiguration() {
		return applicationConfiguration;
	}
	
	protected void setApplicationConfiguration(T applicationConfiguration) {
		this.applicationConfiguration = applicationConfiguration;
	}
	
	public int getReturnCode() {
		return returnCode;
	}
	
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	
}
