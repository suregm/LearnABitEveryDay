package problem.Gapid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by sure GM on 2016/5/21 19:22.
 */
public class Gapid extends Frame implements Runnable {
	private static float geneX[];
	private static float geneY[];
	private static float geneXX[];
	private static float geneYY[];
	private static float fit[];
	private static float fitness[];
	private static float sump=0;
	private static float cumulative=0;
	private static float cumulativeProbability[];
	private static int geneNumber;
	private static int generation;
	private static float crossoverRate;
	private static float mutationRate;
	private static float sjdata;
	private static int sjintdata;
	private static int sjintdata1;
	private static int sjintdata2;
	private static float left1;
	private static float right1;
	private static float left2;
	private static float right2;
	private static float lefttb;
	private static float middtb;
	private static float righttb;
	private static float min=10000;
	private static float s;
	private static float h;
	private static int k=0;
	public void run()
	{
		repaint();
	}

	public void paint(Graphics g)
	{
		g.setColor(new Color(0,0,0));
		for(int i=0;i<geneNumber;i++)
		{
			g.drawString("o",(int)(geneX[i]),(int)(geneY[i]));
			g.setColor(new Color(255,0,0));
			g.drawString(min+"  "+geneX[i]+"  "+geneY[i],50,525);
		}
		try
		{
			k=k+1;
			ycsf();
			if(k>generation-2)
			{
				System.exit(0);
			}
			Thread.sleep((int)(1000));
		}
		catch(InterruptedException e)
		{
			System.out.println(e.toString());
		}
		repaint();
	}

	public static void ycsf()
	{
		for(int i=0;i<geneNumber;i++)
		{
			//计算每个基因的适应度。
			s = (float)(-0.5+Math.sin(Math.sqrt(Math.pow(geneX[i],2)+Math.pow(geneY[i],2))));
			h = (float)(Math.pow((1+0.001*(Math.pow(geneX[i],2)+Math.pow(geneY[i],2))),2));
			fit[i]= (float)(Math.abs(-0.5+(s / h)));
			sump=sump+fit[i];
			if(min>fit[i])
			{
				min=fit[i];
			}

		}
		//备份基因库
		geneXX=geneX;
		geneYY=geneY;
		//计算每个基因的选择概率和累积概率。
		for(int i=0;i<geneNumber;i++)
		{
			fitness[i]=fit[i]/sump;
			cumulative=cumulative+fitness[i];
			cumulativeProbability[i+1]=cumulative;
		}
		//基因的选择，采用轮盘赌的方法。
		for(int i=0;i<geneNumber;i++)
		{
			sjdata=(float)(Math.random());
			for(int j=0;j<geneNumber;j++)
			{
				if ((sjdata<cumulativeProbability[j+1])&&(sjdata>cumulativeProbability[j]))
				{
					geneX[i]=geneXX[j];
					geneY[i]=geneYY[j];
				}
			}

		}
		//基因的交叉
		for(int i=0;i<(int)(geneNumber*crossoverRate);i++)
		{
			sjintdata1=(int)(geneNumber*Math.random());
			sjintdata2=(int)(geneNumber*Math.random());
			left1=(float)(Math.floor(geneX[sjintdata1]/10));
			right1=geneX[sjintdata1]%10;
			left2=(float)(Math.floor(geneX[sjintdata2]/10));
			right2=geneX[sjintdata2]%10;
			geneX[sjintdata1]=left1*10+right2;
			geneX[sjintdata2]=left2*10+right1;
			left1=(float)(Math.floor(geneY[sjintdata1]/10));
			right1=geneY[sjintdata1]%10;
			left2=(float)(Math.floor(geneY[sjintdata2]/10));
			right2=geneY[sjintdata2]%10;
			geneY[sjintdata1]=left1*10+right2;
			geneY[sjintdata2]=left2*10+right1;
		}
		//基因的变异
		for(int i=0;i<(int)(geneNumber*mutationRate);i++)
		{
			sjintdata=(int)(geneNumber*Math.random());
			lefttb=(float)(Math.floor(geneX[sjintdata]/0.01));
			middtb=(float)(Math.floor(10*Math.random()));
			righttb=(float)((geneX[sjintdata]%0.01)%0.1);
			geneX[sjintdata]=(float)(lefttb*0.01+middtb*0.1+righttb);
			lefttb=(float)(Math.floor(geneY[sjintdata]/0.01));
			middtb=(float)(Math.floor(10*Math.random()));
			righttb=(float)((geneY[sjintdata]%0.01)%0.1);
			geneY[sjintdata]=(float)(lefttb*0.01+middtb*0.1+righttb);
		}
	}

	public static void main(String[] args)
	{
		generation=Integer.parseInt(JOptionPane.showInputDialog("请输入世代数1-500）"));
		geneNumber=Integer.parseInt(JOptionPane.showInputDialog("请输入基因个数1000-5000）"));
		crossoverRate=Float.parseFloat(JOptionPane.showInputDialog("请输入交叉率0-0.2）"));
		mutationRate=Float.parseFloat(JOptionPane.showInputDialog("请输入突变率0-0.2）"));
		//种群初始化
		geneX=new float [geneNumber];
		geneY=new float [geneNumber];
		fit=new float [geneNumber];
		fitness=new float [geneNumber];
		cumulativeProbability=new float [geneNumber+1];
		for(int i=0;i<geneNumber;i++)
		{
			geneX[i]=(float)(1000*Math.random());
			geneY[i]=(float)(600*Math.random());
		}
		cumulativeProbability[0]=0;
		//基因开始演化直到满足所需精度为止。
		ycsf();
		Gapid threada=new Gapid();
		threada.setTitle("用遗传算法优化PID参数");
		threada.setSize(800,600);
		threada.addWindowListener(new gbck());
		threada.setVisible(true);
		Thread threadc=new Thread(threada);
		threadc.start();
	}

	static class gbck extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
}
