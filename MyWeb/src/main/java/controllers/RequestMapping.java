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
		map.put("/login", new LoginController());
		map.put("/signup", new SignupController());
		map.put("/userlist", new UserListController());
		map.put("/replyboard", new ReplyBoardController());
		map.put("/detail_rb", new DetailReplyBoardController());
		map.put("/answer_rb", new AnswerReplyBoardController());
		map.put("/write_rb", new WriteReplyBoardController());;
		map.put("/logout", new LogoutController());
	}
	public Controller getController(String url){
		return map.get(url);
	}
	
	public void setController(String url,Controller con){
		map.put(url, con);
	}
}
