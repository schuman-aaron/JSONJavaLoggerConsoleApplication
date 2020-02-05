package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

/**
 * 
 * @author Aaron Schuman
 * 
 * A method logger, which just uses the logger to log the entry and exit of the current method. 
 * The method logger should be instantiated as a parameter of the try block e.g.
 * <pre><code>
 * try(IMethodLogger methodLogger = methodLoggerFactory.createMethodLoggerAndLogEntry();) {
 * ...
 * }
 * </code></pre>
 *
 */
public interface IMethodLogger extends AutoCloseable {
	public void logEntry();
}
