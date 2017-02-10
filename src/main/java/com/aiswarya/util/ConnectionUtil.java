package com.aiswarya.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {
	private ConnectionUtil() {
		throw new IllegalAccessError("Utility class");
	}

	public static DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("dev_user");
		ds.setPassword("dev_pwd");
		ds.setUrl("jdbc:mysql://139.59.29.57:3306/aishwarya_db");
		return ds;
	}

	public static JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jt = new JdbcTemplate();
		jt.setDataSource(getDataSource());
		return jt;
	}
}
