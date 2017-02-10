package com.aiswarya.test;

import java.util.List;
import java.util.Iterator;

import com.aiswarya.dao.AdminDao;
import com.aiswarya.dao.AssignEmployeeDao;
import com.aiswarya.dao.LoginDao;
import com.aiswarya.dao.ReplySolutionDao;
import com.aiswarya.dao.TicketDao;
import com.aiswarya.dao.TicketTransactionDao;
import com.aiswarya.dao.UpdateSolutionDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.TicketTransaction;
import com.aiswarya.model.User;
//import com.aiswarya.dao.LoginDao;

public class TestLogin {

	public static void main(String[] args) throws PersistanceException {
		 //LoginDao login = new LoginDao();
		// System.out.println(login.login("aiswaryaravindran240@gmail.com", "ai0805"));
//		User u = new User();
//		u.setId(1);
//		int userid = u.getId();
//		TicketTransactionDao tdao = new TicketTransactionDao();
	TicketDao c = new TicketDao();
	AssignEmployeeDao ae=new AssignEmployeeDao();
	//UpdateSolutionDao u=new UpdateSolutionDao();
//	u.replySolution("usha@gmail.com","usha567", 4, "hi");
	//ReplySolutionDao r=new ReplySolutionDao();
	//r.replySolution("aiswaryaravindran240@gmail.com", "kuju123",2, "ghfhfdm");
//	
//	AdminDao ad=new AdminDao();
//	ad.AdminDelete("usha@gmail.com", "usha567", 1);
		//

		//c.requestTicket("aiswaryaravindran240@gmail.com", "gjgjgdvfj", "Mhsbdk", "HR", "LOW");
		// c.updateTicket("aiswaryaravindran240@gmail.com","ais0805",2, "zbjhgdjhjha,kdhkvk");
	//	 c.updateStatus("aiswaryaravindran240@gmail.com","ais0805",3);
//		List<TicketTransaction> list = tdao.listById(userid);
//		Iterator<TicketTransaction> i = list.iterator();
//		while (i.hasNext()) {
//			TicketTransaction tt1 = (TicketTransaction) i.next();
//			System.out.println(tt1.getId()+"\t" + tt1.getUserId().getId() + "\t" + tt1.getSubject()+ "\t"+ tt1.getDescription() + "\t" + tt1.getPriorityId().getId()+ "\t"+ tt1.getCreatedDate().toLocalDate()+ "\t"+ tt1.getDepartmentId().getId()  + "\t" + tt1.getStatus());

			// }
	//c.displayTicket("aiswaryaravindrn240@gmail.com","ais0805");
	ae.assignNewEmployee("aiswaryaravindran240@gmail.com",11,34);
	//ae.viewTicket("ush@gmail.com","usha567");
	//ae.assignNewEmployee("usa@gmail.com", "usha567", 7, 3);
		}

	}

