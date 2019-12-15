package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.MockAwareVerificationMode;

import net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.JavaMethodLogger;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMethodLoggerTest {

		@InjectMocks
		private JavaMethodLogger testMethodLogger;
		
		@Mock
		private Logger mockLogger;
		
		private String className;
		private String methodName;
		
		@Before
		public void setUp() {
			mockLogger = mock(Logger.class);
			className = getConstantClassName();
			methodName = getConstantMethodName();
			testMethodLogger = new JavaMethodLogger(mockLogger, className, methodName);
			
			MockitoAnnotations.initMocks(testMethodLogger);
		}
		
		@Test
		public void testlogEntrySuccess() {
			testMethodLogger.logEntry();
			verify(mockLogger,times(1)).entering(className, methodName);
		}
		
		@Test
		public void testCloseSuccess() {
			try {
				testMethodLogger.close();
			} catch (Exception e) {
				throw new AssertionError(e);
			}
			verify(mockLogger, times(1)).exiting(className, methodName);
		}
}
