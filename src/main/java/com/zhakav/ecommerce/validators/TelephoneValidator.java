package com.zhakav.ecommerce.validators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone,String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        return isValidTelephone(arg0);
    }

    private boolean isValidTelephone(String phoneNumber) {

        Set<String> areaCodes=new HashSet(Arrays.asList(
            "041" , "044" , "021" , "026" , "028" , "031" , "045" , "084",
            "077","038", "056" ,"051", "058", "023", "024", "061", "054",
            "071", "025" ,"087" ,"034", "083", "074", "017", "013", "066",
            "011", "086", "076", "081", "035"  
                    ));

        if (phoneNumber == null)
            return true;
            
        boolean result=false;
        String regex = "[0-9]{8,8}";
        Pattern p;
        Matcher m;

        for (String areaCode : areaCodes) {

            p = Pattern.compile(areaCode+regex);
            m = p.matcher(phoneNumber);

            if(m.matches()){
                result= true;
                break;
            }
        }

        return result;
    }
    
}
