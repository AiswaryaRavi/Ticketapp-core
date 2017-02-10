package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.User;
import com.aiswarya.util.ConnectionUtil;

public class UserDao implements Dao<User> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(User user) throws PersistanceException {
		try {
			String sql = "insert into USERS(NAME,EMAIL_ID,PASSWORD) values(?,?,?)";
			Object[] params = { user.getName(), user.getEmailId(), user.getPassword() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistanceException("Given emailid already exists", e);
		} catch (DataIntegrityViolationException e) {
			throw new PersistanceException("Given emailid already exists", e);
		}

	}

	@Override
	public void update(User user) {
		String sql = "update USERS SET PASSWORD=? WHERE EMAIL_ID=?";
		Object[] params = { user.getPassword(), user.getEmailId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(User user) {
		String sql = "update USERS SET ACTIVE=? WHERE ID=?";
		Object[] params = { user.getActive(), user.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<User> listAll() {
		String sql = "select ID,NAME,EMAIL_ID,PASSWORD,ACTIVE from USERS";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));
	}

	private User convert(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setName(rs.getString("Name"));
		user.setEmailId(rs.getString("EMAIL_ID"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setActive(rs.getBoolean("ACTIVE"));
		return user;
	}

	public User getPassword(String emailid) throws PersistanceException {
		try {
			String sql = "SELECT PASSWORD FROM USERS WHERE EMAIL_ID=?";
			Object[] params = { emailid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				User user = new User();
				user.setPassword(rs.getString("PASSWORD"));
				return user;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("emailid does not exixts" , e);
		}
	}
	

	public Integer getId(String emailid,String password) throws PersistanceException {
		try {
		String sql = "SELECT ID FROM USERS WHERE EMAIL_ID=? and PASSWORD=?";
		Object[] params = { emailid,password };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
		catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("invalid username/password" , e);
		}
	}
	public Integer getUId(String emailid) throws PersistanceException {
		try {
		String sql = "SELECT ID FROM USERS WHERE EMAIL_ID=?";
		Object[] params = { emailid };
		return jdbcTemplate.queryForObject(sql, params, Integer.class);
	}
		catch (EmptyResultDataAccessException e) {
			throw new PersistanceException("invalid username/password" , e);
		}
	}

}
