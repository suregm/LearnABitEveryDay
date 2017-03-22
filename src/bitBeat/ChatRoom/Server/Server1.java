/**
 * 服务器端
 */

package bitBeat.ChatRoom.Server;

import java.io.*;
import java.net.*;

public class Server1 {

	public Server1() {
		try {
			//让服务器程序在8888端口监听
			System.out.println("服务器在8888端口监听");
			ServerSocket server = new ServerSocket(8888);
			//一直在等该客服端的连接，如果没有连接，就一直等待下去
			Socket s = server.accept();		//等待客户端接受回应，程序等待
			//建立连接之后，就可以相互通信了
			//如果发送了一个数据，就需要刷新流
			PrintWriter print = new PrintWriter(s.getOutputStream(), true);		//true参数用于刷新流
			print.println("你好，我是服务器");
			//print.flush();	//PrintWriter()不加参数true则可以使用flush()刷新流

			//接受客户端发送的数据
			InputStreamReader input = new InputStreamReader(s.getInputStream());
			BufferedReader read = new BufferedReader(input);
			String str = read.readLine();
			System.out.println("来自客户端的消息：" + str);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server1 s = new Server1();
	}

}
