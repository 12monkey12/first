package Mysnake2;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.util.Random;

public class Win2 extends JFrame{  //��Ϸ����

	//���ڴ�С
	public static int width=800,height=500;
	private Toolkit toolkit;
	private Dimension screensize;
	private String title;
	private Image image;
	private Gwin2 gwin2;
	private Insets insets;
	private Dimension jframesize;
	
	public Win2()
	{ 
		this.setSize(width+6, height+35);
		//���ھ���
		toolkit=Toolkit.getDefaultToolkit();
		screensize=toolkit.getScreenSize();
		this.setLocation((screensize.width-width)/2, (screensize.height-height)/2);
		//����ͼ��ͱ���
		image=new ImageIcon(Win2.class.getResource("image/snake.jpg")).getImage();
		this.setIconImage(image);
		title="̰����";
		this.setTitle(title);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���panel���
		gwin2=new Gwin2();
		this.add(gwin2);
		//���ò��ֹ�����Ϊ�գ���������Ϸ���������õ�������Ч
		this.setLayout(null);
		//��ȡJFrame���������ͱ߿��С��Ҫ�����ڴ��ڿɼ�֮ǰ
		this.setVisible(true);
		insets=this.getInsets();
		jframesize=this.getContentPane().getSize();
		System.out.println("���ڱ߿��ϣ�"+insets.top);
		System.out.println("���ڱ߿��£�"+insets.bottom);
		System.out.println("���ڱ߿���"+insets.left);
		System.out.println("���ڱ߿��ң�"+insets.right);
		System.out.println("��������ȣ�"+jframesize.width);
		System.out.println("���ڱ߿�߶ȣ�"+jframesize.height);
		Random rand=new Random();
		System.out.println(rand.nextInt(20));
		System.out.println(Math.random());
	}
	public static void main(String[] arg)
	{
		new Win2();
	}
}
