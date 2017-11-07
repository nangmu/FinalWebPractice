package main.java.VO;

public class MyFile {
	private int fNum;
	private int bNum;
	private String storedFileName;
	private String originalFileName;
	
	public MyFile(){}
	public MyFile(int bNum, String s, String o){
		this.bNum=bNum; storedFileName=s; originalFileName=o;
	}
	public int getfNum() {
		return fNum;
	}
	public void setfNum(int fNum) {
		this.fNum = fNum;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	
}
