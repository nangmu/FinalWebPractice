package main.java.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Exception.MySqlException;
import main.java.VO.Comment;
import main.java.VO.MyFile;
import main.java.VO.User;

public class FileDao {
	private FileDao(){}
	private static class Singleton{
		private static final FileDao instance= new FileDao();
	}
	public static FileDao getInstance(){
		return Singleton.instance;
	}
	
	public void insertFile(MyFile file)throws MySqlException{
		  JdbcTemplate template = new JdbcTemplate() {
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				  ps.setInt(1, file.getbNum());
				  ps.setString(2, file.getStoredFileName());
				  ps.setString(3, file.getOriginalFileName());
			}
			@Override
			User resultValue(ResultSet rs) {
				return null;
			}
		};
		template.update("INSERT INTO board_file(bNum,storedFileName,originalFileName) VALUES(?,?,?)");
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
		String sql = "select originalFileName from board_file where storedFileName=?";
		return (String)template.selectOneObject(sql);
	}

	public ArrayList<MyFile> getFiles(int bNum) {
		JdbcTemplate template = new JdbcTemplate() {
			@Override
			Object resultValue(ResultSet rs) throws SQLException {
				int fNum = rs.getInt(1);
				MyFile file = new MyFile(rs.getInt(2), rs.getString(3), rs.getString(4));
				file.setfNum(fNum);
				return file;
			}
			
			@Override
			void PreparedStatementMapping(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bNum);
			}
		};
		String sql = "select * from board_file where bNum=?";
		return (ArrayList)template.selectManyObjects(sql);
	}
}
