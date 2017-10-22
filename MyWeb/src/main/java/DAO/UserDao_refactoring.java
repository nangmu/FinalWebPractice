package main.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Exception.MySqlException;
import main.java.VO.User;
/*
 * ���ø� �޼ҵ� ������ ����� �ߺ� ����
 */
public class UserDao_refactoring {
	public void insertUser(User user)throws MySqlException{
		  JdbcTemplate template = new JdbcTemplate() {
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
		template.update("INSERT INTO users VALUES(?,?,?,now())");
	}
	
	public User getUser(String id)throws MySqlException{
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
					return new User(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				 ps.setString(1, id);
			}
		};
		return (User)template.selectOneObject("select id,pw,name from users where id=?");
	}
	
	public ArrayList<User> getAllUsers()throws MySqlException{
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return new User(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
			}
		};
		return (ArrayList)template.selectManyObjects("select * from users");
	}
}