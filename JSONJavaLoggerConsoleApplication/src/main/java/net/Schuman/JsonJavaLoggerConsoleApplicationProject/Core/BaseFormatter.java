package net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core;

import static net.Schuman.JsonJavaLoggerConsoleApplicationProject.Core.Constants.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;

public class BaseFormatter extends Formatter {
	
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
