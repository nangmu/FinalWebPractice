package test.java;

import java.nio.charset.Charset;

import org.junit.Test;

import main.java.utils.CommonUtils;

public class CommonUtilsTest {
	@Test
	public void test1(){
		System.out.println(CommonUtils.getRandomString());
		System.out.println(CommonUtils.getRandomString());
		System.out.println(Charset.defaultCharset().name());
	}
}
