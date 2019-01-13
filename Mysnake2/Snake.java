package Mysnake2;

import java.util.*;
class Node           //每一个节点除了位置变量之外还有方向变量，方便增加新的节点
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

	public Node head;       //头节点
	public static final int size=10;
	public ArrayList<Node> nodes;
	public int length,initialx,initialy;   //贪吃蛇长度、初始贪吃蛇头节点位置
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
