package bitBeat.KeyEvent_MouseEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Created by sure GM on 2016/5/26 0:33.
 */
public class KeyboardEvent {
	/**
	 * @param args
	 * @throws AWTException
	 */
	public static void main(String[] args) throws Exception {
		Robot robot = new Robot(); //创建一个robot对象
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec("notepad");        //打开一个记事本程序
		robot.delay(2000);        //等待 2秒
		//窗口最大化
		keyPressWithAlt(robot, java.awt.event.KeyEvent.VK_SPACE); //按下 alt+ 空格
		keyPress(robot, java.awt.event.KeyEvent.VK_X);  //按下x键
		robot.delay(1000);  //等待 1秒
		keyPressString(robot, "大家好，我是一个小机器人，我有很多本领呢 ！"); //输入字符串
		robot.delay(3000);  //等待 3秒
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		keyPressString(robot, "现在，我就向大家展示一下.....嘻嘻"); //输入字符串
		robot.delay(3000);  //等待 3秒
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		keyPressString(robot, "首先，我能按下 键盘的任何一个键。下面,我单独按下a,b,c,d键"); //输入字符串
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		robot.delay(3000);  //等待 3秒
		keyPress(robot, java.awt.event.KeyEvent.VK_A); //按下 a 键
		robot.delay(2000);  //等待 2秒
		keyPress(robot, java.awt.event.KeyEvent.VK_B); //按下 b 键
		robot.delay(2000);  //等待 2秒
		keyPress(robot, java.awt.event.KeyEvent.VK_C); //按下 c 键
		robot.delay(2000);  //等待 2秒
		keyPress(robot, java.awt.event.KeyEvent.VK_D); //按下 d 键
		robot.delay(2000);  //等待 2秒
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		keyPressString(robot, "呵呵，完成了。。。。  ");
		robot.delay(3000);  //等待 3秒
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		keyPressString(robot, "恩，对了    上面 文字很多  是不是 感到 很乱呢？？？     我现在 帮你清空一下 ");
		robot.delay(2000);  //等待 2秒
		keyPressWithCtrl(robot, java.awt.event.KeyEvent.VK_A); //按下 ctrl+A 全选
		robot.delay(2000);  //等待 2秒
		keyPress(robot, java.awt.event.KeyEvent.VK_DELETE); //清除
		robot.delay(3000);  //等待 3秒
		keyPressString(robot, "恩，现在 是不是 觉得 清爽多了              另外 我还会按 组合键呢 ...");
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		robot.delay(3000);  //等待 3秒
		keyPressString(robot, "................好像已经 演示过了 吧 ，呵呵    ");
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		robot.delay(3000);  //等待 3秒
		keyPressString(robot, "其实，我还有很多本领呢                           现在就不向大家展示了 .....");
		keyPress(robot, java.awt.event.KeyEvent.VK_ENTER); // 按下 enter 换行
		robot.delay(3000);  //等待 3秒
		keyPressString(robot, "谢谢大家！！！！！");

	}

	/**
	 * 单个按键
	 * @param robot
	 * @param key
	 */
	public static void keyPress(Robot robot,int key){
		robot.keyPress(key);
		robot.delay(100);
		robot.keyRelease(key);
		robot.delay(100);
	}

	/**
	 * 两个按键连按
	 * @param robot
	 * @param key1
	 * @param key2
	 */
	public static void keyPressMulti2(Robot robot, int key1, int key2) {
		robot.keyPress(key1);
		robot.keyPress(key2);
		robot.delay(100);
		robot.keyRelease(key2);
		robot.keyRelease(key1);
		robot.delay(100);
	}

	/**
	 * 三个按键连按
	 * @param robot
	 * @param key1
	 * @param key2
	 * @param key3
	 */
	public static void keyPressMulti3(Robot robot, int key1, int key2, int key3) {
		robot.keyPress(key1);
		robot.keyPress(key2);
		robot.keyPress(key3);
		robot.delay(100);
		robot.keyRelease(key3);
		robot.keyRelease(key2);
		robot.keyRelease(key1);
		robot.delay(100);
	}

	// shift+ 按键
	public static void keyPressWithFn(Robot robot, int key) {

	}

	// shift+ 按键
	public static void keyPressWithShift(Robot robot, int key) {
		robot.keyPress(java.awt.event.KeyEvent.VK_SHIFT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(java.awt.event.KeyEvent.VK_SHIFT);
		robot.delay(100);
	}

	// ctrl+ 按键
	public static void keyPressWithCtrl(Robot robot, int key) {
		robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
		robot.delay(100);
	}

	// alt+ 按键
	public static void keyPressWithAlt(Robot robot, int key) {
		robot.keyPress(java.awt.event.KeyEvent.VK_ALT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(java.awt.event.KeyEvent.VK_ALT);
		robot.delay(100);
	}

	// 输出字符串
	public static void keyPressString(Robot robot, String str){
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
		Transferable tText = new StringSelection(str);
		clip.setContents(tText, null); //设置剪切板内容
		keyPressWithCtrl(robot, java.awt.event.KeyEvent.VK_V);//粘贴
		robot.delay(100);
	}


}
