package bitBeat.ChatRoom.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * Created by sure GM on 2016/5/21 16:59.
 */
public class Client3 extends Frame implements ActionListener {
	private TextArea textArea = new TextArea(30, 50);
	private Label label1 = new Label("IP地址");
	private Label label2 = new Label("端口");
	private Label label3 = new Label("昵称");
	private TextField ip = new TextField("127.0.0.1", 20);
	private TextField port = new TextField("8888", 10);
	private TextField nickName = new TextField(20);
	private TextField message = new TextField(40);
	private Button connButton = new Button("连接");
	private Button sendButton = new Button("发送");
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();

	private Socket socket;

	private void launchFrame() {
		this.setTitle("客户端");
		this.setLocation(500, 100);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
					print.println("【" + nickName.getText() + "】" + "quit");
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		// 将控件加入到窗体上

		p1.add(label1);
		p1.add(ip);
		p1.add(label2);
		p1.add(port);
		p1.add(label3);
		p1.add(nickName);
		p1.add(connButton);

		p2.add(message);
		p2.add(sendButton);

		this.add(textArea);
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);

		// 给Button添加事件监听
		connButton.addActionListener(this);
		sendButton.addActionListener(this);

	}

	long time = System.currentTimeMillis();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	String strTime = sdf.format(time);
	// textArea.append("----:"+ strTime); //日期会按照模板的样子转String

	// 处理Button事件
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((nickName != null) && (e.getSource() == connButton)) { // 与服务器建立连接
			connService();
			new Thread(new Receive()).start();	//开启进程并启动
		} else if (e.getSource() == sendButton) { // 发送数据
			sendMessage();
		}
	}

	// 与服务器进行连接，连接之后，给服务器发送一个消息，提示user进入聊天室
	private void connService() {
		try {
			if (socket == null) {
				socket = new Socket(ip.getText(), Integer.parseInt(port.getText()));
				if (socket != null) {
					textArea.append("你已成功与服务器建立连接\n\n");
				}
				// 给服务器发送一个消息
				PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
				print.println(nickName.getText() + " " + "进入了聊天室" + " " + strTime + " " +"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 向服务器和个人聊天面板发送消息
	private void sendMessage() {
		try {
			PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
			print.println(nickName.getText() + " " + strTime + "\n" + message.getText() + "\n");


			if ((message.getText()) != null) {
				/*textAreaOutput.setSelectedTextColor(Color.RED);
				textAreaOutput.setLineWrap(true);	//激活自动换行功能
				textAreaOutput.setWrapStyleWord(true);	//激活换行不断字功能
				*/
				textArea.append(nickName.getText() + " " + strTime + "\n");
				textArea.append(message.getText() + "\n\n");
				message.setText(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//给客户端也添加一个接收消息的功能
	//这个接收消息的功能，是一直需要的，那么就需要使用另外一个线程来完成此功能
	class Receive implements Runnable {

		//在这里需要循环接收服务器发来的消息
		public void run() {
			receiver();
		}
	}

	private void receiver() {
		try {
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader read = new BufferedReader(input);
			String msg = "";
			while(true) {
				if((msg = read.readLine()) != null) {
					textArea.append(msg + "\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client3().launchFrame();
	}
}
