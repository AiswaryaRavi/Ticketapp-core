package com.aiswarya.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import com.aiswarya.exception.PersistanceException;

public class LoginDao {
	UserDao userdao = new UserDao();

	public boolean login(String emailid, String password) throws PersistanceException {
		try{
		userdao.getId(emailid,password);
		}catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("wrong credentials" , e);
		}
		return true;
	
	}

}
