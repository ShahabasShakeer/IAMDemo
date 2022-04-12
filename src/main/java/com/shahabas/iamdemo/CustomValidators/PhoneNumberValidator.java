package com.shahabas.iamdemo.CustomValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shahabas.iamdemo.annotations.IndianPhone;

public class PhoneNumberValidator implements ConstraintValidator<IndianPhone, String> {

	private String countryCode;
	
	@Override
	public void initialize(IndianPhone indianPhone) {
		countryCode = indianPhone.value();
	}

	@Override
	public boolean isValid(String number, ConstraintValidatorContext context) {

		boolean result;
		
		if (number != null) {
			result = number.startsWith(countryCode);
		} else {
			result = true;
		}
		
		return result;
	}

}
