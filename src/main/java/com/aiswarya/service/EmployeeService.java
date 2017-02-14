package com.aiswarya.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.aiswarya.dao.AssignEmployeeDao;
import com.aiswarya.dao.EmployeeLoginDao;
import com.aiswarya.dao.EmployeesDao;
import com.aiswarya.dao.ReplySolutionDao;
import com.aiswarya.dao.TicketTransactionDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.Employee;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.validator.EmployeeValidator;

public class EmployeeService {
	AssignEmployeeDao ae = new AssignEmployeeDao();
	EmployeeLoginDao emp = new EmployeeLoginDao();
	EmployeesDao employee = new EmployeesDao();
	TicketTransactionDao ttdao = new TicketTransactionDao();

	ReplySolutionDao r = new ReplySolutionDao();

	public void reassignEmployee(String emailId, int empId, int ticketId) throws ServiceException {
		try {
			EmployeeValidator.reAssignEmployee(empId, ticketId);
			ae.assignNewEmployee(emailId, empId, ticketId);

		} catch (ValidationException e) {
			throw new ServiceException("Cannot assign!", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("Cannot assign!", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("Cannot assign!", e2);

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

	public void replyTic(String emailId, String solution, int ticketId) throws ServiceException {
		try {

			EmployeeValidator.validateTicketId(solution, ticketId);
			r.replySolution(emailId, ticketId, solution);

		} catch (ValidationException e) {
			throw new ServiceException("Cannot reply please check!", e);
		}

		catch (PersistanceException e1) {
			throw new ServiceException("The ticket id you have mentioned is closed already!", e1);

		} catch (DataAccessException e2) {
			throw new ServiceException("Cannot reply please check!", e2);

		}
	}

	public List<TicketTransaction> DisplayEmployeeTickets(String emailid) throws ServiceException {
		try {
			int eid = employee.geteid(emailid);
			return ttdao.listByEmpId(eid);
		}

		catch (PersistanceException e) {
			throw new ServiceException("unable to show", e);

		} catch (DataAccessException e) {
			throw new ServiceException("Unable to show", e);

		}
	}

}
