package bitBeat.ChatRoom.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by sure GM on 2016/5/21 16:59.
 */
public class Client1 {
	public Client1() {
		try {
			//连接服务器
			Socket s = new Socket("127.0.0.1", 8888);
			System.out.println("已连接服务器");
			//接受服务器端发送的数据
			InputStreamReader input = new InputStreamReader(s.getInputStream());
			BufferedReader read = new BufferedReader(input);
			String str = read.readLine();
			System.out.println("来自服务器的消息：" + str);

			//向服务器发送数据
			PrintWriter print = new PrintWriter(s.getOutputStream(), true);		//true参数用于刷新流
			print.println("你好，我是客户端");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client1 c = new Client1();
	}
}
