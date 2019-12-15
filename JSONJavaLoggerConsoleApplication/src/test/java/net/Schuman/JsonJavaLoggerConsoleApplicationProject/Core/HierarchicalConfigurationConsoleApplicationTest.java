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

import net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.HierarchicalConfigurationConsoleApplication;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.io.File;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.HierarchicalBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public class HierarchicalConfigurationConsoleApplicationTest{

		@InjectMocks
		private HierarchicalConfigurationConsoleApplication<FileBasedConfiguration> testApplication;
		@Mock
		private FileBasedConfigurationBuilder<FileBasedConfiguration> mockBuilder;
		@Mock
		private FileBasedConfiguration mockApplicationConfiguration;
		@Mock
		private Parameters mockParameters;
		
		@Mock
		private HierarchicalBuilderParameters mockHierarchicalParameters;
		
		@Before
		public void setUp() {
			mockBuilder = mock(FileBasedConfigurationBuilder.class);
			mockApplicationConfiguration = mock(FileBasedConfiguration.class);
			mockParameters = mock(Parameters.class);
			mockHierarchicalParameters = mock(HierarchicalBuilderParameters.class);
			testApplication = mock(HierarchicalConfigurationConsoleApplication.class, Mockito.CALLS_REAL_METHODS);
			
			MockitoAnnotations.initMocks(testApplication);
		}
		
		@Test
		public void testConfigureBuilderSuccess() {
			testApplication.configureBuilder();
			
		}
		
		public void defaultMockSettings() {
			when(mockBuilder.configure(any(BuilderParameters.class))).thenReturn(mockBuilder);
			when(mockParameters.hierarchical()).thenReturn(mockHierarchicalParameters);
			when(mockHierarchicalParameters.setFile(any(File.class))).thenReturn(mockHierarchicalParameters);
			//TODO: mock the Paths and System proxy classes
		}
}
