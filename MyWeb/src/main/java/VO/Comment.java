package main.java.VO;

import java.sql.Timestamp;

public class Comment {
	private int cNum;
	private int bNum;
	private StringBuilder contents = new StringBuilder();
	private String writer;
	private Timestamp time;
	
	public Comment(){}
	public Comment(int bN, String s, String w){
		 bNum=bN; contents.append(s); writer=w;
	}
	public Comment(int cN, int bN, String s, String w, Timestamp t){
	     cNum=cN; bNum=bN; contents.append(s); writer=w; time=t;
	}
	public int getcNum() {
		return cNum;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getContents() {
		return contents.toString();
	}
	public void setContents(String contents) {
		(this.contents).append(contents);
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
