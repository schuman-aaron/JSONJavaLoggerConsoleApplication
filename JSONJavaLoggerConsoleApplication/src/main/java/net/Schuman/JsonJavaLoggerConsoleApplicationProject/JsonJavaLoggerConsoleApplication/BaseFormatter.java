package net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.LogRecord;
import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.JsonJavaLoggerConsoleApplication.Constants.*;

public class BaseFormatter {
	
	public String format(LogRecord record) {
		return "\r\n" +
				LocalDateTimeProxy.now().format(DateTimeFormatterProxy.ofPattern(getDatetimeStringFormat())) + getFormattingCharacter() +
				record.getLevel() + getFormattingCharacter() +
				record.getThreadID()+ getFormattingCharacter() +
				record.getSourceClassName()+ getFormattingCharacter() +
                record.getSourceMethodName()+ getFormattingCharacter() +
                record.getMessage();
	}

}
