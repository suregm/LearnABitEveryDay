package learn.TestGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sure GM on 2015/9/6.
 */
public class TestTextField {
	public static TextField tf1, tf2, tf3;

	public static void main(String[] args) {
		tf1 = new TextField(30);
		tf2 = new TextField(30);
		tf3 = new TextField(30);
		Button bn = new Button("=");
		Label lb = new Label("+");
		Frame f = new Frame("calculater");
		f.setLayout(new FlowLayout());
		f.add(tf1);
		f.add(tf2);
		f.add(tf3);
		f.add(bn);
		f.add(lb);

		bn.addActionListener(new MyMonitor());

		f.pack();
		f.setVisible(true);
	}
}

class MyMonitor implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String str1 = TestTextField.tf1.getText();
		String str2 = TestTextField.tf2.getText();
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		int num3 = num1 + num2;

		Integer it = new Integer(num3);
		String str3 = it.toString();

		TestTextField.tf3.setText(str3);
	}
}
