package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.Constants.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;

/**
 * 
 * @author Aaron Schuman
 * <p>
 * The BaseFormatter object is used to return a standardized string containing all of the relevant information within a {@link LogRecord}
 *
 */
public class BaseFormatter extends Formatter {
	
	/**
	 * Returns a standardized string containing all of the relevant information within a LogRecord
	 * @param record a LogRecord containing the relevant information to format.
	 * @return a formatted string
	 */
	public String format(LogRecord record) {
		return "\r\n" +
				LocalDateTimeProxy.now().format(DateTimeFormatterProxy.ofPattern(getLogEntryDatetimeStringStringFormat())) + getFormattingCharacter() +
				record.getLevel() + getFormattingCharacter() +
				record.getThreadID()+ getFormattingCharacter() +
				record.getSourceClassName()+ getFormattingCharacter() +
                record.getSourceMethodName()+ getFormattingCharacter() +
                record.getMessage();
	}

}
