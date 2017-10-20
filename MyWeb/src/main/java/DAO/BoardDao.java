package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.Exception.MySqlException;
import main.java.VO.Board;

//글 추가,삭제,수정
public class BoardDao {
	/** The usual Logger. */
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
	// 글 하나 등록
	public void insert(Board board) throws MySqlException {
		String sql = "INSERT INTO boards"
				+ "(bGroup,bLevel,bOrder,id,writer,title,contents,viewcount) VALUES(?,?,?,?,?,?,?,?)";
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, board.getbGroup());
			pstm.setInt(2, board.getbLevel());
			pstm.setInt(3, board.getbOrder());
			pstm.setString(4, board.getId());
			pstm.setString(5, board.getWriter());
			pstm.setString(6, board.getTitle());
			pstm.setString(7, board.getContents());
			pstm.setInt(8, board.getViewCount());
			pstm.executeUpdate();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
	}

	public void update(Board board) {

	}
	public int findUpdatedGroup() throws SQLException, NamingException{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();

		String sql = "select ifnull(max(bGroup),0) from boards";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		int result =0;
		if(rs.next()) result = rs.getInt(1);
		
		pstmt.close();
		conn.close();
		return result;
	}
	
	public void changeOrder(int bGroup, int bOrder) throws SQLException, NamingException {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();

		String sql = "UPDATE BOARDS SET bOrder=bOrder+1 " + "WHERE bGroup=? AND bOrder>?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bGroup);
		pstmt.setInt(2, bOrder);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void upViewCount(int bNum) throws NamingException, SQLException {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();

		String sql = "update boards set viewcount=viewcount+1 where bNum=" + bNum;
		Statement st = conn.createStatement();
		st.executeUpdate(sql);

		st.close();
		conn.close();
	}

	public String getContents(int bNum) throws NamingException, SQLException {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/web");
		Connection conn = ds.getConnection();
		String sql = "select contents from boards where bNum=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, bNum);
		ResultSet rs = pstm.executeQuery();
		String contents = null;
		if (rs.next()) {
			contents = rs.getString(1);
		}
		rs.close();
		pstm.close();
		conn.close();
		return contents;
	}

	public Board getBoard(int bNum) throws MySqlException {
		String sql = "select * from boards where bNum=?";
		Board board = null;
		ResultSet rs=null;
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, bNum);
			rs = pstm.executeQuery();
			if (rs.next()) {
				board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setViewCount(rs.getInt(9));
			}
			if (rs != null)
				rs.close();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
		return board;
	}

	public ArrayList<Board> getAllBoards() throws MySqlException {
		
		String sql = "select * from boards order by bGroup desc, bOrder asc";
		ArrayList<Board> boardList =null;
		ResultSet rs=null;
		try (Connection conn = ((DataSource) (new InitialContext()).lookup("java:comp/env/jdbc/web")).getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			rs = pstm.executeQuery();
			boardList = new ArrayList<>();
			while (rs.next()) {
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setViewCount(rs.getInt(9));
				boardList.add(board);
			}
			if(rs!=null)
			rs.close();
		} catch (NamingException n) {
			n.printStackTrace();
		} catch (SQLException s) {
			throw new MySqlException(s);
		}
		return boardList;
	}
}
