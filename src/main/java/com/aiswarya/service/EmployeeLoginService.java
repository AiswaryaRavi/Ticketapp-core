package com.aiswarya.service;

import com.aiswarya.dao.EmployeeLoginDao;
import com.aiswarya.dao.EmployeesDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.Employee;
import com.aiswarya.validator.AdminValidator;
import com.aiswarya.validator.EmployeeValidator;

public class EmployeeLoginService {
	EmployeeLoginDao emp = new EmployeeLoginDao();
	EmployeeValidator empv=new EmployeeValidator();
	AdminValidator av=new AdminValidator();
	EmployeesDao empdao=new EmployeesDao();
	Employee employee=new Employee();
	EmployeeLoginDao login=new EmployeeLoginDao();
	
	public void register(Employee emp) throws ServiceException {

		try {

			EmployeeValidator.validatateForRegistration(emp);

				empdao.save(emp);
			} 
			catch (PersistanceException e) {

				throw new ServiceException("unable to register", e);

			}

		catch (ValidationException e) {
			throw new ServiceException("Unable to register", e);
		}
	}
	public boolean login(String emailId,String password) throws ServiceException  {

		try {

			EmployeeValidator.validatateForLogin(emailId, password);
			login.login(emailId, password);
			return true;

		} catch (ValidationException e) {
			throw new ServiceException("Unable to login", e);
		
		} catch (PersistanceException e) {
			throw new ServiceException("", e);
		}
	}
	
	}


