package Mysnake2;

//import java.awt.event.*;
//import javax.swing.*;
//import java.awt.*;
//import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Gwin extends JPanel implements ActionListener,KeyListener{

	/*
	 *游戏区域 
	 */
	private static final long serialVersionUID = 1L;
	//格子大小，游戏区域横坐标和纵坐标、大小，食物坐标
	private int size,glocationx,glocationy,gwidth,gheight,foodx,foody;
	Snake snake;
	JButton but_stop; //暂停按钮
	JButton but_start;//开始按钮
	static boolean gameflag; //游戏状态标志
	ArrayList<Node> nodes;
	Thread thread;
	Node head;
	public Gwin()
	{
		size=10;  
		glocationx=glocationy=50;  //游戏界面位置
		gwidth=700;                //游戏界面大小
		gheight=400;
		foodx=90;
		foody=80;
		but_start=new JButton("开始");
		but_stop=new JButton("结束");
		gameflag=false;            //一开始蛇不动，按开始按钮蛇才动
		this.add(but_start);
		this.add(but_stop);
		but_start.addActionListener(this);
		but_stop.addActionListener(this);
		this.addKeyListener(this);
		snake=new Snake();
		nodes=snake.nodes;
		head=snake.head;
		thread=new Thread(new Mythread());
		thread.start();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.blue);    //绘制蛇  先画蛇，避免蛇覆盖了网格线
		for(int i=0;i<nodes.size();i++)
		{
			g.fillRect(nodes.get(i).getX(), nodes.get(i).getY(), size, size);
		}
		g.setColor(Color.BLACK);
		for(int i=0;i<=70;i++)     //绘制竖线
		{
			g.drawLine(i*size+glocationx, 0+glocationy, i*size+glocationx, gheight+glocationy);
		}
		for(int i=0;i<=40;i++)     //绘制横线
		{
			g.drawLine(0+glocationx, i*size+glocationy, gwidth+glocationx, i*size+glocationy);
		}
		g.setColor(Color.red);     //绘制食物
		g.fillRect(foodx, foody, size, size);
		
	}
	public void actionPerformed(ActionEvent event)
	{
		Object source=event.getSource();
		if(source==but_start)
		{
			//线程开始
			gameflag=true;
		}
		else if(source==but_stop)
		{
			//线程结束
			gameflag=false;
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e)     //控制方向  改变蛇头方向变量
	{
		if(!gameflag)return;
		switch(e.getKeyCode())  
		{
		case KeyEvent.VK_UP:head.direction="U";break;
		case KeyEvent.VK_DOWN:head.direction="D";break;
		case KeyEvent.VK_LEFT:head.direction="L";break;
		case KeyEvent.VK_RIGHT:head.direction="R";break;
		}
	}
	public void dead()
	{
		for(int i=0;i<70;i++)       //判断撞上下墙
		{
			if(head.getX()==i*size+glocationx&&(head.getY()==glocationy||head.getY()==gwidth+glocationy))
			{
				gameflag=false;
			}
		}
		for(int i=0;i<40;i++)       //判断撞左右墙
		{
			if((head.getX()==glocationx||head.getX()==glocationx+gheight)&&head.getY()==i*size+glocationy)
			{
				gameflag=false;
			}
		}
		for(int i=1;i<nodes.size();i++)//判断撞自己
		{
			int firstx=head.getX();
			int firsty=head.getY();
			if(firstx==nodes.get(i).getX()&&firsty==nodes.get(i).getY())
			{
				gameflag=false;
			}
		}
	}
	public void foodDisplay()        //设置食物坐标
	{
		while(true)
		{
			int i=0;
			for(;i<nodes.size();i++)
			{
				if(foodx==nodes.get(i).getX()&&foody==nodes.get(i).getY())
				{
					foodx=(int)(Math.random()*70)*10;
					foody=(int)(Math.random()*40)*10;
					break;
				}
			}
			if(i==nodes.size()-1)return;
		}
	}
	public void move()              //蛇移动  设置整个蛇节点的坐标
	{
		if(!gameflag)return;
		Node First=head;
		switch(First.direction)     //移动蛇头
		{
		case "R":
		{
			First.setX(First.getX()+size);
		}
		case "L":
		{
			First.setX(First.getX()-size);
		}
		case "U":
		{
			First.setY(First.getY()-size);
		}
		case "D":
		{
			First.setY(First.getY()+size);
		}
		}
		for(int i=1;i<nodes.size();i++) //移动蛇身
		{
			int x=nodes.get(i-1).getX();
			int y=nodes.get(i-1).getY();
			nodes.get(i).setX(x);
			nodes.get(i).setY(y);
			nodes.get(i).direction=nodes.get(i-1).direction;
		}
		head=First;
	}
	public boolean eat()                 //蛇吃东西
	{
		int x=0,y=0;
		Node tail=nodes.get(nodes.size()-1);  //蛇尾节点
		String direction=tail.direction;
		if(head.getX()==foodx&&head.getY()==foody)//当蛇吃东西时在蛇尾增加节点
		{
			switch(direction)                  
			{
			case "R":
			{
				x=tail.getX()-size;
				y=tail.getY();
			}
			case "L":
			{
				x=tail.getX()+size;
				y=tail.getY();
			}
			case "U":
			{
				x=tail.getX();
				y=tail.getY()+size;
			}
			case "D":
			{
				x=tail.getX();
				y=tail.getY()-size;
			}
			}
			nodes.add(new Node(x,y,direction));//增加节点
			
			for(int i=0;i<nodes.size();i++)    
			{
				System.out.print(nodes.get(i).getX()+","+nodes.get(i).getY()+" ");
			}
			
			return true;
		}
		else return false;
	}
	class Mythread implements Runnable   
	{
		public void run()
		{
			while(true)
			{
				System.out.println(gameflag);
				repaint();                 //先绘制图案后判断死亡可实现暂停功能
				if(gameflag)
				{
					System.out.println(gameflag);
					if(eat())foodDisplay();
					move();
					dead();
					try 
					{
						Thread.sleep(40);
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	/*
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
}