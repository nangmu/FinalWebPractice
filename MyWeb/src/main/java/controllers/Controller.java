package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public String doService(HttpServletRequest req, HttpServletResponse resp);
}
