package problem.Gobang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by sure GM on 2016/5/21 19:28.
 */
public class Gobang {
	public static void main(String[] args) {
		// 创建窗体类变量
		DemoWindow dw = new DemoWindow("五子棋");

		// 居中显示窗体
		Toolkit theKit = dw.getToolkit();
		Dimension wndSize = theKit.getScreenSize();
		dw.setLocation(wndSize.width / 2 - dw.getWidth() / 2, wndSize.height
				/ 2 - dw.getHeight() / 2);

		// 点击关闭按钮可以退出程序
		dw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗体为可见
		dw.setVisible(true);
	}
}

// 界面窗体
class DemoWindow extends JFrame {
	// 默认棋盘宽度（允许自行调整，最小值为40，设置的值要为20的倍数）
	final int size = 480;

	// 显示五子棋的面板
	DrawPanel bp = new DrawPanel(size);

	// 构造函数
	public DemoWindow(String title) {
		super(title);

		// 不允许调整窗体大小
		setResizable(false);

		// 窗体布局
		add(bp);
		pack();
	}
}

// 显示五子棋的面板类
class DrawPanel extends JPanel implements MouseListener {
	// 棋盘宽度
	int size;

	// 是否黑方走
	boolean isBlack = false;

	// 保存屏幕的缓冲图像，防止刷新窗体导致图像消失
	BufferedImage image = null;

	// 鼠标的点击位置
	Point point = new Point();

	// 是否需要绘制新的棋子
	boolean isNeedRefresh = false;

	// 表示棋子位置的数组（0代表没有棋子，1代表黑子，2代表白子）
	int[][] elements = null;

	// 构造函数
	public DrawPanel(int size) {
		// 设置宽度
		this.size = size;

		// 生成合适大小的表示棋子位置的数组（每格的宽度和高度都为20像素）
		elements = new int[size / 20 - 1][size / 20 - 1];

		// 添加事件监听器
		addMouseListener(this);

		// 设置大小，正好显示整个棋盘
		setPreferredSize(new Dimension(size, size));
	}

	// 重载的绘图函数，在图像缓冲上绘制棋盘和棋子，然后将图像绘制到面板上
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 如果是新的一局，重绘棋盘
		if (image == null) {
			// 创建图像缓冲
			image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

			// 获取图像缓冲的绘图类变量
			Graphics uig = image.createGraphics();

			// 绘制桔黄色背景
			uig.setColor(Color.orange);
			uig.fillRect(0, 0, 1000, 1000);

			// 绘制黑色棋盘网格
			uig.setColor(Color.black);
			for (int i = 0; i <= size - 40; i += 20) {
				uig.drawLine(i + 20, 20, i + 20, size - 20);
				uig.drawLine(20, i + 20, size - 20, i + 20);
			}
		}
		// 如果是在一局当中，无需重新绘制棋盘表格，只需绘制新的棋子即可
		else {
			// 如果需要绘制新的棋子，而不是单纯的刷新屏幕
			if (isNeedRefresh) {
				// 获取图像缓冲的绘图类变量
				Graphics uig = image.createGraphics();

				// 如果是黑方
				if (isBlack == true) {
					// 将前景色设置为黑色
					uig.setColor(Color.black);
				}
				// 如果是白方
				else if (isBlack == false) {
					// 将前景色设置为白色
					uig.setColor(Color.red);
				}

				// 绘制圆形棋子
				uig.fillOval(point.x - 9, point.y - 9, 18, 18);
			}
		}

		// 将图像绘制到面板上
		g.drawImage(image, 0, 0, this);

		// 如果刷新窗体，无需绘制新的棋子
		isNeedRefresh = false;
	}

	// 响应单击鼠标
	public void mouseClicked(MouseEvent e) {
		// 获取鼠标单击的位置
		int x = e.getX();
		int y = e.getY();

		// 获取最近的棋盘网格点
		if (x % 20 <= 10)
			point.x = x - x % 20;
		else
			point.x = x - x % 20 + 20;

		if (y % 20 <= 10)
			point.y = y - y % 20;
		else
			point.y = y - y % 20 + 20;

		// 如果点击在棋盘网格外，则退出
		if (point.x == 0 || point.x == size || point.y == 0 || point.y == size) {
			return;
		}

		// 如果该位置没有棋子
		if (elements[point.x / 20 - 1][point.y / 20 - 1] == 0) {
			// 需要绘制新的棋子
			isNeedRefresh = true;

			// 如果当前是黑方，切换为白方，否则切换为黑方
			if (isBlack == true) {
				isBlack = false;

				// 设置黑方棋子的位置
				elements[point.x / 20 - 1][point.y / 20 - 1] = 1;
			} else {
				isBlack = true;

				// 设置白方棋子的位置
				elements[point.x / 20 - 1][point.y / 20 - 1] = 2;
			}

			// 重绘面板
			repaint();

			// 判断输赢（横向、纵向和两个斜角方向是否有5个连珠）
			if (checkRows() || checkCols() || checkBias()) {
				// 如果当前是黑方，黑方胜，否则白方胜
				if (isBlack == true)
					JOptionPane.showMessageDialog(null, "黑方胜利！");
				else
					JOptionPane.showMessageDialog(null, "红方胜利！");

				// 重置棋子位置数组
				elements = new int[size / 20 - 1][size / 20 - 1];

				// 重置图像缓冲
				image = null;

				// 重置鼠标点击位置
				point = new Point();

				// 重绘面板（输方下轮先走）
				repaint();
			}
		}
	}

	// 检查横向是否存在5个连珠
	public boolean checkCols() {
		// 循环棋子位置数组的每一行
		for (int i = 0; i < elements.length; i++) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 前一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 循环棋子位置数组的每一列
			for (int j = 0; j < elements[i].length; j++) {
				// 如果颜色和前一个棋子一样（不考虑没有棋子的情况），累加连珠次数，否则将连珠次数重新置为1
				if (elements[i][j] == preElement && elements[i][j] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存前一个位置的状态
				preElement = elements[i][j];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;
			}
		}
		return false;
	}

	// 检查纵向是否存在5个连珠
	public boolean checkRows() {
		// 循环棋子位置数组的每一列
		for (int i = 0; i < elements.length; i++) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 上一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 循环棋子位置数组的每一行
			for (int j = 0; j < elements[i].length; j++) {
				// 如果颜色和上一个棋子一样（不考虑没有棋子的情况），累加连珠次数，否则将连珠次数重新置为1
				if (elements[j][i] == preElement && elements[j][i] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存上一个位置的状态
				preElement = elements[j][i];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;
			}
		}
		return false;
	}

	// 检查两个斜角方向是否存在5个连珠
	public boolean checkBias() {
		// 检查45度斜角方向是否存在5个连珠（检查棋盘的左上半边部分）
		// 从第五行开始，直到最后一行
		for (int i = 4; i < elements.length; i++) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 上一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 保存上一行的行号（ 在检查每行时，不断的拿上一行的后一个棋子与当前棋子进行比较）
			int tempI = i;

			// 从第一列开始，不断的与右上方棋子比较，直到抵达棋盘边界为止
			for (int j = 0; tempI >= 0; j++) {
				// 如果颜色和上一个棋子一样（不考虑没有棋子的情况），累加连珠次数，否则将连珠次数重新置为1
				if (elements[tempI][j] == preElement && elements[tempI][j] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存上一个位置的状态
				preElement = elements[tempI][j];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;

				// 获取上一行行号
				tempI--;
			}
		}

		// 检查45度斜角方向是否存在5个连珠（检查棋盘的右下半边部分）
		// 从倒数第五行开始，直到第一行
		for (int i = elements.length - 4 - 1; i >= 0; i--) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 上一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 保存下一行的行号（ 在检查每行时，不断的拿下一行的前一个棋子与当前棋子进行比较）
			int tempI = i;

			// 从最后一列开始，不断的与左下方棋子比较，直到抵达棋盘边界为止
			for (int j = elements.length - 1; tempI < elements.length; j--) {
				// 如果颜色和上一个棋子一样（不考虑没有棋子的情况），累加连珠次数，否则将连珠次数重新置为1
				if (elements[tempI][j] == preElement && elements[tempI][j] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存上一个位置的状态
				preElement = elements[tempI][j];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;

				// 获取下一行行号
				tempI++;
			}
		}

		// 检查135度斜角方向是否存在5个连珠（检查棋盘的左下半边部分）
		// 从倒数第五行开始，直到第一行
		for (int i = elements.length - 4 - 1; i >= 0; i--) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 上一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 保存下一行的行号（ 在检查每行时，不断的拿下一行的后一个棋子与当前棋子进行比较）
			int tempI = i;

			// 从第一列开始，不断的与右下方棋子比较，直到抵达棋盘边界为止
			for (int j = 0; tempI < elements.length; j++) {
				if (elements[tempI][j] == preElement && elements[tempI][j] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存上一个位置的状态
				preElement = elements[tempI][j];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;

				// 获取下一行行号
				tempI++;
			}
		}

		// 检查135度斜角方向是否存在5个连珠（检查棋盘的右上半边部分）
		// 从第五行开始，直到最后一行
		for (int i = 4; i < elements.length; i++) {
			// 统计连珠的次数
			int sumFlag = 1;

			// 上一个位置的状态（-1为初始值，0代表没有棋子，1代表黑子，2代表白子）
			int preElement = -1;

			// 保存上一行的行号（ 在检查每行时，不断的拿上一行的前一个棋子与当前棋子进行比较）
			int tempI = i;

			// 从最后一列开始，不断的与左上方棋子比较，直到抵达棋盘边界为止
			for (int j = elements.length - 1; tempI >= 0; j--) {
				if (elements[tempI][j] == preElement && elements[tempI][j] != 0)
					sumFlag++;
				else
					sumFlag = 1;

				// 保存上一个位置的状态
				preElement = elements[tempI][j];

				// 已有5个连珠，返回真表示有胜利方
				if (sumFlag >= 5)
					return true;

				// 获取上一行行号
				tempI--;
			}
		}

		return false;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
