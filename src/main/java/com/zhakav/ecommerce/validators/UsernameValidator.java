package com.zhakav.ecommerce.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private boolean isValidUsername(String name) {

        String regex = "^[A-Za-z]\\w{5,29}$";

        Pattern p = Pattern.compile(regex);

        if (name == null) 
            return false;
        

        Matcher m = p.matcher(name);

        return m.matches();
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        return isValidUsername(arg0);
    }

}
