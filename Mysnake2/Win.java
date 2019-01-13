package Mysnake2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
//import javax.swing.*;
//import java.awt.*;

public class Win extends JFrame {

	/*
	 * 游戏窗口
	 */
	private static final long serialVersionUID = 1L;
	private int width,height;  //窗口大小
	Gwin gwin;
	public Win()   //初始化界面
	{
		//注入游戏区域
		gwin=new Gwin();
		this.add(gwin);
		width=800;
		height=500;
		//窗口居中
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screensize=toolkit.getScreenSize();
		int screenwidth=screensize.width;
		int screenheight=screensize.height;
		this.setLocation((screenwidth-width)/2,(screenheight-height)/2);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		gwin.setBounds(0, 0, width, height);
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] arg)
	{
		new Win();
	}
}
