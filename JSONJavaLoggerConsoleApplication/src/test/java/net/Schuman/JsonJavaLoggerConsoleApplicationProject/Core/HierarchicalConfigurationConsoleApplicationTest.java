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

import net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.HierarchicalConfigurationConsoleApplication;
import net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.Constants;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.Constants.getInvalidPathErrorCode;
import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URI;
import java.nio.file.InvalidPathException;
import java.security.InvalidParameterException;

import javax.naming.ConfigurationException;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.HierarchicalBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PathsProxy.class, SystemProxy.class})
public class HierarchicalConfigurationConsoleApplicationTest{

		private String[] tooManyArguments = { getDefaultString(), getDefaultString()};
	
		private HierarchicalConfigurationConsoleApplication<FileBasedConfiguration> testApplication;
		@Mock
		private FileBasedConfigurationBuilder<FileBasedConfiguration> mockBuilder;
		@Mock
		private FileBasedConfiguration mockApplicationConfiguration;
		@Mock
		private Parameters mockParameters;
		@Mock
		private HierarchicalBuilderParameters mockHierarchicalParameters;
		@Mock
		private PathsProxy mockPaths;
		@Mock
		private File mockFile;
		
		@Before
		public void setUp() {
			mockBuilder = mock(FileBasedConfigurationBuilder.class);
			mockApplicationConfiguration = mock(FileBasedConfiguration.class);
			mockParameters = mock(Parameters.class);
			mockHierarchicalParameters = mock(HierarchicalBuilderParameters.class);
			mockFile = mock(File.class);
			mockPaths = mock(PathsProxy.class);
			PowerMockito.mockStatic(PathsProxy.class);
			PowerMockito.mockStatic(SystemProxy.class);
			testApplication = mock(HierarchicalConfigurationConsoleApplication.class, Mockito.CALLS_REAL_METHODS);
			testApplication.setApplicationConfiguration(mockApplicationConfiguration);
			testApplication.setBuilder(mockBuilder);
			testApplication.setParameters(mockParameters);
			testApplication.setReturnCode(getDefaultInt());
			
			MockitoAnnotations.initMocks(testApplication);
		}
		
		// [start] ConfigureBuilder
		//TODO: Exceptions need to change the return code.
		@Test
		public void testConfigureBuilderSuccess() {
			defaultConfigureBuilderMockSettings();
			try {
				testApplication.configureBuilder(getDefaultStringArray());
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			}
			assertTrue("Unexpected return code provided\n\rExpected: " + getDefaultInt() + "\n\r Actual: " + testApplication.getReturnCode(), getDefaultInt() == testApplication.getReturnCode());
			verify(mockBuilder, times(1)).configure(any(BuilderParameters.class));
			verify(mockParameters, times(1)).hierarchical();
			verify(mockHierarchicalParameters, times(1)).setFile(any(File.class));
			verify(mockPaths, times(1)).resolve(anyString());
			verify(mockPaths, times(1)).normalize();
			verify(mockPaths, times(1)).toFile();
			//PowerMockito.verifyStatic(PathsProxy.class, times(1));
			PathsProxy.get(getDefaultString());
			//PowerMockito.verifyStatic(SystemProxy.class, times(1));
			SystemProxy.getProperty(getDefaultString());
		}
		
		@Test
		public void testConfigureBuilderFailureConfiguration() {
			defaultConfigureBuilderMockSettings();
			try {
				testApplication.configureBuilder(tooManyArguments);
				throw new AssertionError("Unexpected successful execution");
			}
			catch(ConfigurationException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getInputArgumentErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getInputArgumentErrorCode() == testApplication.getReturnCode());
				verify(mockParameters, times(0)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				//PowerMockito.verifyStatic(PathsProxy.class, times(1));
				PathsProxy.get(getDefaultString());
				//PowerMockito.verifyStatic(SystemProxy.class, times(1));
				SystemProxy.getProperty(getDefaultString());
				verify(mockPaths, times(0)).resolve(anyString());
				verify(mockPaths, times(0)).normalize();
				verify(mockPaths, times(0)).toFile();
			}
		}
		
		@Test
		public void testConfigureBuilderFailureInvalidPath() {
			defaultConfigureBuilderMockSettings();
			PowerMockito.when(PathsProxy.get(anyString())).thenThrow(new InvalidPathException(getDefaultString(), getDefaultString()));
			try {
				testApplication.configureBuilder(getDefaultStringArray());
				throw new AssertionError("Unexpected successful execution");
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			} catch (InvalidPathException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getInvalidPathErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getInvalidPathErrorCode() == testApplication.getReturnCode());
				verify(mockBuilder, times(0)).configure(any(BuilderParameters.class));
				verify(mockParameters, times(1)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				verify(mockPaths, times(0)).resolve(anyString());
				//PowerMockito.verifyStatic(SystemProxy.class, times(1));
				SystemProxy.getProperty(getDefaultString());
				verify(mockPaths, times(0)).normalize();
				verify(mockPaths, times(0)).toFile();
			}
		}
		
		@Test
		public void testConfigureBuilderFailureUnsupportedOperation() {
			defaultConfigureBuilderMockSettings();
			when(mockPaths.toFile()).thenThrow(new UnsupportedOperationException());
			try {
				testApplication.configureBuilder(getDefaultStringArray());
				throw new AssertionError("Unexpected successful execution");
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			} catch (UnsupportedOperationException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getUnsupportedOperationErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getUnsupportedOperationErrorCode() == testApplication.getReturnCode());
				verify(mockBuilder, times(0)).configure(any(BuilderParameters.class));
				verify(mockParameters, times(1)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				verify(mockPaths, times(1)).resolve(anyString());
				verify(mockPaths, times(1)).normalize();
				verify(mockPaths, times(1)).toFile();
				//PowerMockito.verifyStatic(PathsProxy.class, times(1));
				PathsProxy.get(getDefaultString());
				//PowerMockito.verifyStatic(SystemProxy.class, times(1));
				SystemProxy.getProperty(getDefaultString());
			}
		}
		
		@Test
		public void testConfigureBuilderFailureSecurity() {
			defaultConfigureBuilderMockSettings();
			PowerMockito.when(SystemProxy.getProperty(anyString())).thenThrow(new SecurityException());
			try {
				testApplication.configureBuilder(getDefaultStringArray());
				throw new AssertionError("Unexpected successful execution");
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			} catch (SecurityException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getSecurityErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getSecurityErrorCode() == testApplication.getReturnCode());
				verify(mockBuilder, times(0)).configure(any(BuilderParameters.class));
				verify(mockParameters, times(1)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				verify(mockPaths, times(0)).resolve(anyString());
				verify(mockPaths, times(0)).normalize();
				verify(mockPaths, times(0)).toFile();
			}
		}
		
		@Test
		public void testConfigureBuilderFailureNullPointer() {
			defaultConfigureBuilderMockSettings();
			PowerMockito.when(SystemProxy.getProperty(anyString())).thenThrow(new NullPointerException());
			try {
				testApplication.configureBuilder(getDefaultStringArray());
				throw new AssertionError("Unexpected successful execution");
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			} catch (NullPointerException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getNullPointerErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getNullPointerErrorCode() == testApplication.getReturnCode());
				verify(mockBuilder, times(0)).configure(any(BuilderParameters.class));
				verify(mockParameters, times(1)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				verify(mockPaths, times(0)).resolve(anyString());
				verify(mockPaths, times(0)).normalize();
				verify(mockPaths, times(0)).toFile();
			}
		}
		
		@Test
		public void testConfigureBuilderFailureIllegalArgument() {
			defaultConfigureBuilderMockSettings();
			PowerMockito.when(SystemProxy.getProperty(anyString())).thenThrow(new IllegalArgumentException());
			try {
				testApplication.configureBuilder(getDefaultStringArray());
				throw new AssertionError("Unexpected successful execution");
			} catch (ConfigurationException e) {
				throw new AssertionError(e);
			} catch (IllegalArgumentException e) {
				assertTrue("Unexpected return code provided\nExpected: " + Constants.getIllegalArgumentErrorCode() + "\n Actual: " + testApplication.getReturnCode(), Constants.getIllegalArgumentErrorCode() == testApplication.getReturnCode());
				verify(mockBuilder, times(0)).configure(any(BuilderParameters.class));
				verify(mockParameters, times(1)).hierarchical();
				verify(mockHierarchicalParameters, times(0)).setFile(any(File.class));
				verify(mockPaths, times(0)).resolve(anyString());
				verify(mockPaths, times(0)).normalize();
				verify(mockPaths, times(0)).toFile();
			}
		}
		
		public void defaultConfigureBuilderMockSettings() {
			when(mockBuilder.configure(any(BuilderParameters.class))).thenReturn(mockBuilder);
			when(mockParameters.hierarchical()).thenReturn(mockHierarchicalParameters);
			when(mockHierarchicalParameters.setFile(any(File.class))).thenReturn(mockHierarchicalParameters);
			PowerMockito.when(PathsProxy.get(anyString())).thenReturn(mockPaths);
			when(mockPaths.resolve(anyString())).thenReturn(mockPaths);
			when(mockPaths.normalize()).thenReturn(mockPaths);
			when(mockPaths.toFile()).thenReturn(mockFile);
			PowerMockito.when(SystemProxy.getProperty(anyString())).thenReturn(getDefaultString());
		}
		// [end] ConfigureBuilder
		
		// [start] InitializeConfiguration
		@Test
		public void testInitializeConfigurationSuccess() {
			try {
				when(mockBuilder.getConfiguration()).thenReturn(mockApplicationConfiguration);
				testApplication.initializeConfiguration();
				verify(mockBuilder, times(1)).getConfiguration();
			} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
				throw new AssertionError();
			}
		}
		
		
		@Test
		public void testInitializeConfigurationFailureConfiguration() {
			try {
				when(mockBuilder.getConfiguration()).thenThrow(new org.apache.commons.configuration2.ex.ConfigurationException());
				testApplication.initializeConfiguration();
				throw new AssertionError("Unexpected successful execution");
			} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
				assertTrue("Unexpected return code provided\nExpected:" + Constants.getConfigurationErrorCode() + "\nActual:" + testApplication.getReturnCode(), testApplication.getReturnCode() == Constants.getConfigurationErrorCode());
			}
		}
		
		
		// [end] InitializeConfiguration
}
