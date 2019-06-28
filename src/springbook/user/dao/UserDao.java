package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDao 
{
	private static UserDao INSTANCE;
	
	private ConnectionMaker connectionMaker;
	
	private UserDao(ConnectionMaker connectionMaker)
	{
		this.connectionMaker = connectionMaker;
	}

	public static synchronized UserDao getInstance()
	{
		if(INSTANCE == null)
			INSTANCE = new UserDao(???);
		
		return INSTANCE;
	}
	
	public void add(User user)
	throws ClassNotFoundException, SQLException
	{
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("insert into test.users(id, name, password) values(?, ?, ?)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		ps.close();
		c.close();
	}
	
	public User get(String id)
	throws ClassNotFoundException, SQLException
	{
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select * from test.users where id = ?");
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs .next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close ();
		ps.close();
		c.close();
		
		return user;
	}

/***
	private Connection getConnection()
	throws ClassNotFoundException, SQLException
	{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
		
		return c;
	}
***/					
}