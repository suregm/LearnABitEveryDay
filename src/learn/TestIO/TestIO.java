package learn.TestIO;

import java.io.*;

/**
 * Created by sure GM on 2016/5/21 17:07.
 */
public class TestIO {
	public static void main(String[] args) {

		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream("G:\\Code\\hello.java");
			//读入一个字符
			int n;
				/*
				int n = input.read();
				System.out.println(n);	//112 p，ascii码
				System.out.print((char)n);	//强制转换为字符型
				//读出全部的文件，则可以循环读取文件，就可以把文件中所有的数据都读出
				//怎么判断我们这个文件已经读取完了呢？read()读取文件结尾时返回-1
				//如果在文件中存在中文的话，按此方法读取就会出现乱码的情况
				//用字符流处理
				/*while(true) {
					n = input.read();
					System.out.print((char)n);
				}*/
				/*
				while((n = input.read()) != -1) {
					System.out.print((char)n);
				}
				*/
			byte b[] = new byte[1024];
			while((n = input.read(b)) != -1) {
				System.out.println(new String(b));
			}

			//文件的输出
			output = new FileOutputStream("G:\\Code\\hello.txt");
			output.write(97);
			output.write(b);

		} catch (Exception e) {		//FileNotFoundException改为Exception，全部抛出
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
