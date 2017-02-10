package com.aiswarya.service;

import org.springframework.dao.DataAccessException;

import com.aiswarya.dao.TicketDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.exception.ServiceException;
import com.aiswarya.exception.ValidationException;
import com.aiswarya.validator.UserValidator;

public class TicketService {
	private UserService u = new UserService();
	TicketDao t = new TicketDao();

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
				t.updateStatus(emailid,id);
			} catch (ValidationException e) {
				throw new ServiceException("Updation failed", e);

			}

			catch (PersistanceException e) {
				throw new ServiceException("Updation failed", e);

			} catch (DataAccessException e) {
				throw new ServiceException("Updation failed", e);

			}
		
	}

	public void displayTicket(String emailid, String password) throws ServiceException {
		if (u.login(emailid, password)) {
			try {

				t.displayTicket(emailid, password);
			} catch (PersistanceException e) {
				throw new ServiceException("", e);

			} catch (DataAccessException e) {
				throw new ServiceException("", e);

			}
		}
	}

}
