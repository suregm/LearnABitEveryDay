package learn.TestIO;

import java.io.*;

/**
 * Created by sure GM on 2016/5/21 17:10.
 */
public class ReaderWrite {
	public static void main(String[] args) {
		FileReader fileReader = null;
		BufferedReader read = null;
		FileWriter fileWriter = null;
		BufferedWriter write = null;
		try {
			fileReader = new FileReader("G:\\Code\\hello.java");
			read = new BufferedReader(fileReader);
			fileWriter = new FileWriter("G:\\Code\\helloCopy.java");
			write = new BufferedWriter(fileWriter);
			String str = null;
			while((str = read.readLine()) != null) {	//readLine()读文本，一次就一行
				System.out.println(str);
				write.write(str);
				write.write("\n");
			}
			write.flush();	//缓存流有一部分未写完，调用flush()可以全部写完
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				read.close();
				fileWriter.close();
				write.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
