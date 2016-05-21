package bitBeat.ChatRoom.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by sure GM on 2016/5/21 16:59.
 */
public class Client2 {
	public Client2() {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
			if (s != null) {
				System.out.println("已连接服务器");
			}

			while (true) {

				InputStreamReader input2 = new InputStreamReader(System.in);
				BufferedReader read2 = new BufferedReader(input2);

				String response = read2.readLine();

				// 向服务器2发送消息
				PrintWriter print = new PrintWriter(s.getOutputStream(), true);
				print.println("来自客户端的消息：" + response);

				// 接收来自服务器的消息
				InputStreamReader input = new InputStreamReader(s.getInputStream());
				BufferedReader read = new BufferedReader(input);
				String receiver = read.readLine();
				System.out.println("来自服务器的消息" + receiver);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client2 c = new Client2();
	}
}
