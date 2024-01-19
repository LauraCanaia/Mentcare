package demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeValidator {

    public static boolean isValidTimeFormat(String time) {

        String regex = "^(?:[01]\\d|2[0-3]):[0-5]\\d$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);

        return matcher.matches();
    }
}

