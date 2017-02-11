package com.aiswarya.test;

import java.util.Iterator;
import java.util.List;

import com.aiswarya.dao.TicketTransactionDao;
import com.aiswarya.dao.UserDao;
import com.aiswarya.exception.PersistanceException;
//import com.aiswarya.model.Departments;
//import com.aiswarya.model.Employee;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.model.User;
//import com.aiswarya.model.User;

public class TestTransacaction {

	public static void main(String[] args) throws PersistanceException {
		TicketTransaction tt = new TicketTransaction();
		TicketTransactionDao ttdao = new TicketTransactionDao();
		User user = new User();
		UserDao s1=new UserDao();
		System.out.println(ttdao.getTicketStatus(5));
		// Employee e = new Employee();
		// Departments d = new Departments();
		//tt.setId(1);
		//tt.setEmployeeId(1);
//		tt.setDescription("hsgfkgzf");
//		tt.setStatus("on progreSs");
//		ttdao.update(tt);
		// List<TicketTransaction> list = ttdao.listAll();
		// Iterator<TicketTransaction> i = list.iterator();
		// while (i.hasNext()) {
		// TicketTransaction tt1 = (TicketTransaction) i.next();
		// System.out.println(tt1.getId() + "\t" + tt1.getUserId().getId() +
		// "\t" + tt1.getSubject() + "\t"
		// + tt1.getDescription() + "\t" + tt1.getPriorityId().getId() + "\t"
		// + tt1.getCreatedDate().toLocalDate() + "\t" +
		// tt1.getResolvedDate().toLocalDate() + "\t"
		// + tt1.getDepartmentId().getId() + "\t" + tt1.getEmployeeId().getId()
		// + "\t" + tt1.getStatus());
		// }
//		 List<TicketTransaction> list = ttdao.listByEmpId(1);
//		 Iterator<TicketTransaction> i = list.iterator();
//		 while (i.hasNext()) {
//		 TicketTransaction tt1 = (TicketTransaction) i.next();
//		 System.out.println(tt1.getId() + "\t" + tt1.getUserId().getId() +
//		 "\t" + tt1.getSubject() + "\t"
//		 + tt1.getDescription() + "\t" + tt1.getPriorityId().getId() +"\t" + tt1.getStatus());
//		 }
		//ttdao.getTicketId(1)
	//	ttdao.getTId(1,9);

		
//System.out.println(ttdao.getTId(1,6).getId());
	}

}
