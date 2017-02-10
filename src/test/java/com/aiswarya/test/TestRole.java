package com.aiswarya.test;

import java.util.List;

import com.aiswarya.dao.DepartmentDao;
import com.aiswarya.model.Departments;
import com.aiswarya.model.Role;
import com.aiswarya.dao.RoleDAO;
import com.aiswarya.exception.PersistanceException;

public class TestRole {

	public static void main(String[] args) throws PersistanceException {
		Role r = new Role();
		RoleDAO rdao = new RoleDAO();

		// r.setName("MANAGER");
		// rdao.save(r);

		// To update the table
		// r.setName("DEVELOPER");
		// r.setId(2);
		// rdao.update(r);

		// To display table
//		List<Role> list = rdao.listAll();
//		for (Role i : list) {
//			System.out.println(i);
//		}
		

	}
}
