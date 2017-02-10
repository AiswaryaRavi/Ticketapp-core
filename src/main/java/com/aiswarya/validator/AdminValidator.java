package com.aiswarya.validator;

import com.aiswarya.exception.ValidationException;
import com.aiswarya.util.ValidationUtil;

public class AdminValidator {

	public static void validatateFordelete(int ticketId) throws ValidationException{

		ValidationUtil.isInvalid(ticketId,"Ticket id");

		
		}
}
