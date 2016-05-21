package bitBeat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by sure GM on 2016/5/21 17:14.
 */
public class Notepad extends JFrame implements ActionListener {
	JTextArea textArea = null;
	JMenuBar menuBar = null;
	JMenu menu = null;
	JMenuItem jOpen = null;
	JMenuItem jSave = null;

	public Notepad() {
		this.setTitle("简单记事本");
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea = new JTextArea(20,40);
		textArea.setLineWrap(true);
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		jOpen = new JMenuItem("Open(O)");
		jSave = new JMenuItem("Save(S)");

		menuBar.add(menu);
		menu.add(jOpen);
		menu.add(jSave);

		this.setJMenuBar(menuBar);	//不是通过add()方法加入

		JPanel panel = new JPanel();
		JScrollPane jsp = new JScrollPane(textArea);
		panel.add(jsp);
		this.add(panel);


		//给菜单设置监听事件
		jOpen.addActionListener(this);
		jSave.addActionListener(this);

		//this.add(textArea);
		//让窗体的大小能够容纳加入的控件
		this.pack();

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Open(O)")) {
			System.out.println("打开文件");
			openFile();
		} else if(cmd.equals("Save(S)")) {
			System.out.println("保存文件");
			saveFile();
		}

	}

	private void saveFile() {
		JFileChooser chooser = new JFileChooser();
		OutputStream out = null;
		chooser.setDialogTitle("另存为");
		int type = chooser.showSaveDialog(null);
		if(type == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getAbsolutePath();
			try {
				out = new FileOutputStream(path);
				out.write(textArea.getText().getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 打开文件
	 */
	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		InputStream in = null;
		chooser.setDialogTitle("请打开");
		int type = chooser.showOpenDialog(null);

		if(type == JFileChooser.APPROVE_OPTION) {
			//怎么得到选择了哪个文件，能不能获得文件的路径
			String path = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(path);
			try {
				in = new FileInputStream(path);
				byte[] b = new byte[1024];
				int n = -1;
				String content = "";
				while((n = in.read(b)) != -1) {
					content += new String(b,0,n);	//偏移量，从0开始
				}
				textArea.setText(content);
			} catch (Exception e) {		//抛出所有的异常
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notepad notepad = new Notepad();
	}
}
