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

import main.java.VO.User;

public class UserDao {
	public void insert(User user){
		  
	}
	public void update(){
		
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
		  //resource «ÿ¡¶
		  return user;
	}
	
	public ArrayList<User> getAllUsers(){
		return null;
	}
}
