package com.aiswarya.test;

import java.util.Iterator;
import java.util.List;

import com.aiswarya.dao.EmployeesDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Departments;
import com.aiswarya.model.Employee;
import com.aiswarya.model.Role;

public class TestEmployee {

	public static void main(String[] args) throws PersistanceException {
		Employee employee = new Employee();
		EmployeesDao employeedao = new EmployeesDao();
		Departments dept = new Departments();
		Role role = new Role();
		System.out.println(employeedao.getAll(1).getEmailId());
		

		// To insert into table
//		dept.setId(2);
//		employee.setDepartmentId(dept);
//		role.setId(2);
//		employee.setRole(role);
//		employee.setName("Rahul");
//		employee.setEmailId("rahul@gmail.com");
//		employee.setPassword("rahull123");
//		employeedao.save(employee);

		// To update the table
		// employee.setEmailId("rasik@gmail.com");
		// employee.setPassword("ra");
		// employeedao.update(employee);

		// To display the table
		// List<Employee> list = employeedao.listAll();
		// Iterator<Employee> i = list.iterator();
		// while (i.hasNext()) {
		// Employee employee1 = (Employee) i.next();
		// System.out.println(employee1.getId() + "\t" +
		// employee1.getDepartmentId().getId()+ "\t"
		// + employee1.getName() + "\t" +
		// employee1.getEmailId()+"\t"+employee1.getPassword()+"\t"+employee1.getActive());
		// }
		//
	//	System.out.println(employeedao.getId("usha@gmail.com"));
		//employeedao.lastInserId(1);
//System.out.println(employeedao.getRoleId(1).getId());
		//System.out.println(employeedao.getRid(1,1).getId());
	}

}
