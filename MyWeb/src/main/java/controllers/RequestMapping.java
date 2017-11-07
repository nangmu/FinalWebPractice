package main.java.controllers;

import java.util.HashMap;

public class RequestMapping {
	private static HashMap<String,Controller> map = new HashMap<>();
	
	void init(){
		map.put("/", new ForwardController("/home.jsp"));
		map.put("/loginForm", new ForwardController("/login.jsp"));
		map.put("/signupForm", new ForwardController("/signup.jsp"));
		map.put("/write_rb_Form", new ForwardController("/write_replyboard.jsp"));
		map.put("/answer_rb_Form", new ForwardController("/answer_replyboard.jsp"));
		map.put("/detail_rb_Form", new ForwardController("/detail_replyboard.jsp"));
		map.put("/login", new LoginController());
		map.put("/signup", new SignupController());
		map.put("/userlist", new UserList_Show());
		map.put("/replyboard", new ReplyBoard_Show());
		map.put("/detail_rb", new ReplyBoard_ShowDetail());
		map.put("/answer_rb", new ReplyBoard_AnswerWrite());
		map.put("/write_rb", new ReplyBoard_BasicWrite2());
		map.put("/logout", new LogoutController());
		map.put("/deleteReplyBoard", new ReplyBoard_BasicDelete());
		map.put("/filedown", new FileDownController());
		map.put("/upviewcount", new ReplyBoard_UpViewCount());
		map.put("/search_board", new ReplyBoard_Search());
		map.put("/write_comment", new ReplyBoard_CommentWrite());
	} // "write_rb", "/answer_rb", "/signup"
	public Controller getController(String url){
		return map.get(url);
	}
	
	public void setController(String url,Controller con){
		map.put(url, con);
	}
}
