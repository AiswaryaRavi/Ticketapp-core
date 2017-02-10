package com.aiswarya.service;

import org.springframework.dao.DataAccessException;

import com.aiswarya.dao.AdminDao;
import com.aiswarya.dao.EmployeeLoginDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.validator.AdminValidator;

public class AdminService {
	EmployeeLoginDao emp = new EmployeeLoginDao();
	AdminDao ad = new AdminDao();

	public void deleteService(String emailId, String password, int ticketId) throws ServiceException {
		try {
			if (emp.login(emailId, password)) {
				AdminValidator.validatateFordelete(ticketId);
				ad.AdminDelete(emailId, password, ticketId);

			}
		} catch (ValidationException e) {
			throw new ServiceException("", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("", e2);

		}

	}
}
