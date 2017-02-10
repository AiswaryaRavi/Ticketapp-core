package com.aiswarya.util;

import java.util.List;

import com.aiswarya.dao.UserDao;
import com.aiswarya.exception.PersistanceException;
import com.aiswarya.model.User;

public class TestUserDao {

	public static void main(String[] args) throws PersistanceException {
		UserDao userdao = new UserDao();
		User user = new User();

		// To insert into table
//		user.setName("ais");
//		user.setEmailId("aiswaryaravi240@gmail.com");
//		user.setPassword("ais0805");
//		userdao.save(user);
		
		//To update the table
		user.setEmailId("a@gmail.com");
		user.setPassword("ais");
		userdao.update(user);
		
		List<User> list = userdao.listAll();
		for (User i : list) {
			System.out.println(i);
}

	}

}
