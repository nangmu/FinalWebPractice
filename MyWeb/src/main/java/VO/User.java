package main.java.VO;

public class User {
	private String id;
	private String pw;
	private String name;
	
	public User(String id, String pw, String name){
		this.id=id; this.pw=pw; this.name=name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
}
