package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

public interface IMethodLoggerFactoryFactory <T extends IMethodLoggerFactory<S>, S extends AutoCloseable>{
	public T createMethodLoggerFactory(String className);
}
