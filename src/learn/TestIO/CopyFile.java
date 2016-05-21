package learn.TestIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sure GM on 2016/5/21 17:06.
 */
public class CopyFile {
	public static void main(String[] args) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("G:\\Code\\hello.txt");
			out = new FileOutputStream("G:\\hello.txt");
			byte[] b = new byte[1024];
			int n = -1;
			try {
				while((n = in.read(b)) != -1) {
					System.out.println(new String(b));
					out.write(b,0,n);	//write(b,0,n)实际字节，非1024
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch(Exception e2) {

			}
		}
	}
}
