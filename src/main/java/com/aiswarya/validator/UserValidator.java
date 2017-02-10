package com.aiswarya.validator;

import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.User;
import com.aiswarya.util.ValidationUtil;

public class UserValidator {
	static final String MSG = "password";
	static final String MSG1 = "emailid";
	static final String MSG2 = "ticket id";

	public static void validatateForRegistration(User u) throws ValidationException {

		ValidationUtil.isInvalid(u.getName(), "Name");
		ValidationUtil.isInvalid(u.getEmailId(), MSG1);
		ValidationUtil.isInvalid(u.getPassword(), MSG);

	}

	public static void validatateForLogin(String emailid, String password) throws ValidationException {

		ValidationUtil.isInvalid(emailid, MSG1);
		ValidationUtil.isInvalid(password, MSG);

	}

	public static void ticketCreation(String subject, String description, String department, String priority)
			throws ValidationException {

		ValidationUtil.isInvalid(subject, "subject");
		ValidationUtil.isInvalid(description, "description");
		ValidationUtil.isInvalid(department, "department name");
		ValidationUtil.isInvalid(priority, "priority");
	}

	public static void ticketUpdation(Integer id, String description) throws ValidationException {

		ValidationUtil.isInvalid(id, MSG2);

		ValidationUtil.isInvalid(description, "description");

	}

	public static void ticketUpdateStatus(Integer id) throws ValidationException {

		ValidationUtil.isInvalid(id, MSG2);

	}

	public static void viewTicket(String emailid, String password) throws ValidationException {

		ValidationUtil.isInvalid(emailid, "email");
		ValidationUtil.isInvalid(password, MSG);
	}

}
