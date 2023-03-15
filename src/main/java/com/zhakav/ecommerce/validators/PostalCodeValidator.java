package com.zhakav.ecommerce.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PostalCodeValidator implements ConstraintValidator<PostalCode,String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
       return isValidPostalCode(arg0);
    }
 
    private boolean isValidPostalCode(String postalCode ){

        String regex = "[0-9]{10,10}";
        Pattern p;
        Matcher m;
    
        p= Pattern.compile(regex);
        m=p.matcher(postalCode);
    
        return m.matches();
    
    }
    
}