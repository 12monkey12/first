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
	 *��Ϸ���� 
	 */
	private static final long serialVersionUID = 1L;
	//���Ӵ�С����Ϸ���������������ꡢ��С��ʳ������
	private int size,glocationx,glocationy,gwidth,gheight,foodx,foody;
	Snake snake;
	JButton but_stop; //��ͣ��ť
	JButton but_start;//��ʼ��ť
	static boolean gameflag; //��Ϸ״̬��־
	ArrayList<Node> nodes;
	Thread thread;
	Node head;
	public Gwin()
	{
		size=10;  
		glocationx=glocationy=50;  //��Ϸ����λ��
		gwidth=700;                //��Ϸ�����С
		gheight=400;
		foodx=90;
		foody=80;
		but_start=new JButton("��ʼ");
		but_stop=new JButton("����");
		gameflag=false;            //һ��ʼ�߲���������ʼ��ť�߲Ŷ�
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
		g.setColor(Color.blue);    //������  �Ȼ��ߣ������߸�����������
		for(int i=0;i<nodes.size();i++)
		{
			g.fillRect(nodes.get(i).getX(), nodes.get(i).getY(), size, size);
		}
		g.setColor(Color.BLACK);
		for(int i=0;i<=70;i++)     //��������
		{
			g.drawLine(i*size+glocationx, 0+glocationy, i*size+glocationx, gheight+glocationy);
		}
		for(int i=0;i<=40;i++)     //���ƺ���
		{
			g.drawLine(0+glocationx, i*size+glocationy, gwidth+glocationx, i*size+glocationy);
		}
		g.setColor(Color.red);     //����ʳ��
		g.fillRect(foodx, foody, size, size);
		
	}
	public void actionPerformed(ActionEvent event)
	{
		Object source=event.getSource();
		if(source==but_start)
		{
			//�߳̿�ʼ
			gameflag=true;
		}
		else if(source==but_stop)
		{
			//�߳̽���
			gameflag=false;
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e)     //���Ʒ���  �ı���ͷ�������
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
		for(int i=0;i<70;i++)       //�ж�ײ����ǽ
		{
			if(head.getX()==i*size+glocationx&&(head.getY()==glocationy||head.getY()==gwidth+glocationy))
			{
				gameflag=false;
			}
		}
		for(int i=0;i<40;i++)       //�ж�ײ����ǽ
		{
			if((head.getX()==glocationx||head.getX()==glocationx+gheight)&&head.getY()==i*size+glocationy)
			{
				gameflag=false;
			}
		}
		for(int i=1;i<nodes.size();i++)//�ж�ײ�Լ�
		{
			int firstx=head.getX();
			int firsty=head.getY();
			if(firstx==nodes.get(i).getX()&&firsty==nodes.get(i).getY())
			{
				gameflag=false;
			}
		}
	}
	public void foodDisplay()        //����ʳ������
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
	public void move()              //���ƶ�  ���������߽ڵ������
	{
		if(!gameflag)return;
		Node First=head;
		switch(First.direction)     //�ƶ���ͷ
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
		for(int i=1;i<nodes.size();i++) //�ƶ�����
		{
			int x=nodes.get(i-1).getX();
			int y=nodes.get(i-1).getY();
			nodes.get(i).setX(x);
			nodes.get(i).setY(y);
			nodes.get(i).direction=nodes.get(i-1).direction;
		}
		head=First;
	}
	public boolean eat()                 //�߳Զ���
	{
		int x=0,y=0;
		Node tail=nodes.get(nodes.size()-1);  //��β�ڵ�
		String direction=tail.direction;
		if(head.getX()==foodx&&head.getY()==foody)//���߳Զ���ʱ����β���ӽڵ�
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
			nodes.add(new Node(x,y,direction));//���ӽڵ�
			
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
				repaint();                 //�Ȼ���ͼ�����ж�������ʵ����ͣ����
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