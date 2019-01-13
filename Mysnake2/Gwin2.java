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
 * ���������⣺
 * �Ƚ��ַ����Ƿ���ȣ���ֵ��equals()���ȶ�����==
 * �̵߳���ֹ��stop()����־λ��interrupt()
 * Jframe���ñ�����ɫ����ȡ�������������ɫ
 * �������⵼�µļ����¼��޷���Ӧ���ڼ����¼��л�ȡ����
 * �����ж��̣߳����޷������̣߳���������Ҫ������Ϸ���㡰���¿�ʼ����ťû�ã������ж��̣߳�ֻ����flagΪfalse
 */
public class Gwin2 extends JPanel implements ActionListener,KeyListener{//��Ϸ����

	//���������С�����꣬���Ӵ�С
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
		//���Ӵ�СΪ20����
		/*snake1=new Snake1();
		snake=snake1.getSnake();
		food=snake1.getFood();
		flag=false;*/
		/*
		 * 700/20=35
		 * ��ťҪռ�ռ�
		 * ��һ��������60���أ�500-60=440��440/20=22
		 * ������35������Ҫ������36���ߣ����ߵ�1���͵�36����Ҫ��
		 * ������22������Ҫ������23���ߣ����ߵ�23����Ҫ��
		 * ��Ҫ������34��������22��
		 * 0-700   60-500
		 * ���������С��λ��
		 */
		width=700;
		height=500;
		this.setSize(width, height);
		this.setLocation((Win2.width-width)/2, (Win2.height-height)/2);
		this.setBackground(Color.black);
		//��Ӱ�ť���
		but_start=new JButton("��ʼ");
		but_stop=new JButton("��ͣ");
		but_restart=new JButton("���¿�ʼ");
		but_start.addActionListener(this);
		but_stop.addActionListener(this);
		but_restart.addActionListener(this);
		this.add(but_start);
		this.add(but_stop);
		this.add(but_restart);
		this.addKeyListener(this);
		//�����߳�
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
		//�����߳�
		/*mythread=new Mythread();
		thread=new Thread(mythread);
		thread.start();*/
	}
	public void paint(Graphics g) //����
	{
		super.paint(g);
		
		g.setColor(Color.red);
		for(int i=1;i<35;i++)  //����
		{
			g.drawLine(size*i, 60, size*i, 500);
		}
		for(int i=0;i<22;i++)  //����
		{
			g.drawLine(0, 60+size*i, 700, 60+size*i);
		}
		
		g.setColor(Color.white);
		g.fillRect(food.getX(), food.getY(), size, size); //��ʳ��
		for(int i=0;i<snake.size();i++)
		{
			g.fillRect(snake.get(i).getX(), snake.get(i).getY(), size, size); //����
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
		this.requestFocus();  //Ϊjpanel��ȡ�¼�����ֹ���̼�����Ч
	}
	public void keyPressed(KeyEvent e)
	{
		Node1 temp=new Node1(snake.get(0));
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			{
				//�ж��Ƿ��෴����������෴�����򲻸ı䷽��
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
	//�ڲ���Ϊ�߳���
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
						JOptionPane.showMessageDialog(null, "Score��"+snake.size(), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
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
