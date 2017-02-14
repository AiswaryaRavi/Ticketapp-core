package com.aiswarya.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.aiswarya.dao.TicketDao;
import com.aiswarya.dao.TicketTransactionDao;
import com.aiswarya.dao.UserDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.model.User;
import com.aiswarya.validator.UserValidator;

public class TicketService {
	private UserService u = new UserService();
	TicketDao t = new TicketDao();
	UserDao user = new UserDao();
	TicketTransactionDao ttdao = new TicketTransactionDao();

	public void ticketCreation(String emailid, String subject, String description, String department, String priority)
			throws ServiceException {

		try {
			UserValidator.ticketCreation(subject, description, department, priority);
			t.requestTicket(emailid, subject, description, department, priority);

		} catch (ValidationException e) {
			throw new ServiceException("unable to create", e);
		}

		catch (PersistanceException e) {
			throw new ServiceException("unable to create", e);

		} catch (DataAccessException e) {
			throw new ServiceException("unable to create", e);

		}

	}

	public void ticketUpdation(String emailid, Integer id, String description) throws ServiceException {
		try {
			UserValidator.ticketUpdation(id, description);
			t.updateTicket(emailid, id, description);
		} catch (ValidationException e) {
			throw new ServiceException("Updation failed", e);

		}

		catch (PersistanceException e) {
			throw new ServiceException("updation failed", e);
		} catch (DataAccessException e) {
			throw new ServiceException("updation failed", e);

		}

	}

	public void closeTicket(String emailid, Integer id) throws ServiceException {
		try {
			UserValidator.ticketUpdateStatus(id);
			System.out.println("hi");
			t.updateStatus(emailid, id);
		} catch (ValidationException e) {
			throw new ServiceException("Updation failed", e);

		}

		catch (PersistanceException e) {
			throw new ServiceException("Updation failed", e);

		} catch (DataAccessException e) {
			throw new ServiceException("Updation failed", e);

		}

	}

	public List<TicketTransaction> DisplayUserTickets(String emailid) throws ServiceException {
		try{
		int uid = user.getUId(emailid);
		System.out.println("hi");
		return ttdao.listById(uid);
		}

		catch (PersistanceException e) {
			throw new ServiceException("unable to show", e);

		} catch (DataAccessException e) {
			throw new ServiceException("Unable to show", e);

		}
	}
	

}
