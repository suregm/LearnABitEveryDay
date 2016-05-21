package bitBeat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by sure GM on 2016/5/21 16:38.
 */
public class Calculator extends JFrame {
	private JTextField dataText1;
	private JTextField dataText2;
	private JTextField result;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton button;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello, World! sure");
		Calculator frame = new Calculator();
	}

	public Calculator() {
		//获取当前屏幕分辨率大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = d.width;
		int height = d.height;
		//设置窗体的标题
		setTitle("简单计算器");
		//设置窗体的初始位置
		//窗体居中显示，屏幕分辨率大小减去窗体本身的大小
		setLocation((width-700)/2,(height-500)/2);
		//设置窗体初始的大小
		this.setSize(700,500);
		//设置窗体的大小不可变
		this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//添加一个windows事件
		/*this.addWindowListener(new WindowListener() {

		}*/

		//使用到了java中的内部类的概念
		//内部类在事件处理中使用非常多
		//不能使用windowClosed
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//怎么把这些控件加入到我们的窗体中去
		dataText1 = new JTextField();
		dataText2 = new JTextField();
		result = new JTextField();
		label1 = new JLabel("第一个操作数：");
		label2 = new JLabel("第二个操作数：");
		label3 = new JLabel("计算结果：");
		button = new JButton("计算");
		radio1 = new JRadioButton("+");
		radio2 = new JRadioButton("-");
		radio3 = new JRadioButton("*");
		radio4 = new JRadioButton("/");

		//让每一行的控件在一个组中
		JPanel p1 = new JPanel(new GridLayout(1,2));
		p1.add(label1);
		p1.add(dataText1);
		JPanel p2 = new JPanel(new GridLayout(1,4));
		p2.add(radio1);
		p2.add(radio2);
		p2.add(radio3);
		p2.add(radio4);
		JPanel p3 = new JPanel(new GridLayout(1,2));
		p3.add(label2);
		p3.add(dataText2);
		JPanel p4 = new JPanel(new GridLayout(1,2));
		p4.add(label3);
		p4.add(result);
		JPanel p5 = new JPanel(new GridLayout(1,1));
		p5.add(button);

		//处理一组按钮，只能有一个选中的问题
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);

		//布局 Layout
		//System.out.println(this.getLayout());
		this.setLayout(new GridLayout(5,1));

		//将控件加入到窗体中
		/*this.add(dataText1);
		this.add(dataText2);
		this.add(result);
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(button);
		this.add(radio1);
		this.add(radio2);
		this.add(radio3);
		this.add(radio4);
		*/
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.add(p5);

		//让窗体显示
		setVisible(true);

		//给Button设置事件监听
		button.addActionListener(new ButtonListener());
	}

	//内部类
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//System.out.println("button监听事件");
			//保存第一个操作数
			String data1 = dataText1.getText();
			//保存操作符号
			String operation = "";
			if(radio1.isSelected()) {
				operation = radio1.getText();
			}	else if(radio2.isSelected()) {
				operation = radio2.getText();
			}	else if(radio3.isSelected()) {
				operation = radio3.getText();
			}	else if(radio4.isSelected()) {
				operation = radio4.getText();
			}
			//保存第二个操作数
			String data2 = dataText2.getText();

			//得到了它的数据后，我们应该判断数据的合法性
			//判断是否选择了操作符
			if(operation == "") {
				JOptionPane.showMessageDialog(Calculator.this, "请选择运算符");
				return ;
			}
			//进行计算
			double d = calculate(data1,data2,operation);
			//把结果显示在result中
			result.setText(String.valueOf(d));
		}
	}

	public double calculate(String data1, String data2, String operation) {
		double result = Double.MAX_VALUE;
		double d1=0, d2=0;
		//第一步，把字符串转换为数字
		try {
			d1 = Double.parseDouble(data1);
			d2 = Double.parseDouble(data2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "输入的数据有误");
		}
		//第二步，根据操作符进行计算
		if(operation == "+") {
			result = d1 + d2;
		}	else if(operation == "-") {
			result = d1 - d2;
		}	else if(operation == "*") {
			result = d1 * d2;
		}	else if(operation == "/" ) {
			if(d2 != 0)
				result = d1 / d2;
			else {
				JOptionPane.showMessageDialog(this, "除数不能为零");
				return 0;
			}
		}
		return result;
	}

}
