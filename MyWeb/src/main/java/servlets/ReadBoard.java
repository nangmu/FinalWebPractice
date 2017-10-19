package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

@WebServlet(urlPatterns="/readBoard")
public class ReadBoard extends HttpServlet{
private static final Logger logger = LoggerFactory.getLogger(LoginCheck.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, NamingException, SQLException{
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));
		
		BoardDao boardDao = new BoardDao();
		Board parent_board = boardDao.getBoard(parent_bNum);
		
		req.setAttribute("board", parent_board);
		String path = "/readView.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req,resp);
	}
}
