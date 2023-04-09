package com.g31.demo.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<FullName, String> {
    /**
     * 2 - 30 characters, numbers, or chinese characters (both traditional and simplified)
     */
    private static final String REG_EXP = "(?!.*\\s$)((?=\\S)(?![0-9]+$)[\\u4E00-\\u9FA5A-Za-z0-9. ' ]{2,30})";

    @Override
    public boolean isValid(String fullNameField, ConstraintValidatorContext context) {
        if (fullNameField == null) {
            // can be null
            return true;
        }
        return fullNameField.matches(REG_EXP);
    }
}
