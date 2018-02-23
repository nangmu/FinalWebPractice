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

import main.java.Exception.MySqlException;

abstract public class JdbcTemplate {
	Context init = null;
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement pstm = null;

	public void update(String sql) throws MySqlException {
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			PreparedStatementMapping(pstm);
			pstm.executeUpdate();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
			throw new MySqlException();
		}
	}

	public Object selectOneObject(String sql) throws MySqlException {
		Object object = null;
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			PreparedStatementMapping(pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				object = resultValue(rs);
			}
			if (rs != null)
				rs.close();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
			throw new MySqlException();
		}
		return object;
	}

	public List<Object> selectManyObjects(String sql) throws MySqlException {
		ArrayList<Object> list = null;
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			PreparedStatementMapping(pstm);
			ResultSet rs = pstm.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {
				list.add(resultValue(rs));
			}
			if (rs != null) {
				rs.close();
			}
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException();
		}
		return list;
	}

	abstract void PreparedStatementMapping(PreparedStatement ps) throws SQLException;
	abstract Object resultValue(ResultSet rs) throws SQLException;
}
