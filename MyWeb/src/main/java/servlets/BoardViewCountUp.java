package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao;

@WebServlet(urlPatterns="/boardViewCountUp")
public class BoardViewCountUp extends HttpServlet{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(WriteBoard.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException {
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));
		BoardDao boardDao = new BoardDao();
		boardDao.upViewCount(parent_bNum);
				
		resp.sendRedirect("/readBoard?bNum="+parent_bNum);
	}
}
