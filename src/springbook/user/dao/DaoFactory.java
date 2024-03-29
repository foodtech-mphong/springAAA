package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory
{
	@Bean
	public UserDao userDao()
	{
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	
	}

	@Bean
	public DataSource dataSource()
	{
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mariadb://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		
		return dataSource;
	}
	
	
}