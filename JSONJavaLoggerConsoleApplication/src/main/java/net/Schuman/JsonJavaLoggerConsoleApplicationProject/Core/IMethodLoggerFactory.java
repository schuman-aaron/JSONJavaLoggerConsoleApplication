package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

/**
 * 
 * @author Aaron Schuman
 *
 * @param <T> A method logger class
 * 
 * An interface for method logger factories regardless of the type of logger they use. A new method logger factory should be instantiated for each class.
 */
public interface IMethodLoggerFactory <T extends IMethodLogger>{
	public T createMethodLogger(String methodName);
	public T createMethodLoggerAndLogEntry(String methodName);
}
