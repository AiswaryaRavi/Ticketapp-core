package com.aiswarya.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Employee;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.util.ConnectionUtil;

public class AssignEmployeeDao {
	Logger logger = Logger.getLogger(TicketDao.class.getName());
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	TicketTransaction tt = new TicketTransaction();
	TicketTransactionDao ttdao = new TicketTransactionDao();
	EmployeesDao employeesDAO = new EmployeesDao();
	Employee e = new Employee();

	public void assignNewEmployee(String emailid,int empId, int ticketId)
			throws PersistanceException {
		try {

				ttdao.getTicketId(ticketId);
				String sql = "UPDATE TICKET_TRANSACTIONS SET ASSIGNED_EMPLOYEE_ID=?,STATUS='ON PROGRESS' WHERE ID=? AND STATUS<>'CLOSE'";
				Object[] params = { empId, ticketId };
				jdbcTemplate.update(sql, params);
				String sql1 = "update EMPLOYEES set STATUS='ASSIGNED' where ID=?";
				Object[] params1 = { empId };
				jdbcTemplate.update(sql1, params1);
			
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);
		}
	}

	
	public void assignEmployee(int deptId) throws PersistanceException {
		try {

			int employeeId = employeesDAO.getNotAssignedEmployeeId(deptId);
			String sql = "update EMPLOYEES set STATUS='ASSIGNED' where ID=? and DEPARTMENT_ID=?";
			Object[] params = { employeeId, deptId };
			jdbcTemplate.update(sql, params);
			logger.log(Level.INFO, "Emp Id is assigned" + employeeId);
			logger.log(Level.INFO, "Employee is assigned");
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("TicketId not exists", e);

		}
	}
}
