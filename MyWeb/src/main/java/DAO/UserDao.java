package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.LogbackException;
import main.java.VO.User;

public class UserDao {
	
	public void insert(User user) throws NamingException, SQLException{
		  Context init = new InitialContext();
		  DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		  Connection conn = ds.getConnection(); 
		
		  String sql = "INSERT INTO users VALUES(?,?,?,now())";
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  pstm.setString(1, user.getId());
		  pstm.setString(2, user.getPw());
		  pstm.setString(3, user.getName());
		  
		  pstm.executeUpdate();
		  
		  pstm.close();
		  conn.close();
	}
	public void update(User user) throws NamingException, SQLException{
		 DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/web");
		  Connection conn = ds.getConnection();
		  String sql = "UPDATE users SET pw=?,name=? WHERE id=?";
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  pstm.setString(1, user.getPw());
		  pstm.setString(2, user.getName());
		  pstm.setString(3, user.getId());
		  
		  pstm.executeUpdate();
		  
		  pstm.close();
		  conn.close();
	}
	public User getUser(String id)throws SQLException, NamingException{
		  Context init = new InitialContext();
		  DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		  Connection conn = ds.getConnection();
		  String sql = "select id,pw,name from users where id=?";
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  pstm.setString(1, id);
		  ResultSet rs = pstm.executeQuery();
		  User user = null;
		  if(rs.next()){
			  user = new User(rs.getString(1),rs.getString(2),rs.getString(3));
		  }
		  rs.close();
		  pstm.close();
		  conn.close();
		  return user;
	}
	
	public ArrayList<User> getAllUsers()throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();
		String sql = "select * from users";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<User> userList = new ArrayList<>();
		while(rs.next()){
			userList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
		  rs.close();
		  pstm.close();
		  conn.close();
		return userList;
	}
}
