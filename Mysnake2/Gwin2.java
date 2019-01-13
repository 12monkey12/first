package Mysnake2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/*
 * 遇到的问题：
 * 比较字符串是否相等：比值用equals()、比对象用==
 * 线程的终止：stop()、标志位、interrupt()
 * Jframe设置背景颜色：获取内容面板设置颜色
 * 焦点问题导致的键盘事件无法响应：在键盘事件中获取焦点
 * 死后中断线程，且无法启动线程，导致死后要重启游戏，点“重新开始”按钮没用：死后不中断线程，只设置flag为false
 */
public class Gwin2 extends JPanel implements ActionListener,KeyListener{//游戏区域

	//定义区域大小和坐标，格子大小
	private int width,height;
	public static final int size=20;    				
	private JButton but_start;							
	private JButton but_stop;
	private JButton but_restart;
	private Thread thread;				
	private Mythread mythread;
	private Snake1 snake1;
	ArrayList<Node1> snake;
	private boolean flag;
	private Node1 food;
	public static int speed=500;
	
	public Gwin2()
	{
		//格子大小为20像素
		/*snake1=new Snake1();
		snake=snake1.getSnake();
		food=snake1.getFood();
		flag=false;*/
		/*
		 * 700/20=35
		 * 按钮要占空间
		 * 第一条线下移60像素，500-60=440，440/20=22
		 * 横排有35个格子要画竖线36条线，竖线第1条和第36条不要画
		 * 纵排有22个格子要画横线23条线，横线第23条不要画
		 * 则要画竖线34条，横线22条
		 * 0-700   60-500
		 * 设置区域大小和位置
		 */
		width=700;
		height=500;
		this.setSize(width, height);
		this.setLocation((Win2.width-width)/2, (Win2.height-height)/2);
		this.setBackground(Color.black);
		//添加按钮组件
		but_start=new JButton("开始");
		but_stop=new JButton("暂停");
		but_restart=new JButton("重新开始");
		but_start.addActionListener(this);
		but_stop.addActionListener(this);
		but_restart.addActionListener(this);
		this.add(but_start);
		this.add(but_stop);
		this.add(but_restart);
		this.addKeyListener(this);
		//启动线程
		mythread=new Mythread();
		thread=new Thread(mythread);
		thread.start();
		initial();
	}
	public void initial()
	{
		snake1=new Snake1();
		snake=snake1.getSnake();
		food=snake1.getFood();
		flag=false;
		//启动线程
		/*mythread=new Mythread();
		thread=new Thread(mythread);
		thread.start();*/
	}
	public void paint(Graphics g) //绘制
	{
		super.paint(g);
		
		g.setColor(Color.red);
		for(int i=1;i<35;i++)  //竖线
		{
			g.drawLine(size*i, 60, size*i, 500);
		}
		for(int i=0;i<22;i++)  //横线
		{
			g.drawLine(0, 60+size*i, 700, 60+size*i);
		}
		
		g.setColor(Color.white);
		g.fillRect(food.getX(), food.getY(), size, size); //画食物
		for(int i=0;i<snake.size();i++)
		{
			g.fillRect(snake.get(i).getX(), snake.get(i).getY(), size, size); //画蛇
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();
		if(source==but_start)
		{
			flag=true;
		}
		else if(source==but_stop)
		{
			flag=false;
		}
		else if(source==but_restart)
		{
			initial();
			but_start.setEnabled(true);
		}
		this.requestFocus();  //为jpanel获取事件，防止键盘监听无效
	}
	public void keyPressed(KeyEvent e)
	{
		Node1 temp=new Node1(snake.get(0));
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			{
				//判断是否按相反方向，如果按相反方向则不改变方向
				temp.setX(snake.get(0).getX()+0);
				temp.setY(snake.get(0).getY()-size);
				if(temp.getX()==snake.get(1).getX()&&temp.getY()==snake.get(1).getY())break;
				
				snake1.setString("up");break;
			}
		case KeyEvent.VK_DOWN:
			{
				temp.setX(snake.get(0).getX()+0);
				temp.setY(snake.get(0).getY()+size);
				if(temp.getX()==snake.get(1).getX()&&temp.getY()==snake.get(1).getY())break;
				
				snake1.setString("down");break;
			}
		case KeyEvent.VK_LEFT:
			{
				temp.setX(snake.get(0).getX()-size);
				temp.setY(snake.get(0).getY()+0);
				if(temp.getX()==snake.get(1).getX()&&temp.getY()==snake.get(1).getY())break;
				
				snake1.setString("left");break;
			}
		case KeyEvent.VK_RIGHT:
			{
				temp.setX(snake.get(0).getX()+size);
				temp.setY(snake.get(0).getY()+0);
				if(temp.getX()==snake.get(1).getX()&&temp.getY()==snake.get(1).getY())break;
				
				snake1.setString("right");break;
			}
		}
		
		//System.out.println(direction);
		System.out.println(snake1.getString());
		System.out.println(snake.size());
		for(Node1 i:snake)
		{
			System.out.println(i.getX()+" "+i.getY());
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	//内部类为线程类
	class Mythread implements Runnable
	{
		public void run()
		{
			while(true)
			{
				if(flag)
				{
					snake1.eatFood();
					snake1.snakeMove();
					if(snake1.snakeDead())
						{
						JOptionPane.showMessageDialog(null, "Score："+snake.size(), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
						//break;
						flag=false;
						but_start.setEnabled(false);
						}
					try {
						//speed=530-snake.size()*10;
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				repaint();
			}
		}
	}
}
