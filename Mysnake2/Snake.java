package Mysnake2;

import java.util.*;
class Node           //ÿһ���ڵ����λ�ñ���֮�⻹�з�����������������µĽڵ�
{
	public String direction;
	private int x,y;
	public Node(int x,int y,String direction)
	{
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public int getX() {return x;}
	public int getY() {return y;}
}
public class Snake {

	public Node head;       //ͷ�ڵ�
	public static final int size=10;
	public ArrayList<Node> nodes;
	public int length,initialx,initialy;   //̰���߳��ȡ���ʼ̰����ͷ�ڵ�λ��
	public Snake()
	{
		length=3;
		initialx=400;
		initialy=300;
		nodes=new ArrayList<>();
		originalSnake();
		head=nodes.get(0);
	}
	public void originalSnake()
	{
		for(int i=0;i<length;i++)
		{
			nodes.add(new Node(initialx-i*size,initialy,"R"));
	        System.out.print(nodes.get(i).getX()+","+nodes.get(i).getY()+" ");
			//System.out.println(nodes.get(nodes.size()-1).getX()+" "+nodes.get(nodes.size()-1).getY());
		}
	}
}
