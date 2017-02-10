package com.aiswarya.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.logging.Level;

import java.util.Iterator;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Departments;
import com.aiswarya.model.Priority;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.model.User;
import com.aiswarya.util.ConnectionUtil;
import com.aiswarya.util.MailUtil;

public class TicketDao {
	User user = new User();
	UserDao u = new UserDao();
	TicketTransaction tt = new TicketTransaction();
	TicketTransactionDao ttdao = new TicketTransactionDao();
	Logger logger = Logger.getLogger(TicketDao.class.getName());
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	EmployeesDao e = new EmployeesDao();

	public void requestTicket(String emailid, String subject, String description, String department, String priority)
			throws PersistanceException {
		Departments dept = new Departments();
		Priority p = new Priority();
		DepartmentDao deptdao = new DepartmentDao();
		PriorityDao priordao = new PriorityDao();
		AssignEmployeeDao ae = new AssignEmployeeDao();

		int deptId = deptdao.getDepartmentId(department).getId();
		int priorId = priordao.getPriorityId(priority).getId();
		int userid = u.getUId(emailid);
		System.out.println(userid);

		dept.setId(deptId);
		p.setId(priorId);

		user.setId(userid);
		tt.setUserId(user);

		tt.setSubject(subject);
		tt.setDescription(description);

		tt.setPriorityId(p);
		tt.setDepartmentId(dept);

		ttdao.save(tt);

		ae.assignEmployee(deptId);
		int tid = ttdao.getTickId(userid, subject, description, deptId, priorId);
		String email = e.getAll(deptId).getEmailId();
		System.out.println(tid);
		System.out.println(email);
		try {
			MailUtil.sendSimpleMail(emailid, "Ticket Created Sucessfully.Your Ticket id is:", tid);
			MailUtil.sendSimpleMail(email, "A ticket has been created. The issue id is:", tid);
		} catch (Exception e) {

		}

	}

	public void updateTicket(String emailid, Integer id, String description) throws PersistanceException {
		if (ttdao.getStatus(id).getStatus().equals("RESOLVED") || ttdao.getStatus(id).getStatus().equals("OPEN")
				|| ttdao.getStatus(id).getStatus().equals("ON PROGRESS")) {
			tt.setId(id);
			tt.setDescription(description);
			tt.setStatus("on progress");
			ttdao.update(tt);
		}
	}

	public void updateStatus(String emailid,Integer id) throws PersistanceException {
		TicketTransaction tt = new TicketTransaction();
		TicketTransactionDao ttdao = new TicketTransactionDao();

		if (ttdao.getStatus(id).getStatus().equals("RESOLVED") || ttdao.getStatus(id).getStatus().equals("OPEN")
				|| ttdao.getStatus(id).getStatus().equals("ON PROGRESS")) {

			tt.setId(id);
			tt.setStatus("closed");
			ttdao.updateStatus(tt);
		}


	}

	public void displayTicket(String emailid, String password) throws PersistanceException {

		LoginDao login = new LoginDao();
		if (login.login(emailid, password)) {
			int id = ttdao.getId(emailid).getId();
			user.setId(id);

			tt.setUserId(user);
			ttdao.listById(user.getId());
			List<TicketTransaction> list = ttdao.listById(id);
			Iterator<TicketTransaction> i = list.iterator();
			while (i.hasNext()) {
				TicketTransaction tt1 = (TicketTransaction) i.next();
				logger.log(Level.INFO, tt1.getId() + "\t" + tt1.getUserId().getId() + "\t" + tt1.getSubject() + "\t"
						+ tt1.getDescription() + "\t" + tt1.getStatus());

			}

		}

	}

}
