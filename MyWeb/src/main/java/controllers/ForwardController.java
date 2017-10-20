package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller{
	String path;
	public ForwardController(String path){
		if(path==null){
			throw new NullPointerException();
		}
		this.path=path;
	}
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		
		return path;
	}

}
