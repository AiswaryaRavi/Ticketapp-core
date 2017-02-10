package com.aiswarya.service;

import com.aiswarya.dao.LoginDao;
import com.aiswarya.dao.UserDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.User;
import com.aiswarya.validator.UserValidator;

public class UserService {
	private UserDao userdao = new UserDao();
	private LoginDao login=new LoginDao();
	User u=new User();

	public void register(User u) throws ServiceException {

		try {

			UserValidator.validatateForRegistration(u);

				userdao.save(u);
			} 
			catch (PersistanceException e) {

				throw new ServiceException("unable to register", e);

			}

		catch (ValidationException e) {
			throw new ServiceException("Unable to register", e);
		}
	}
	public boolean login(String emailid,String password) throws ServiceException  {

		try {

			UserValidator.validatateForLogin(emailid,password);
			login.login(emailid, password);
			return true;

		} catch (ValidationException e) {
			throw new ServiceException("Unable to login", e);
		
		} catch (PersistanceException e) {
			throw new ServiceException("Unable to login", e);
		}
	}
	
	
}
