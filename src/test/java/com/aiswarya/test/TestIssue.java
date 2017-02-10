package com.aiswarya.test;

import java.util.Iterator;
import java.util.List;

import com.aiswarya.dao.IssueSolutionDao;
import com.aiswarya.dao.TicketTransactionDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.IssuesSolutions;
import com.aiswarya.model.TicketTransaction;

public class TestIssue {

	public static void main(String[] args) throws PersistanceException {
		 IssuesSolutions issue = new IssuesSolutions();
		IssueSolutionDao idao = new IssueSolutionDao();
		TicketTransaction t=new TicketTransaction();
		TicketTransactionDao tt=new TicketTransactionDao();
//		List<IssuesSolutions> list = idao.listAll();
//		Iterator<IssuesSolutions> i = list.iterator();
//		while (i.hasNext()) {
//			IssuesSolutions tt1 = (IssuesSolutions) i.next();
//			System.out.println(tt1.getId() + "\t" + tt1.getTicketId().getId() + "\t" + tt1.getSolution());
//
//		}
		t.setId(1);
		issue.setTicketId(t);
		issue.setSolution("shmjhsf");
		idao.save(issue);
	}

}
