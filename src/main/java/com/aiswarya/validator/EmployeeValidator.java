package com.aiswarya.validator;

import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.Employee;
import com.aiswarya.util.ValidationUtil;

public class EmployeeValidator {
	static final String MSG = "password";
	static final String MSG1 = "emailid";
	static final String MSG2 = "ticket id";
	public static void validatateForRegistration(Employee e) throws ValidationException{

		ValidationUtil.isInvalid(e.getName(),"Name");
		ValidationUtil.isInvalid(e.getEmailId(),MSG1);
		ValidationUtil.isInvalid(e.getPassword(),MSG);
		ValidationUtil.isInvalid(e.getDepartmentId().getId(),"department id");
		ValidationUtil.isInvalid(e.getRole().getId(),"role id");


		
		
		}
	public static void validatateForLogin(String emailid,String password) throws ValidationException{

		ValidationUtil.isInvalid(emailid,MSG1);
		ValidationUtil.isInvalid(password,MSG);
		
		}
	public static void reAssignEmployee(int empId,int ticketId) throws ValidationException{

		ValidationUtil.isInvalid(empId,"Employee id");
		ValidationUtil.isInvalid(ticketId,"ticketid");	
		}
	public static void assignEmployee(int deptId) throws ValidationException{

		ValidationUtil.isInvalid(deptId,"department id");	
		}
	public static void viewTicket(String emailid,String password) throws ValidationException{

		ValidationUtil.isInvalid(emailid,"subject");
		ValidationUtil.isInvalid(password,MSG);	
		}
	public static void validateTicketId(String solution,int ticketId) throws ValidationException{

		ValidationUtil.isInvalid(solution,"solution");
		ValidationUtil.isInvalid(ticketId,MSG2);	
		}
	
	
	
	

}
