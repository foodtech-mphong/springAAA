package springbook.user;

import java.sql.SQLException;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args)
	throws ClassNotFoundException, SQLException
	{
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
	
		User user = new User();
		user.setId("ship13");
		user.setName("홍민표");
		user.setPassword("married");

		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		
		
		
		if(!user.getName().equals(user2.getName()))
		{
			System.out.println("테스트 실패 (name)");
		}
		else if(!user.getPassword().equals(user2.getPassword()))
		{
			System.out.println("테스트 실패 (password)");
		}
		else
		{
			System.out.println("조회 테스트 성공");
		}
	}

	
	@Test
	public void addAndGet()
	throws ClassNotFoundException, SQLException
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		//dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		User user = new User();
		user.setId("test4");
		user.setName("박테스트");
		user.setPassword("pwtest");
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));
		
		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
		
	}
	
}
