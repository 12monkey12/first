package Mysnake2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
//import javax.swing.*;
//import java.awt.*;

public class Win extends JFrame {

	/*
	 * ��Ϸ����
	 */
	private static final long serialVersionUID = 1L;
	private int width,height;  //���ڴ�С
	Gwin gwin;
	public Win()   //��ʼ������
	{
		//ע����Ϸ����
		gwin=new Gwin();
		this.add(gwin);
		width=800;
		height=500;
		//���ھ���
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
