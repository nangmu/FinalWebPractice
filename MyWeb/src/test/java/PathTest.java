package test.java;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.controllers.DispatcherServlet;

public class PathTest{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(PathTest.class);
	
	@Test
	public void 경로테스트() throws IOException{
		
		logger.debug(new File("/").getAbsolutePath());
		logger.debug(new File("/").getCanonicalPath());
		logger.debug(new File("").getAbsolutePath());
		logger.debug(new File("").getCanonicalPath());
		logger.debug(new File("./").getAbsolutePath());
		logger.debug(new File("./").getCanonicalPath());
		
		
	}
}
