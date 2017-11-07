package main.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Exception.MySqlException;
import main.java.VO.Comment;
import main.java.VO.User;
public class CommentDao {
	private CommentDao(){}
	private static class Singleton{
		private static final CommentDao instance= new CommentDao();
	}
	public static CommentDao getInstance(){
		return Singleton.instance;
	}
	
	public void insertComment(Comment comment)throws MySqlException{
		  JdbcTemplate template = new JdbcTemplate() {
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				  ps.setInt(1, comment.getbNum());
				  ps.setString(2, comment.getContents());
				  ps.setString(3, comment.getWriter());
			}
			@Override
			User resultValue(ResultSet rs) {
				return null;
			}
		};
		template.update("INSERT INTO comment(bNum,contents,writer,time) VALUES(?,?,?,now())");
	}
	
	public ArrayList<Comment> getComments(int bNum)throws MySqlException{
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				Comment comment = new Comment(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5));
				return comment;
			}
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bNum);
			}
		};
		String sql = "select * from comment where bNum=? order by time";
		return (ArrayList)template.selectManyObjects(sql);
	}
}
