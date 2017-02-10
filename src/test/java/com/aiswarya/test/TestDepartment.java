package com.aiswarya.test;

import java.util.List;

import com.aiswarya.dao.DepartmentDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Departments;

public class TestDepartment {

	public static void main(String[] args) throws PersistanceException {
		Departments dept = new Departments();
		DepartmentDao deptdao = new DepartmentDao();

		// dept.setDepartment("FINANCE");
		// deptdao.save(dept);

		// To update the table
		// dept.setDepartment("Testing");
		// dept.setId(2);
		// deptdao.update(dept);

		// To display table
		List<Departments> list = deptdao.listAll();
		for (Departments i : list) {
			System.out.println(i);
		}
	}
}
