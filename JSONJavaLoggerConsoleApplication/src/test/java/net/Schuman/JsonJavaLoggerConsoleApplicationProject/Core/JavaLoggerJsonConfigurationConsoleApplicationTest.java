package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.internal.mockcreation.RuntimeExceptionProxy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.BDDMockito;
import org.mockito.BDDMockito.*;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.lang3.CharSequenceUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocalDateTimeProxy.class, DateTimeFormatterProxy.class, LoggerProxy.class})
public class JavaLoggerJsonConfigurationConsoleApplicationTest {

		private JavaLoggerJsonConfigurationConsoleApplication testApplication;
		
		@Mock
		private FileBasedConfigurationBuilder<FileBasedConfiguration> mockBuilder;
		
		@Mock
		private JSONConfiguration mockJsonConfiguration;
		
		@Mock
		private Handler mockLogHandler;

		@Mock
		private Logger mockLogger;
		
		@Mock
		private LoggerProxy mockProxyLogger;
		
		@Mock
		private JavaMethodLoggerFactoryFactory mockMethodLoggerFactoryFactory;
		
		@Mock
		private JavaMethodLoggerFactory mockMethodLoggerFactory;
		
		@Before
		public void setUp() {
			mockJsonConfiguration = mock(JSONConfiguration.class);
			mockLogHandler = mock(Handler.class);
			mockLogger = mock(Logger.class);
			mockProxyLogger = mock(LoggerProxy.class);
			mockMethodLoggerFactoryFactory = mock(JavaMethodLoggerFactoryFactory.class);
			mockMethodLoggerFactory = mock(JavaMethodLoggerFactory.class);
			PowerMockito.mockStatic(LocalDateTimeProxy.class, Mockito.CALLS_REAL_METHODS);
			PowerMockito.mockStatic(DateTimeFormatterProxy.class, Mockito.CALLS_REAL_METHODS);
			PowerMockito.mockStatic(LoggerProxy.class, Mockito.CALLS_REAL_METHODS);
			
			testApplication = mock(JavaLoggerJsonConfigurationConsoleApplication.class, Mockito.CALLS_REAL_METHODS);
			testApplication.setReturnCode(Constants.getSuccessCode());
			testApplication.setApplicationConfiguration(mockJsonConfiguration);
			testApplication.setReturnCode(Constants.getSuccessCode());
			testApplication.setLogHandler(mockLogHandler);
			testApplication.setLogger(mockLogger);
			testApplication.setMethodLoggerFactoryFactory(mockMethodLoggerFactoryFactory);
			testApplication.setMethodLoggerFactory(mockMethodLoggerFactory);
		}
		
		@Test
		public void testInitializeHandlerSuccessful() {
			try {
				defaultInitializeHandlerMockSettings();
				testApplication.initializeHandler();
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSuccessCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSuccessCode() == testApplication.getReturnCode());
				assertTrue("Unexpected encoding provided\nExpected: null\nActual:"+ testApplication.getLogHandler().getEncoding(), testApplication.getLogHandler().getEncoding() == null);
				assertTrue("Unexpected level provided\nExpected:" + Level.ALL + "\nActual:"+ testApplication.getLogHandler().getLevel(), testApplication.getLogHandler().getLevel() == Level.ALL);
				verify(mockJsonConfiguration, times(2)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
				DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			} catch (Exception e) {
				throw new AssertionError(e);
			}
		}
		
		@Test
		public void testInitializeHandlerFailureSecurity() {
			try {
				defaultInitializeHandlerMockSettings();
				when(mockJsonConfiguration.getString(anyString())).thenThrow(new SecurityException());
				testApplication.initializeHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch (SecurityException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSecurityErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSecurityErrorCode() == testApplication.getReturnCode());
				verify(mockJsonConfiguration, times(1)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
				DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			} 
		}
		
		@Test
		public void testInitializeHandlerFailureIO() {
			try {
				defaultInitializeHandlerMockSettings();
				when(mockJsonConfiguration.getString(anyString())).thenThrow(new RuntimeException());
				testApplication.initializeHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch (RuntimeException e) {
				//assertTrue("Wrong exception was thrown\nExpected: java.io.RuntimeException\nActual: " + e.getClass(), e.getClass() == RuntimeException.class);
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getIoErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getIoErrorCode() == testApplication.getReturnCode());
				verify(mockJsonConfiguration, times(1)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
				DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			} catch(Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		@Test
		public void testInitializeHandlerFailureIllegalArgumentFileHandler() {
			try {
				defaultInitializeHandlerMockSettings();
				when(mockJsonConfiguration.getString(anyString())).thenThrow(new IllegalArgumentException());
				testApplication.initializeHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch (IllegalArgumentException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getIllegalArgumentErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getIllegalArgumentErrorCode() == testApplication.getReturnCode());
				verify(mockJsonConfiguration, times(1)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
				DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			} catch(Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		@Test
		public void testInitializeHandlerFailureDateTime() {
			try {
				defaultInitializeHandlerMockSettings();
				PowerMockito.doThrow(new DateTimeException(getDefaultString())).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
				testApplication.initializeHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch (DateTimeException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getDateTimeErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getDateTimeErrorCode() == testApplication.getReturnCode());
				verify(mockJsonConfiguration, times(1)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
				DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
			} catch(Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		@Test
		public void testInitializeHandlerFailureIllegalArgumentOfPattern() {
			try {
				defaultInitializeHandlerMockSettings();
				PowerMockito.doThrow(new IllegalArgumentException()).when(DateTimeFormatterProxy.class, "ofPattern", anyString());
				testApplication.initializeHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch (IllegalArgumentException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getIllegalArgumentErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getIllegalArgumentErrorCode() == testApplication.getReturnCode());
				verify(mockJsonConfiguration, times(1)).getString(anyString());
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.now();
				//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
				LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			} catch(Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		public void defaultInitializeHandlerMockSettings() throws Exception {
			when(mockJsonConfiguration.getString(anyString())).thenReturn(getDefaultString());
			/*
			when(mockLogHandler.getEncoding()).thenReturn(getDefaultString());
			when(mockLogHandler.getFormatter()).thenReturn(null);
			when(mockLogHandler.getLevel()).thenReturn(Level.FINE);
			*/
			PowerMockito.doReturn(LocalDateTimeProxy.now()).when(LocalDateTimeProxy.class, "now");
			PowerMockito.doReturn(DateTimeFormatter.ofPattern(getDateTimeStringFormat())).when(DateTimeFormatterProxy.class, "ofPattern", anyString());
			PowerMockito.doReturn(getConstantDatetime()).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
		}
		
		@Test
		public void testSetHandlerSuccess() {
			testApplication.configureHandler();
			assertTrue("Unexpected return code provided\nExpected: " + Constants.getSuccessCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSuccessCode() == testApplication.getReturnCode());
			verify(mockLogHandler, times(1)).setFormatter(any(java.util.logging.Formatter.class));
		}
		
		@Test
		public void testSetHandlerFailureSecurityException() {
			doThrow(new SecurityException()).when(mockLogHandler).setFormatter(any(java.util.logging.Formatter.class));
			try {
				testApplication.configureHandler();
				throw new AssertionError("Unexpected successful execution");
			} catch(SecurityException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSecurityErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSecurityErrorCode() == testApplication.getReturnCode());
			}
		}
		
		@Test
		public void testInitializeLoggerSuccess() {
			try {
				PowerMockito.doReturn(mockLogger).when(LoggerProxy.class, "getLogger", anyString());
				testApplication.initializeLogger();
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSuccessCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSuccessCode() == testApplication.getReturnCode());
				//PowerMockito.verifyStatic(LoggerProxy.class, times(1));
				LoggerProxy.getLogger(getDefaultString());
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		@Test
		public void testInitializeLoggerFailureNullPointer() {
			try {
				PowerMockito.doThrow(new NullPointerException()).when(LoggerProxy.class, "getLogger", anyString());
				testApplication.initializeLogger();
				throw new AssertionError("Unexpected successful execution");
			} catch(NullPointerException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getNullPointerErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getNullPointerErrorCode() == testApplication.getReturnCode());
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		@Test
		public void testInitializeLoggerFactoryFactorySuccess() {
			when(mockLogger.getLevel()).thenReturn(Level.ALL);
			when(mockLogger.getName()).thenReturn(Constants.getLoggerName());
			testApplication.InitializeMethodLoggerFactoryFactory();
			assertTrue("Method logger factory factory returned null",  testApplication.getMethodLoggerFactoryFactory() != null);
			assertTrue("Logger returned null",  testApplication.getMethodLoggerFactoryFactory().getLogger() != null);
			assertTrue("Unexpected level was provided\nExpected: " + Level.ALL + "\nActual: " + testApplication.getMethodLoggerFactoryFactory().getLogger().getLevel(),testApplication.getMethodLoggerFactoryFactory().getLogger().getLevel().equals(Level.ALL));
			assertTrue("Unexpected logger name was provided\nExpected: " + Constants.getLoggerName() + "\nActual: " + testApplication.getMethodLoggerFactoryFactory().getLogger().getName(),testApplication.getMethodLoggerFactoryFactory().getLogger().getName().contentEquals(Constants.getLoggerName()));
		}
		
		@Test
		public void testConfigureLoggerSuccess() {
			defaultLoggerMockSettings();
			testApplication.configureLogger();
			assertTrue("Unexpected return code provided\nExpected: " + Constants.getSuccessCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSuccessCode() == testApplication.getReturnCode());
			assertTrue("Method logger factory returned null", testApplication.getMethodLoggerFactory() != null);
			assertTrue("Unexpected level was provided\nExpected: " + Level.ALL + "\nActual: " + testApplication.getMethodLoggerFactory().getLogger().getLevel(), testApplication.getMethodLoggerFactory().getLogger().getLevel().equals(Level.ALL));
			assertTrue("Unexpected logger name was provided\nExpected: " + TestConstants.getDefaultString() + "\nActual: " + testApplication.getMethodLoggerFactory().getLogger().getName(), testApplication.getMethodLoggerFactory().getLogger().getName().contentEquals(TestConstants.getDefaultString()));
			verify(mockLogger, times(1)).addHandler(any(Handler.class));
			verify(mockLogger, times(1)).setLevel(any(Level.class));
		}
		
		@Test
		public void testConfigureLoggerFailureSecurity() {
			defaultLoggerMockSettings();
			doThrow(new SecurityException()).when(mockLogger).addHandler(any(Handler.class));
			try {
				testApplication.configureLogger();
				throw new AssertionError("Unexpected successful execution");
			} catch(SecurityException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSecurityErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSecurityErrorCode() == testApplication.getReturnCode());
				assertTrue("Method logger factory returned null", testApplication.getMethodLoggerFactory() != null);
				assertTrue("Unexpected level was provided\nExpected: " + Level.ALL + "\nActual: " + testApplication.getMethodLoggerFactory().getLogger().getLevel(), testApplication.getMethodLoggerFactory().getLogger().getLevel().equals(Level.ALL));
				assertTrue("Unexpected logger name was provided\nExpected: " + TestConstants.getDefaultString() + "\nActual: " + testApplication.getMethodLoggerFactory().getLogger().getName(), testApplication.getMethodLoggerFactory().getLogger().getName().contentEquals(TestConstants.getDefaultString()));
				verify(mockLogger, times(1)).addHandler(any(Handler.class));
				verify(mockLogger, times(0)).setLevel(any(Level.class));
			}
		}
		
		public void defaultLoggerMockSettings() {
			testApplication.setMethodLoggerFactory(null);
			when(mockMethodLoggerFactoryFactory.createMethodLoggerFactory(anyString())).thenReturn(mockMethodLoggerFactory);
			when(mockLogHandler.getLevel()).thenReturn(Level.ALL);
			when(mockLogHandler.getEncoding()).thenReturn(null);
			when(mockLogger.getName()).thenReturn(TestConstants.getDefaultString());
			when(mockLogger.getLevel()).thenReturn(Level.ALL);
			when(mockMethodLoggerFactory.getLogger()).thenReturn(mockLogger);
		}
}
