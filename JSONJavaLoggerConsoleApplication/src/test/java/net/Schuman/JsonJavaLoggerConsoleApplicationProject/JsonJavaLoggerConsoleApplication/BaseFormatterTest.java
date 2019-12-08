package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

/*
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
*/
import net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication.BaseFormatter;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication.TestConstants.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({LocalDateTimeProxy.class, DateTimeFormatterProxy.class})
public class BaseFormatterTest{

	@InjectMocks
	private BaseFormatter testFormatter;
	@Mock
	private LogRecord mockRecord;
	
	private LocalDateTime mockDateTime;
	
	private DateTimeFormatter mockFormatter;
	
	private String expected;
	private String result;
	
	/*
	public BaseFormatterTest(String testName) {
		//super(testName);
	}
	*/
	
	public BaseFormatterTest() {	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(testFormatter);
		//PowerMockito.mockStatic(LocalDateTime.class, DateTimeFormatter.class);
		//mockDateTime = 
		PowerMockito.mockStatic(DateTimeFormatterProxy.class, Mockito.CALLS_REAL_METHODS);
		PowerMockito.mockStatic(LocalDateTimeProxy.class, Mockito.CALLS_REAL_METHODS);
		//mockFormatter = PowerMockito.spy(DateTimeFormatter.ISO_DATE_TIME);
		//PowerMockito.mockStatic(DateTimeFormatter.class);
		mockRecord = mock(LogRecord.class);
		testFormatter = new BaseFormatter();
		//super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		//super.tearDown();
	}

	/*
	public static Test suite() {
		return new TestSuite(BaseFormatterTest.class);
	}
	*/
	
	@Test
	public void testFormatSuccessful() {
		defaultMockSettings();
		try {
		PowerMockito.doReturn(LocalDateTimeProxy.now()).when(LocalDateTimeProxy.class, "now");
		//when(LocalDateTimeProxy.now()).thenReturn(LocalDateTime.parse(getConstantDatetime(), getDateTimeFormat()));
		PowerMockito.doReturn(DateTimeFormatter.ofPattern(getDateTimeStringFormat())).when(DateTimeFormatterProxy.class, "ofPattern", anyString());
		//when(DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat())).thenReturn(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
		PowerMockito.doReturn(getConstantDatetime()).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
		}
		catch(Exception e) {
			throw new AssertionError(e);
		}
		result = testFormatter.format(mockRecord);
		assertTrue("\nUnexpected value returned; Expected: \n" + expected + "\n Actual:\n" + result, expected.contentEquals(result));
		//verify(mockDateTime, times(1)).now();
		//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
		LocalDateTimeProxy.now();
		//verify(mockFormatter, times(1)).ofPattern(anyString());
		//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
		DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
		//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
		LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
		verify(mockRecord, times(1)).getLevel();
		verify(mockRecord, times(1)).getMessage();
		verify(mockRecord, times(1)).getThreadID();
		verify(mockRecord, times(1)).getSourceClassName();
		verify(mockRecord, times(1)).getSourceMethodName();
	}

	@Test(expected = DateTimeException.class)
	public void testFormatFailureDateTime() {
		defaultMockSettings();
		try {
			PowerMockito.doReturn(LocalDateTimeProxy.now()).when(LocalDateTimeProxy.class, "now");
			PowerMockito.doReturn(DateTimeFormatter.ofPattern(getDateTimeStringFormat())).when(DateTimeFormatterProxy.class, "ofPattern", anyString());
			PowerMockito.doThrow(new DateTimeException(getDefaultString())).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
			result = testFormatter.format(mockRecord);
		}
		catch(Exception e) {
			//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
			LocalDateTimeProxy.now();
			//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
			DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
			try {
				PowerMockito.doReturn(null).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
			}
			catch(Exception e2) {
				throw new AssertionError(e2);
			}
			//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
			LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			throw new DateTimeException(e.getMessage());
		}
		throw new AssertionError("Error: No exception was thrown");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFormatFailureIllegalArgument() {
		defaultMockSettings();
		try {
			PowerMockito.doReturn(LocalDateTimeProxy.now()).when(LocalDateTimeProxy.class, "now");
			PowerMockito.doThrow(new IllegalArgumentException()).when(DateTimeFormatterProxy.class, "ofPattern", anyString());
			PowerMockito.doReturn(getConstantDatetime()).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
			result = testFormatter.format(mockRecord);
		}
		catch(Exception e) {
			//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
			LocalDateTimeProxy.now();
			try {
				PowerMockito.doReturn(null).when(LocalDateTimeProxy.class, "format", any(DateTimeFormatter.class));
			}
			catch(Exception e2) {
				throw new AssertionError(e2);
			}
			//PowerMockito.verifyStatic(DateTimeFormatterProxy.class, times(1));
			DateTimeFormatterProxy.ofPattern(getDateTimeStringFormat());
			//PowerMockito.verifyStatic(LocalDateTimeProxy.class, times(1));
			LocalDateTimeProxy.format(DateTimeFormatter.ofPattern(getDateTimeStringFormat()));
			throw new IllegalArgumentException(e.getMessage());
		}
		throw new AssertionError("Error: No exception was thrown");
	}
	
	@SuppressWarnings("static-access")
	private void defaultMockSettings() {
		try {
		/*
		mockDateTime = mock(LocalDateTime.class);
		mockFormatter = mock(DateTimeFormatter.class);
		*/
		expected = "\r\n" + getConstantDatetime() + getFormattingCharacter() + 
				Level.FINE + getFormattingCharacter() + 
				getDefaultInt() + getFormattingCharacter() +
				getDefaultString() + getFormattingCharacter() + 
				getDefaultString() + getFormattingCharacter() + 
				getDefaultString();
		when(mockRecord.getLevel()).thenReturn(Level.FINE);
		when(mockRecord.getMessage()).thenReturn(getDefaultString());
		when(mockRecord.getThreadID()).thenReturn(getDefaultInt());
		when(mockRecord.getSourceClassName()).thenReturn(getDefaultString());
		when(mockRecord.getSourceMethodName()).thenReturn(getDefaultString());
		}
		catch(Exception e) {
			throw new AssertionError(e);
		}
	}
}
