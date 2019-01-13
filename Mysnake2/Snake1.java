package Mysnake2;

import java.util.ArrayList;
import java.util.Random;

public class Snake1 {

	private ArrayList<Node1> snake;
	private int snakex=10*size,snakey=10*size+60;  //��ʼ��ͷλ��
	private int foodx=size*3,foody=60+size*5;      //��ʼʳ��λ��
	public static final int length=3;    //��ʼ̰��ʼ����
	private Node1 food;                  //ʳ����
	private static final int size=Gwin2.size;
	private Random rand;
	private String direction="right";  //����
	public Snake1()
	{
		snake=new ArrayList<Node1>(length);
		food=new Node1(foodx,foody);
		rand=new Random();
		initial();
	}
	
	public ArrayList<Node1> getSnake()
	{
		return snake;
	}
	public Node1 getFood()
	{
		return food;
	}
	public String getString()
	{
		return direction;
	}
	public void setString(String str)
	{
		this.direction=str;
	}
	
	public void initial() //��ʼ����λ�ú�ʳ��λ��
	{
		snake.add(new Node1(snakex,snakey)); //�����ͷ
		for(int i=1;i<length;i++)
		{
			int tempx=snakex-size*i;
			int tempy=snakey;
			snake.add(new Node1(tempx,tempy));
		}
	}
	
	public boolean snakeDead()
	{
		Node1 head=snake.get(0);
		//ײ�Լ�
		for(int i=1;i<snake.size();i++)
		{
			if(head.getX()==snake.get(i).getX()&&head.getY()==snake.get(i).getY())return true;
		}
		//ײǽ
		if(head.getX()<0||head.getX()>700||head.getY()>500||head.getY()<60)return true;
		return false;
	}
	
	public void snakeMove()  //ˢ����λ��
	{
		Node1 head=snake.get(0);

		for(int i=snake.size()-1;i>0;i--) //����
		{
			int tempx=snake.get(i-1).getX();
			int tempy=snake.get(i-1).getY();
			snake.get(i).setX(tempx);
			snake.get(i).setY(tempy);
		}
		/*int tempx=snake.get(0).getX();
		int tempy=snake.get(0).getY();
		snake.get(0).setX(tempx+x);
		snake.get(0).setY(tempy+y);*/
		
		if(direction.equals("up"))       //��ͷ
		{
			head.setX(snake.get(0).getX()+0);
			head.setY(snake.get(0).getY()-size);
		}
		else if(direction.equals("down"))
		{
			head.setX(snake.get(0).getX()+0);
			head.setY(snake.get(0).getY()+size);
		}
		else if(direction.equals("left"))
		{
			head.setX(snake.get(0).getX()-size);
			head.setY(snake.get(0).getY()+0);
		}
		else if(direction.equals("right"))
		{
			head.setX(snake.get(0).getX()+size);
			head.setY(snake.get(0).getY()+0);
		}
	}
	public void foodChange()  //ˢ��ʳ��λ��
	{
		boolean flag;  //�ж�ʳ���Ƿ�ˢ�����ߵ��������棬��������ˢ��
		do {
			flag=false;
			food.setX(rand.nextInt(35)*size);
			food.setY(rand.nextInt(22)*size+60);
			for(Node1 i:snake)
			{
				if(i.getX()==food.getX()&&i.getY()==food.getY())flag=true;
			}
		}while(flag);
	}
	/*
	 * �߳���ʳ���ᷢ��������1.ˢ��ʳ�� 2.�߱䳤
	 */
	public void eatFood()
	{
		//���Ƿ����ʳ��
		if(snake.get(0).getX()==food.getX()&&snake.get(0).getY()==food.getY())
		{
			//ˢ��ʳ��
			foodChange();
			//�߱䳤
			Node1 newnode=new Node1(snake.get(snake.size()-1));
			snake.add(newnode);
			Gwin2.speed-=20;
		}
	}
	
}