package learn.TestIO;

import java.io.File;

/**
 * Created by sure GM on 2016/5/21 17:09.
 */
public class ReadFileName {
	public static void main(String[] args) {
		File file = new File("G:\\Code\\hello.java");
		System.out.println(file.isDirectory());
		file = new File("G:\\Code");
		System.out.println(file.isDirectory());
		//如果是一个目录，把它下面的所有文件名都打印出来
		if(file.isDirectory()) {
			File[] list = file.listFiles();
			for(int i=0; i<list.length; i++) {
				File f = list[i];
				System.out.println(f.getName());
				//System.out.println(f.canRead());
				//System.out.println(f.canWrite());
				System.out.println(f.getAbsolutePath());
			}
		}
	}
}
