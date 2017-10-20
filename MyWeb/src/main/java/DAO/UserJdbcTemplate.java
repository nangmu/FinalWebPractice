package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

abstract public class UserJdbcTemplate {
	public void update() throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection(); 
		String sql = createQuery();
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		PreparedStatementMapping(pstm);
		pstm.executeUpdate();
		pstm.close();
		conn.close();
	}
	
	public Object selectOneObject(String id)throws SQLException, NamingException{
		  Context init = new InitialContext();
		  DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		  Connection conn = ds.getConnection();
		  String sql = createQuery();
		  PreparedStatement pstm = conn.prepareStatement(sql);
		  PreparedStatementMapping(pstm);
		  ResultSet rs = pstm.executeQuery();
		  Object object = null;
		  if(rs.next()){
			  object = resultValue(rs);
		  }
		  rs.close();
		  pstm.close();
		  conn.close();
		  return object;
	}
	
	public List<Object> selectManyObjects()throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();
		String sql = createQuery();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		ArrayList<Object> list = new ArrayList<>();
		while(rs.next()){
			list.add(resultValue(rs));
		}
		
		rs.close();
		pstm.close();
		conn.close();
		return list;
	}
	
	abstract String createQuery();
	abstract void PreparedStatementMapping(PreparedStatement ps) throws SQLException;
	abstract Object resultValue(ResultSet rs) throws SQLException;
}
