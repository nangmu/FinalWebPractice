package main.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMove {
	private static final Logger logger = LoggerFactory.getLogger(FileMove.class);
	
	public File fileMove(String s, String t) {
		File f1 = new File(s);
		File f2 = new File(t);
		try {
			if (f1.exists()) {
				if (!createNewFile(f2)) {
					String name = f2.getName();
					String body = null;
					String ext = null;

					int dot = name.lastIndexOf(".");
					if (dot != -1) {
						body = name.substring(0, dot);
						ext = name.substring(dot); // includes "."
					} else {
						body = name;
						ext = "";
					}

					int count = 0;
					while (!createNewFile(f2) && count < 9999) {
						count++;
						String newName = body + count + ext;
						f2 = new File(f2.getParent(), newName);
					}
				}
				
				//renameTo 실패시
				if (!f1.renameTo(f2)) {
					FileInputStream fin = new FileInputStream(f1);
					FileOutputStream fout = new FileOutputStream(f2);
					byte buffer[] = new byte[1024];
					int j;

					while ((j = fin.read(buffer)) >= 0)
						fout.write(buffer, 0, j);

					fout.close();
					fin.close();
					f1.delete();
				}
				return f2;
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return null;
	}

	private boolean createNewFile(File file) {
		try {
			return file.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
