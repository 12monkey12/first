package Mysnake2;

public class Node1 {

	private int x,y;
	public Node1() {}
	public Node1(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public Node1(Node1 node)
	{
		this.x=node.x;
		this.y=node.y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
}
