package com.example.filmai.utills;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String USERNAME_REGEX_PATTERN="^[A-Za-z0-9]{5,13}$";
    public static final String PASSWORD_REGEX_PATTERN="^[A-Za-z0-9!@#$%]{5,13}$";
    public static final String EMAIL_REGEX_PATTERN="^[A-Za-z0-9@]{5,20}$";

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
}

