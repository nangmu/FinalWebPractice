package main.java.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CommonUtils {
	 public static String getRandomString(){
	        return UUID.randomUUID().toString().replaceAll("-", "");
	 }
	 public static String toUTF8(String str){
		 String utf8=null;
		try {
			utf8 =  new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		 return utf8;
	 }
}
