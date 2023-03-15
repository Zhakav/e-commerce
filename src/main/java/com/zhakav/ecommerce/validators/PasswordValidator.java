package com.zhakav.ecommerce.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

        private boolean isValidPassword(String password) {
                boolean isValid = true;
                if (password.length() > 15 || password.length() < 8) {
                        //System.out.println("Password must be less than 20 and more than 8 characters in length.");
                        isValid = false;
                }
                String upperCaseChars = "(.*[A-Z].*)";
                if (!password.matches(upperCaseChars)) {
                        //System.out.println("Password must have at least one uppercase character");
                        isValid = false;
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!password.matches(lowerCaseChars)) {
                        //System.out.println("Password must have at least one lowercase character");
                        isValid = false;
                }
                String numbers = "(.*[0-9].*)";
                if (!password.matches(numbers)) {
                        //System.out.println("Password must have at least one number");
                        isValid = false;
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!password.matches(specialChars)) {
                        //System.out.println("Password must have at least one special character among @#$%");
                        isValid = false;
                }
                return isValid;
        }

        @Override
        public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
                return isValidPassword(arg0);
        }

}
