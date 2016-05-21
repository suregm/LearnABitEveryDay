package bitBeat.TimingResponder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by sure GM on 2016/5/21 18:21.
 */
public class RsonderFrame extends JFrame implements ActionListener {
	JTextField jText1;
	JTextField jText2;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JButton button1;
	JButton button2;

	int score = 0;		//保存得到的分数
	int count = 9;		//保存题目的个数
	int qanswered = 1;		//剩余题目的个数
	long time = 0;		//计时
	TimeThread timeThread = new TimeThread();
	Thread thread = new Thread(timeThread);
	String operation;

	private void lanchFrame() {
		//设置标题
		this.setTitle("快速抢答器");
		//设置初始大小
		this.setSize(400, 300);
		//设置大小不可变
		this.setResizable(false);
		//设置初始位置
		this.setLocation(300, 300);
		//设置点击关闭按钮是，结束应用程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//在设置控件的大小并把它加入到窗体中的时候，需要把它的布局设置为空
		this.setLayout(null);

		//初始化基本控件，并且将他们添加到窗体上
		jText1 = new JTextField();
		jText2 = new JTextField();
		label1 = new JLabel("题目");
		label2 = new JLabel("答案");
		label3 = new JLabel("得分：0分");
		label4 = new JLabel("计时：0秒");
		label5 = new JLabel("共10题");
		label6 = new JLabel("已答：0题");
		label7 = new JLabel("剩余：10题");
		button1 = new JButton("答题");
		button2 = new JButton("下一题");

		label1.setBounds(new Rectangle(50, 50, 30, 30));
		label2.setBounds(new Rectangle(50, 120, 30, 30));
		label3.setBounds(new Rectangle(315, 120, 80, 30));
		label4.setBounds(new Rectangle(315, 50, 80, 30));
		label5.setBounds(new Rectangle(70, 5, 80, 30));
		label6.setBounds(new Rectangle(160, 5, 80, 30));
		label7.setBounds(new Rectangle(250, 5, 80, 30));
		jText1.setBounds(new Rectangle(150, 50, 120, 30));
		jText2.setBounds(new Rectangle(150, 120, 120, 30));
		button1.setBounds(new Rectangle(35, 190, 60, 30));
		button2.setBounds(new Rectangle(150, 190, 120, 30));

		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		this.add(label6);
		this.add(label7);
		this.add(jText1);
		this.add(jText2);
		this.add(button1);
		this.add(button2);

		//让窗体显示出来
		this.setVisible(true);

		//设置按钮的监听事件
		button1.addActionListener(this);
		button2.addActionListener(this);

		//接下来就要随机产生题目（按下一题换题目），并且答题
		produceQuestion();
		//在这里开始计时，也就是启动线程
		thread.start();
		time = System.currentTimeMillis();
	}

	private void produceQuestion() {
		String result = "";
		String operations [] = {"+","-","*"};
		Random rand = new Random();
		result = result + (Math.abs(rand.nextInt())%9 + 1) + " ";
		result = result + operations[Math.abs(rand.nextInt())%3] + " ";
		result = result + (Math.abs(rand.nextInt())%9 + 1) + " =";
		jText1.setText(result);
	}

	//实现的ActionListener接口中的方法
	public void actionPerformed(ActionEvent e) {
		//if(e.getActionListener() =="答题");
		if(e.getSource() == button1) {
			//System.out.println("button1被按下");
			//点击答题按钮后，需要对答案进行判断，看是否答对了
			//如果答对了，则分数加10分
			//不论答对与否，题目数都应该减1
			if(count >= 0) {
				if(CheckResult.check(jText1.getText(), jText2.getText())) {
					score += 10;
					//把新的分数显示出来
					label3.setText("得分：" + score + "分");
					label6.setText("已答：" + qanswered + "题");
					label7.setText("剩余：" + count + "题");
				}
			} else {
				//thread.interrupt();		//？ 无效？
				//让线程终止
				timeThread.setFlag(false);
				JOptionPane.showMessageDialog(null,"题目回答完毕");
			}
			count--;
			qanswered++;
			jText1.setText("");
			jText2.setText("");

		}	else if(e.getSource() == button2) {
			//System.out.println("button2被按下");
			//点击下一题按钮，应该重新产生一个题目
			//如果count<0，则不能继续答题
			if(count >= 0) {
				produceQuestion();
			} else {
				//虽然stop方法能够终止线程，但是不推荐使用，易引起安全隐患
				//thread.stop();
				//thread.interrupt();		//？ 无效？
				//让线程终止
				timeThread.setFlag(false);
				JOptionPane.showMessageDialog(null, "题目回答完毕");
			}
		}
	}

	class TimeThread implements Runnable {

		private boolean flag = true;

		//强烈推荐此方法终止线程
		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public void run() {
			//在线程中更新时间
			while(flag) {
				label4.setText("计时：" +
						((System.currentTimeMillis()-time)/1000) + "秒");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RsonderFrame frame = new RsonderFrame();
		frame.lanchFrame();
	}
}
