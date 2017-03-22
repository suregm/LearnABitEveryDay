/**
 * 使用GUI编写的聊天程序
 */

package bitBeat.ChatRoom.Server;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class Server3 extends Frame {

	private TextArea textArea = new TextArea(20, 40);
	private Socket socket;

	private Hashtable table = new Hashtable();

	private void launchFrame() {
		this.setTitle("服务器");
		this.setLocation(100,100);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.add(textArea);
		this.pack(); // 超出范围出现滚动条
		this.setVisible(true);

		startService();
	}

	private void startService() {
		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(8888);
			while (true) {
				// 就需要使用多线程来解决多个客户端连接服务器的问题
				socket = server.accept();
				ConnService thread = new ConnService(socket);
				new Thread(thread).start();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ConnService implements Runnable {

		private Socket socket;
		private String name;

		public ConnService(Socket socket) {
			this.socket = socket;
			try {
				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader read = new BufferedReader(input);
				name = read.readLine();
				table.put(name, socket);
				broadcast(name, socket);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			// 服务器接收到客户端发送的消息，需要其他连接到该服务器的所有用户都能够看到
			// 首先要解决：保存所有在线用户的信息
			// receiveMessage(); //重新写一个方法
			// 转发所有用户的消息，当客户端有消息上传时
			try {
				InputStreamReader input = new InputStreamReader(socket.getInputStream());
				BufferedReader read = new BufferedReader(input);
				String content = "";
				while (true) {
					if ((content = read.readLine()) != null) {
						broadcast(content, socket);	//广播消息
						if(content.equals("quit")) {
							table.remove(name);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 转发客户端发送的消息 content 转发的内容 socket 发送者
	 */
	private void broadcast(String content, Socket socket) {
		Enumeration en = table.keys();
		textArea.append(content + "\n");
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			Socket s = (Socket) table.get(name);
			try {
				if (s != socket) {
					PrintWriter print = new PrintWriter(s.getOutputStream(), true);
					print.println(content);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void receiveMessage() {
		try {
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader read = new BufferedReader(input);
			String msg;
			while ((msg = read.readLine()) != null) {
				textArea.append(msg + "\n");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server3().launchFrame();
	}

}
