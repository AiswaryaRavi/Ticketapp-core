package com.aiswarya.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import com.aiswarya.exception.PersistanceException;

public class EmployeeLoginDao {
	EmployeesDao emp=new EmployeesDao();

	public boolean login(String emailid, String password) throws PersistanceException {
		try{
			emp.getId(emailid,password);
			}catch (EmptyResultDataAccessException e) {
				throw new PersistanceException("wrong credentials" , e);
			}
			return true;
		
	}

}
