package com.example.filmai.utills;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USERNAME_REGEX_PATTERN="^[A-Za-z0-9]{5,13}$";
    public static final String PASSWORD_REGEX_PATTERN="^[A-Za-z0-9!@#$%]{5,13}$";
    public static final String EMAIL_REGEX_PATTERN="^[A-Za-z0-9@]{5,20}$";//"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String TITLE_REGEX_PATTERN = "^[A-Za-z0-9 ]{5,25}$";
    public static final String TIME_REGEX_PATTERN = "^[0-9]{1,13}$";


    public static boolean isValidUsername(String username){
         //sukuriamas validacijos taissykles pagal anskciau apsirasyta sablona
        Pattern pattern =Pattern.compile(USERNAME_REGEX_PATTERN);
        //valadicijoss atitikmens sukurimas palyginaant vartotojo iveesta username su validacijos taisyklemis
        Matcher matcher=pattern.matcher(username);
        //grazins true jeigu atitiks vartotojo ivestas vardas musu susikurta valadacija
        //priesingu atveju grazins false
        return matcher.find();
    }
    public static boolean isValidPassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher=pattern.matcher(password);
        return matcher.find();
    }
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher=pattern.matcher(email);
        return matcher.find();
    }
    public static boolean isValidTitle(String title) {
        Pattern pattern = Pattern.compile(TITLE_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(title);
        return matcher.find();
    }
    public static boolean isValidTime(String time) {
        Pattern pattern = Pattern.compile(TIME_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(time);
        return matcher.find();
    }
}

