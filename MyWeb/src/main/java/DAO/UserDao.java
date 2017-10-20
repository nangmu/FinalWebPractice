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

import main.java.Exception.MySqlException;
import main.java.VO.User;

public class UserDao {
	/*[[update, insert => 처리만 해주면 된다]]
	 * 변경부분: Sql Query, 매개변수, 매개변수 값을 statement로 세팅하는 부분
	 */
	public void insert(User user) throws MySqlException {
		String sql = "INSERT INTO users VALUES(?,?,?,now())";
		
		try(Connection conn = ((DataSource)(new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, user.getId());
			pstm.setString(2, user.getPw());
			pstm.setString(3, user.getName());
			pstm.executeUpdate();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
	}
	public void update(User user)throws MySqlException{
		String sql = "UPDATE users SET pw=?,name=? WHERE id=?";
		try(Connection conn = ((DataSource)new InitialContext().lookup("java:comp/env/jdbc/web")).getConnection();
				 PreparedStatement pstm = conn.prepareStatement(sql)){
			  pstm.setString(1, user.getPw());
			  pstm.setString(2, user.getName());
			  pstm.setString(3, user.getId());
			  pstm.executeUpdate();
		}catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
		
	}

	public User getUser(String id) throws MySqlException {
		String sql = "select id,pw,name from users where id=?";
		User user = null;
		try (Connection conn = ((DataSource) new InitialContext().lookup("java:comp/env/jdbc/web")).getConnection();
			 PreparedStatement pstm = conn.prepareStatement(sql)) {

			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			if(rs!=null)
			rs.close();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
		return user;
	}
	
	public ArrayList<User> getAllUsers() throws MySqlException {
		String sql = "select * from users";
		ArrayList<User> userList = null;
		try (Connection conn = ((DataSource) new InitialContext().lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			ResultSet rs = pstm.executeQuery();
			userList = new ArrayList<>();
			while (rs.next()) {
				userList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			if(rs!=null)
			rs.close();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
		return userList;
	}
}
