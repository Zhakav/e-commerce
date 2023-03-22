package com.zhakav.ecommerce.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile,String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

        
        return isValidMobile(arg0);
    }
    
    private boolean isValidMobile(String phoneNumber) {

        String regex = "09[0-9]{9,9}";

        Pattern p = Pattern.compile(regex);

        if (phoneNumber == null) 
            return true;
        

        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }
}
