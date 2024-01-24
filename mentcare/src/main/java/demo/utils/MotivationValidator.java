package demo.utils;

public class MotivationValidator {

    public static boolean isWithinMaxLength(String input, int maxLength) {
        return input.length() <= maxLength;
    }
}

