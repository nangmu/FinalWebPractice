package main.java.VO;

public class Board {
	int bNum;
	int bGroup;
	int bLevel;
	int bOrder;
	String id;
	String writer;
	String title;
	StringBuilder contents = new StringBuilder();
	int viewCount=0;
	
	public Board(){}
	public Board(int num, int group, int level, int order){
		bNum=num; bGroup=group; bLevel=level; bOrder=order;
	}
	
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbLevel() {
		return bLevel;
	}
	public void setbLevel(int bLevel) {
		this.bLevel = bLevel;
	}
	public int getbOrder() {
		return bOrder;
	}
	public void setbOrder(int bOrder) {
		this.bOrder = bOrder;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents.toString();
	}
	public void setContents(String contents) {
		this.contents.append(contents);
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
