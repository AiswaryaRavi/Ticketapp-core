package com.aiswarya.test;

import java.util.List;

import com.aiswarya.dao.PriorityDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.Priority;

public class TestPriority {

	public static void main(String[] args) throws PersistanceException {
		Priority p = new Priority();
		PriorityDao pdao = new PriorityDao();

		// p.setName("LOW");
		// pdao.save(p);

		// To update the table
		// p.setName("DEVELOPER");
		// p.setId(2);
		// pdao.update(p);

		// To display table
		List<Priority> list = pdao.listAll();
		for (Priority i : list) {
			System.out.println(i);
		}

	}

}
