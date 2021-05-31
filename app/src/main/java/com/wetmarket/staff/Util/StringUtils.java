package com.wetmarket.staff.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

    public static boolean isNotEmpty(CharSequence str) {
        if (str == null || str.length() == 0 || str.equals("null") || str.equals(" ") || str.equals("none") || str.equals("") || str.equals("(null)") || str.equals("undefined")) {
            return false;
        } else {
            return true;
        }
    }

    public static int findPatternRepeatNumber(String main_str, String match_string) {
        Pattern pattern = Pattern.compile(match_string); //Pattern string you want to be matched
        Matcher matcher = pattern.matcher(main_str);

        int count = 0;
        while (matcher.find())
            count++; //count any matched pattern

        return count;
    }
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static String capitizeString(String name){
        String captilizedString="";
        if(!name.trim().equals("")){
            captilizedString = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return captilizedString;
    }
}
