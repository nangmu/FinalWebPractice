package main.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.Exception.MySqlException;
import main.java.VO.Board;


public class BoardDao_refactoring {
	private static final Logger logger = LoggerFactory.getLogger(BoardDao_refactoring.class);
	
	private BoardDao_refactoring(){}
	private static class Singleton{
		private static final BoardDao_refactoring instance= new BoardDao_refactoring();
	}
	public static BoardDao_refactoring getInstance(){
		return Singleton.instance;
	}
	
	public void insertBoard(Board board) throws MySqlException {
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstm) throws SQLException {
				pstm.setInt(1, board.getbGroup());
				pstm.setInt(2, board.getbLevel());
				pstm.setInt(3, board.getbOrder());
				pstm.setString(4, board.getId());
				pstm.setString(5, board.getWriter());
				pstm.setString(6, board.getTitle());
				pstm.setString(7, board.getContents());
				pstm.setInt(8, board.getViewCount());
			}
		};
		String sql = "INSERT INTO boards"
				+ "(bGroup,bLevel,bOrder,id,writer,title,contents,viewcount) VALUES(?,?,?,?,?,?,?,?)";
		template.update(sql);
	}

	public void deleteBoard(int bNum){
		Board board = getBoard(bNum);
		downOrder(board.getbGroup(), board.getbOrder());
		
		JdbcTemplate template = new JdbcTemplate() {
			
			@Override
			Object resultValue(ResultSet rs){
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bNum);
			}
		};
		String sql = "delete from boards where bNum=?";
		template.update(sql);
	}
	public void update(Board board) {

	}

	public int findUpdatedNum() {
	   JdbcTemplate template = new JdbcTemplate() {
		@Override
		Object resultValue(ResultSet rs) throws SQLException{
			int result = rs.getInt(1);
			return result;
		}
		@Override
		void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
		}
	};
	String sql = "select ifnull(max(bNum),1) from boards";
	return (int) template.selectOneObject(sql);
	}
	
	public int findUpdatedGroup(){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException{
				int result = rs.getInt(1);
				return result;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
			}
		};
		String sql = "select ifnull(max(bGroup),0) from boards";
		return (int) template.selectOneObject(sql);
	}

	public void upOrder(int bGroup, int bOrder){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return null;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bGroup);
				pstmt.setInt(2, bOrder);
			}
		};
		String sql = "UPDATE BOARDS SET bOrder=bOrder+1 " + "WHERE bGroup=? AND bOrder>?";
		template.update(sql);
	}
	
	public void downOrder(int bGroup, int bOrder){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return null;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bGroup);
				pstmt.setInt(2, bOrder);
			}
		};
		String sql = "UPDATE BOARDS SET bOrder=bOrder-1 " + "WHERE bGroup=? AND bOrder>?";
		template.update(sql);
	}
	public void upViewCount(int bNum){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return null;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1,bNum);
			}
		};
		String sql = "update boards set viewcount=viewcount+1 where bNum=?";
		template.update(sql);
	}

	public String getContents(int bNum){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				String contents = rs.getString(1);
				return contents;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bNum);
			}
		};
		
		String sql = "select contents from boards where bNum=?";
		return (String)template.selectOneObject(sql);
	}

	public ArrayList<Board> getAllBoards(){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setViewCount(rs.getInt(9));
				board.setTime(rs.getTimestamp(10));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
			}
		};
		String sql = "select * from boards order by bGroup desc, bOrder asc";
		return (ArrayList)template.selectManyObjects(sql);
	}
	// s번째부터 e번째 Board를 리턴.
	public ArrayList<Board> getBoards(int start, int nums){
		if(start<=0) return null;
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setViewCount(rs.getInt(9));
				board.setTime(rs.getTimestamp(10));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1,start-1);
				pstmt.setInt(2, nums);
			}
		};
		String sql = "select * from boards order by bGroup desc, bOrder asc limit ?,?";
		return (ArrayList)template.selectManyObjects(sql);
	}
	
	
	public ArrayList<Board> searchBoards(String search_key, String search_value){
		logger.debug("1:{}  2:{}",search_key,search_value);
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setViewCount(rs.getInt(9));
				board.setTime(rs.getTimestamp(10));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, search_value);
			}
		};
		String sql = "select * from boards"
				+ " where"+ search_key
				+ " like concat('%',?,'%') order by bGroup desc, bOrder asc";
		return (ArrayList)template.selectManyObjects(sql);	
	}
	public Board getBoard(int bNum){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				board.setId(rs.getString(5));
				board.setWriter(rs.getString(6));
				board.setTitle(rs.getString(7));
				board.setContents(rs.getString(8));
				board.setViewCount(rs.getInt(9));
				board.setTime(rs.getTimestamp(10));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bNum);
			}
		};
		String sql = "select * from boards where bNum=?";
		return (Board)template.selectOneObject(sql);
	}

	public int getTotalCount(){
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return rs.getInt(1);
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
			}
		};
		String sql = "select count(*) from boards";
		return (int)template.selectOneObject(sql);
	}

}

