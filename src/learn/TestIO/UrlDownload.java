package learn.TestIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sure GM on 2016/5/21 17:11.
 */
public class UrlDownload {
	public static void main(String[] args) {
		String path = "http://ugc.qpic.cn/manhua_cover/0/15_12_08_c8ee7a816281091151d25843d9135ad0.jpg/0";
		URL url = null;
        /*try {
            url = new URL(path);
            System.out.println(url.getFile());
            System.out.println(url.getHost());
            System.out.println(url.getPort())
            System.out.println(url.getProtocol());
            System.out.println(url.getUserInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
		//从网络上下载一张图片
		InputStream in = null;
		OutputStream out = null;
		//建立一个网络连接
		HttpURLConnection con = null;
		try {
			url = new URL(path);
			con = (HttpURLConnection) url.openConnection();
			in = con.getInputStream();
			out = new FileOutputStream(new File("G:\\a.jpg"));   //下载一张图片
			int n = -1;
			byte b[] = new byte[1024];
			while((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
