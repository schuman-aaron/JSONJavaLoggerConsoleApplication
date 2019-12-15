package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public abstract class HierarchicalConfigurationConsoleApplication <T extends FileBasedConfiguration> implements IConsoleApplication {

	private FileBasedConfigurationBuilder<T> builder;
	private Parameters parameters;
	private T applicationConfiguration;
	private int returnCode;
	
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
	
	
	protected void configureBuilder() {
		
	}
	
	protected void initializeConfiguration() {
		
	}
	
}
