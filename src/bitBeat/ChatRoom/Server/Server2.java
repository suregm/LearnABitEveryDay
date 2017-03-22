/**
 * 通过控制台输入实现服务器与客户端聊天
 */

package bitBeat.ChatRoom.Server;

import java.net.*;
import java.io.*;

public class Server2 {

	public Server2() {
		try {
			System.out.println("服务器在8888端口监听");
			ServerSocket server = new ServerSocket(8888);
			Socket s = server.accept();
			System.out.println("已建立连接");

			while (true) {
				InputStreamReader input = new InputStreamReader(s.getInputStream());
				BufferedReader read = new BufferedReader(input);
				String receiver = read.readLine();
				System.out.println("来自客户端的消息" + receiver);

				InputStreamReader input2 = new InputStreamReader(System.in);
				BufferedReader read2 = new BufferedReader(input2);

				String response = read2.readLine();

				//向客户端2发送消息
				PrintWriter print = new PrintWriter(s.getOutputStream(), true);
				print.println("来自服务器的消息：" + response);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server2 s = new Server2();
	}

}
