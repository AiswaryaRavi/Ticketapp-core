package com.aiswarya.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Role;
import com.aiswarya.util.ConnectionUtil;

public class RoleDAO implements Dao<Role> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Role r) throws PersistanceException {
		try {
			String sql = "insert into ROLES(NAME)values(?)";
			Object[] params = { r.getName() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistanceException("Given role already exists", e);
		}

	}

	@Override
	public void update(Role r) {
		String sql = "update ROLES SET NAME=? WHERE ID=?";
		Object[] params = { r.getName(), r.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public void updateAsInactive(Role r) {
		String sql = "update ROLES SET ACTIVE=? WHERE ID=?";
		Object[] params = { r.getActive(), r.getId() };
		jdbcTemplate.update(sql, params);

	}

	@Override
	public List<Role> listAll() {
		String sql = "select ID,NAME,ACTIVE from ROLES";
		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));

	}

	private Role convert(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("ID"));
		role.setName(rs.getString("NAME"));
		role.setActive(rs.getBoolean("ACTIVE"));
		return role;
	}

	

}
