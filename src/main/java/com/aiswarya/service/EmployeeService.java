package com.aiswarya.service;

import org.springframework.dao.DataAccessException;

import com.aiswarya.dao.AssignEmployeeDao;
import com.aiswarya.dao.EmployeeLoginDao;
import com.aiswarya.dao.ReplySolutionDao;
import com.aiswarya.dao.UpdateSolutionDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.validator.EmployeeValidator;

public class EmployeeService {
	AssignEmployeeDao ae = new AssignEmployeeDao();
	EmployeeLoginDao emp = new EmployeeLoginDao();
	UpdateSolutionDao u = new UpdateSolutionDao();
	ReplySolutionDao r = new ReplySolutionDao();

	public void reassignEmployee(String emailId, int empId, int ticketId) throws ServiceException {
		try {
				EmployeeValidator.reAssignEmployee(empId, ticketId);
				ae.assignNewEmployee(emailId,empId, ticketId);

			
		} catch (ValidationException e) {
			throw new ServiceException("Cannot assign!", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("Cannot assign!", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("Cannot assign!", e2);

		}

	}

	public void displayTicket(String emailId, String password) throws ServiceException {
		try {
			if (emp.login(emailId, password))

				ae.viewTicket(emailId, password);
		} catch (PersistanceException e) {
			throw new ServiceException("", e);

		} catch (DataAccessException e) {
			throw new ServiceException("", e);

		}
	}

	public void assignDefaultEmployee(int deptId) throws ServiceException {
		try {
		
				EmployeeValidator.assignEmployee(deptId);
				ae.assignEmployee(deptId);

			
		} catch (ValidationException e) {
			throw new ServiceException("", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("", e2);

		}

}

	public void replyTic(String emailId,String solution, int ticketId) throws ServiceException {
		try {
			
				EmployeeValidator.validateTicketId(solution, ticketId);
				r.replySolution(emailId,ticketId, solution);

			
		} catch (ValidationException e) {
			throw new ServiceException("Cannot reply please check!", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("Cannot reply please check!", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("Cannot reply please check!", e2);

		}
	}

	public void updateTic(String emailId,String solution, int ticketId) throws ServiceException {
		try {
			
				EmployeeValidator.validateTicketId(solution, ticketId);
				u.updateSolution(emailId, ticketId, solution);

			
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
