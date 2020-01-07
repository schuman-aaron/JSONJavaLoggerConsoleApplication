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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocalDateTimeProxy.class, DateTimeFormatterProxy.class})
public class JavaLoggerJsonConfigurationConsoleApplicationTest {

		private JavaLoggerJsonConfigurationConsoleApplication testConsoleApplication;
		
		@Mock
		private FileBasedConfigurationBuilder<FileBasedConfiguration> mockBuilder;
		
		@Mock
		private JSONConfiguration mockJsonConfiguration;
		
		
		
		@Before
		public void setUp() {
			mockJsonConfiguration = mock(JSONConfiguration.class);
			PowerMockito.mockStatic(LocalDateTimeProxy.class, Mockito.CALLS_REAL_METHODS);
			PowerMockito.mockStatic(DateTimeFormatterProxy.class, Mockito.CALLS_REAL_METHODS);
			
			testConsoleApplication = mock(JavaLoggerJsonConfigurationConsoleApplication.class, Mockito.CALLS_REAL_METHODS);
		}
		
		@Test
		public void testInitializeHandlerSuccessful() {
			defaultInitializeHandlerMockSettings();
		}
		
		public void defaultInitializeHandlerMockSettings() {
			when(mockJsonConfiguration.getString(anyString())).thenReturn(getDefaultString());
			
		}
}
