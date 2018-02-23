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
	/** The usual Logger. */
	private static final Logger logger = LoggerFactory.getLogger(BoardDao_refactoring.class);

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
				pstm.setString(9, board.getOriginalFileName());
				pstm.setString(10, board.getStoredFileName());
			}
		};
		String sql = "INSERT INTO boards"
				+ "(bGroup,bLevel,bOrder,id,writer,title,contents,viewcount,original_file_name,stored_file_name) VALUES(?,?,?,?,?,?,?,?,?,?)";
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
				board.setOriginalFileName(rs.getString(10));
				board.setStoredFileName(rs.getString(11));
				board.setTime(rs.getTimestamp(12));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
			}
		};
		String sql = "select * from boards order by bGroup desc, bOrder asc";
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
				board.setOriginalFileName(rs.getString(10));
				board.setStoredFileName(rs.getString(11));
				board.setTime(rs.getTimestamp(12));
				return board;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, search_key);
				pstmt.setString(2, search_value);
			}
		};
		String sql = "select * from boards where ? like '%?%' order by bGroup desc, bOrder asc";
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
				board.setViewCount(rs.getInt(9));
				board.setOriginalFileName(rs.getString(10));
				board.setStoredFileName(rs.getString(11));
				board.setTime(rs.getTimestamp(12));
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

	public String getOriginalFileName(String storedFileName) {
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				return rs.getString(1);
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				ps.setString(1, storedFileName);
			}
		};
		String sql = "select original_file_name from boards where stored_file_name=?";
		return (String)template.selectOneObject(sql);
	}
}

