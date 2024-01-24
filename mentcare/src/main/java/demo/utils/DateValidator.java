package demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    private static final String DATE_PATTERN = "\\d{1,2}[-/]\\d{1,2}[-/]\\d{4}";

    public static boolean isValidDateFormat(String date) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
}
