package com.example.casestudy.util;

import java.util.regex.Pattern;

public class ValidateUtils {
    public  static  final  String USERNAME_REGEX ="^[\\p{L}\\s]{7,20}$";
    private static final  String ID_CARD_REGEX= "^[0-9]{9,12}$";
    private static final  String CELL_PHONE_REGEX= "^(0[3|5|7|8|9])+([0-9]{8})$";
    private static final  String EMAIL_REGEX= "^\\w+@\\w+[.]\\w+$";
    private static final String regex = "^KH-[0-9]{4}$";
    public static  boolean isValidateName(String name){
        return Pattern.matches(USERNAME_REGEX,name);
    }
    public static boolean isValidateIdCard(String card){
        return Pattern.matches(ID_CARD_REGEX,card);
    }
    public static boolean isValidatePhone(String phone){
        return Pattern.matches(CELL_PHONE_REGEX,phone);
    }
    public static boolean isValidateEmail(String email){
        return Pattern.matches(EMAIL_REGEX,email);
    }
}
