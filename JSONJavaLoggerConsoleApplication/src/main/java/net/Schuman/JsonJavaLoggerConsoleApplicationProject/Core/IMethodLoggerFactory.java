package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

public interface IMethodLoggerFactory <T extends AutoCloseable>{
	public T createMethodLogger(String methodName);
}
