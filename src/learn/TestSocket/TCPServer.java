package learn.TestSocket;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sure GM on 2015/9/17.
 */
public class TCPServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(6666);   //new出的ServerSocket对象ss并不会自动监听客户端有没有向6666端口发送请求，要想监听6666端口是否有客户端的请求，则必须的调用ss对象的accept方法
		while(true) {
			Socket s = ss.accept();
			//accept()是阻塞式方法，如果接收不到客户端的连接，则程序将停止不前
			System.out.println("一个连接已经建立");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			System.out.println(dis.readUTF());  //readUTF()方法也是阻塞式方法
			dis.close();    //DataInputStream 和 InputStream 流中都没有flush方法  DataOutputStream 和 OutputStream 流中都有flush方法
			s.close();
		}
	}
}
