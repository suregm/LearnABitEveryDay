package learn.TestSocket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sure GM on 2015/9/17.
 */
public class TCPClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("127.0.0.1", 6666);   //记住：一旦new出了Socket对象，该对象就会自动向服务器端发送连接请求，如果连接不成功则程序立即终止
		//因此我们在TCP编程的客户端是找不到请求连接的代码，但是在服务器端却存在监听客户端连接请求的代码, 代码类似于：ss.accept()
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF("hello, sure");
		dos.flush();
		dos.close();
		s.close();
	}
}
