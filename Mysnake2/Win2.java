package Mysnake2;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.util.Random;

public class Win2 extends JFrame{  //游戏窗口

	//窗口大小
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
		//窗口居中
		toolkit=Toolkit.getDefaultToolkit();
		screensize=toolkit.getScreenSize();
		this.setLocation((screensize.width-width)/2, (screensize.height-height)/2);
		//设置图标和标题
		image=new ImageIcon(Win2.class.getResource("image/snake.jpg")).getImage();
		this.setIconImage(image);
		title="贪吃蛇";
		this.setTitle(title);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//添加panel组件
		gwin2=new Gwin2();
		this.add(gwin2);
		//设置布局管理器为空，否则在游戏区域中设置的坐标无效
		this.setLayout(null);
		//获取JFrame的内容面板和边框大小，要设置在窗口可见之前
		this.setVisible(true);
		insets=this.getInsets();
		jframesize=this.getContentPane().getSize();
		System.out.println("窗口边框上："+insets.top);
		System.out.println("窗口边框下："+insets.bottom);
		System.out.println("窗口边框左："+insets.left);
		System.out.println("窗口边框右："+insets.right);
		System.out.println("内容面板宽度："+jframesize.width);
		System.out.println("窗口边框高度："+jframesize.height);
		Random rand=new Random();
		System.out.println(rand.nextInt(20));
		System.out.println(Math.random());
	}
	public static void main(String[] arg)
	{
		new Win2();
	}
}
