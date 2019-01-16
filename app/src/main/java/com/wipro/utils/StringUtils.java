package com.wipro.utils;

public final class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static String trim(String s) {
        return isEmpty(s) ? null : s.trim();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean compare(String s, String s1) {
        if (s == null && s1 == null) {
            return true;
        }
        if (s1 == null || s == null) {
            return false;
        }
        return s.equals(s1);
    }
}
