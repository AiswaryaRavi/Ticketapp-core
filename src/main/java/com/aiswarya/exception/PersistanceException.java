package com.aiswarya.exception;

import java.sql.SQLException;

public class PersistanceException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistanceException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public PersistanceException(String arg0) {
		super(arg0);

	}

	

}
