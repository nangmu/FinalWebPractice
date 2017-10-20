package main.java.Exception;

public class MySqlException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MySqlException(){
		super();
	}
	public MySqlException(String s){
		super(s);
	}
	public MySqlException(Throwable t){
		super(t);
	}
}
