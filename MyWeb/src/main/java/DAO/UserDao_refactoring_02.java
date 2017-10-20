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

public class UserDao_refactoring_02 {
	public void insert(User user) throws NamingException, SQLException{
		  UserJdbcTemplate template = new UserJdbcTemplate() {
			@Override
			String createQuery() {
				  String sql = "INSERT INTO users VALUES(?,?,?,now())";
				return sql;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				  ps.setString(1, user.getId());
				  ps.setString(2, user.getPw());
				  ps.setString(3, user.getName());
			}
			@Override
			User resultValue(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		template.update();
	}
	
	public User getUser(String id)throws SQLException, NamingException{
		UserJdbcTemplate template = new UserJdbcTemplate() {
			
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				User user = null;  
				if(rs.next()){
					  user= new User(rs.getString(1),rs.getString(2),rs.getString(3));
				 }
				return user;
			}
			@Override
			String createQuery() {
				String sql = "select id,pw,name from users where id=?";
				return sql;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				 ps.setString(1, id);
			}
		};
		return (User)template.selectOneObject(id);
	}
	
	public ArrayList<Object> getAllUsers()throws SQLException, NamingException{
		UserJdbcTemplate template = new UserJdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return new User(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			@Override
			String createQuery() {
				return  "select * from users";
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				
			}
		};
		return (ArrayList)template.selectManyObjects();
	}
}
