package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import net.schuman.JavaMethodLoggerFactory.JavaMethodLoggerFactory.IMethodLogger;
import net.schuman.JavaMethodLoggerFactory.JavaMethodLoggerFactory.IMethodLoggerFactory;

/**
 * 
 * @author Aaron Schuman
 *
 * @param <T> A method logger factory class
 * @param <S> A method logger class
 * <p>
 * An interface for method logger factory factories regardless of the type of logger they use. 
 * Only one method logger factory factory should be instantiated in each project.
 */

public interface IMethodLoggerFactoryFactory <T extends IMethodLoggerFactory<S>, S extends IMethodLogger>{
	public T createMethodLoggerFactory(String className);
}
