package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Departments;
import com.aiswarya.model.Employee;
import com.aiswarya.model.Role;

import com.aiswarya.util.ConnectionUtil;

public class EmployeesDao implements Dao<Employee> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(TicketDao.class.getName());

	@Override
	public void save(Employee emp) throws PersistanceException {
		try {
			String sql = "insert into EMPLOYEES(DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD) values(?,?,?,?,?)";
			Object[] params = { emp.getDepartmentId().getId(), emp.getRole().getId(), emp.getName(), emp.getEmailId(),
					emp.getPassword() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistanceException("Given emailid already exists", e);
		}

	}

	@Override
	public void update(Employee emp) {
		String sql = "update EMPLOYEES SET PASSWORD=? WHERE EMAIL_ID=?";
		Object[] params = { emp.getPassword(), emp.getEmailId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(Employee emp) {
		String sql = "update EMPLOYEES SET ACTIVE=? WHERE ID=?";
		Object[] params = { emp.getActive(), emp.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Employee> listAll() {
		String sql = "SELECT ID,DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD,ACTIVE from EMPLOYEES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));

	}

	private Employee convert(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		Role role = new Role();
		Departments dept = new Departments();
		employee.setId(rs.getInt("ID"));
		dept.setId(rs.getInt("DEPARTMENT_ID"));
		employee.setDepartmentId(dept);
		role.setId(rs.getInt("ROLE_ID"));
		employee.setRole(role);
		employee.setName(rs.getString("NAME"));
		employee.setEmailId(rs.getString("EMAIL_ID"));
		employee.setPassword(rs.getString("PASSWORD"));
		employee.setActive(rs.getBoolean("ACTIVE"));
		return employee;
	}

	public Employee getPassword(String emailid) throws PersistanceException {
		try {
			String sql = "SELECT PASSWORD FROM EMPLOYEES WHERE EMAIL_ID=?";
			Object[] params = { emailid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Employee emp = new Employee();
				emp.setPassword(rs.getString("PASSWORD"));
				return emp;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("emailid does not exixts", e);
		}
	}

	public int getNotAssignedEmployeeId(int id) throws PersistanceException {
		try {
			String sql = "select ID from EMPLOYEES where DEPARTMENT_ID=? AND ROLE_ID=4";
			Object[] params = { id };
			return (int) jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Employee unavailable currently", e);
		}
	}

	public Integer getId(String emailid,String password) throws PersistanceException {
		try {
			String sql = "SELECT ID FROM EMPLOYEES WHERE EMAIL_ID=? and PASSWORD=?";
			Object[] params = { emailid,password };
			return jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("INVALID USERNAME/PASSWORD", e);
		}

	}
	public Integer geteid(String emailid) throws PersistanceException {
		try {
			String sql = "SELECT ID FROM EMPLOYEES WHERE EMAIL_ID=?";
			Object[] params = { emailid};
			return jdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("INVALID USERNAME", e);
		}

	}


	public Employee getEmpId(int empid) throws PersistanceException {
		try {
			String sql = "SELECT ID FROM EMPLOYEES WHERE ID=? and ROLE_ID=1";
			Object[] params = { empid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Employee e = new Employee();
				Role r = new Role();
				r.setId(rs.getInt("ID"));
				e.setRole(r);
				return e;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Your access is denied", e);
		}

	}
	public Employee getAll(int deptid) throws PersistanceException {
		try {
			String sql = "SELECT * from EMPLOYEES WHERE DEPARTMENT_ID=? AND ROLE_ID=4";
			Object[] params = { deptid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Employee employee = new Employee();
				Role role = new Role();
				Departments dept = new Departments();
				employee.setId(rs.getInt("ID"));
				dept.setId(rs.getInt("DEPARTMENT_ID"));
				employee.setDepartmentId(dept);
				role.setId(rs.getInt("ROLE_ID"));
				employee.setRole(role);
				employee.setName(rs.getString("NAME"));
				employee.setEmailId(rs.getString("EMAIL_ID"));
				employee.setPassword(rs.getString("PASSWORD"));
				employee.setActive(rs.getBoolean("ACTIVE"));
				return employee;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("Department id does not exists", e);
		}

	}
	
	

}
