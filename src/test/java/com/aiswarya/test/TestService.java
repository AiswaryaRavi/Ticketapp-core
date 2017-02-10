package com.aiswarya.test;

import com.aiswarya.exception.ServiceException;
import com.aiswarya.model.Departments;
import com.aiswarya.model.Employee;
import com.aiswarya.model.Role;
import com.aiswarya.model.User;
import com.aiswarya.service.EmployeeLoginService;
import com.aiswarya.service.EmployeeService;
import com.aiswarya.service.TicketService;
import com.aiswarya.service.UserService;

public class TestService {

	public static void main(String[] args) throws ServiceException {
		TicketService ts=new TicketService();
		UserService us=new UserService();
		EmployeeLoginService es=new EmployeeLoginService();
		EmployeeService es1=new EmployeeService();
		User user=new User();
		Employee employee=new Employee();
		Departments dept=new Departments();
		Role r=new Role();
		
//		user.setName("sdsf");
//		user.setEmailId("chitra@gmail.com");
//		user.setPassword("ahgfhfjf");
//		us.register(user);
//		dept.setId(1);
//		employee.setDepartmentId(dept);
//		r.setId(2);
//		employee.setRole(r);
//		employee.setName("hsfjh");
//		employee.setEmailId("hgvdjvd");
//		employee.setPassword("");
//		es.register(employee);
		//us.login("shfkjf", "");
		//es.login("", "gfgf");
		//us.login("dfd", "hsvdjmfd");
		//ts.ticketCreation("hhgf", "fgdd", "sfjfsdjfgjafduf", "agffkayd", "");
		//ts.ticketUpdation("aiswaryaravindran240@gmail.com",33 , "hi");
	//	ts.closeTicket("aiswaryaravindran240@gmail.com",2);
		//ts.displayTicket("", "");
		es1.reassignEmployee("aiswaryaravindran240@gmail.com", 11, 34);
		

	}

}
