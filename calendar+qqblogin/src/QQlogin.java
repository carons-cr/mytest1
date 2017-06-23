import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class QQlogin {
	QQlogin(String title) {
		JFrame jframe = new JFrame();
		jframe.setLocation(350,200);  //窗口的坐标
		jframe.setTitle(title);   //窗口标题
		jframe.setSize(300,200);  //窗口大小
		jframe.setVisible(true);  //设置窗口为可见
		JPanel jpanel = new JPanel(); 
		BorderLayout borderlayout = new BorderLayout();
		JButton jbutton1 = new JButton("登录");  //设置按钮
		JButton jbutton2 = new JButton("取消");
		JLabel lab1 = new JLabel("用户名");  //设置标签
		JLabel lab2 = new JLabel("密码");
		JTextField jtfl = new JTextField(20);    //文本框长度
		JPasswordField jp = new JPasswordField(20);
		jbutton1.setBounds(100,100,60,22);   //组件的坐标和宽度高度。
		jbutton2.setBounds(189,100,60,22);
		lab1.setBounds(50,35,80,22);
		lab2.setBounds(50,65,80,22);
		jtfl.setBounds(100,35,150,22);
		jp.setBounds(100,65,150,22);
		jpanel.setLayout(null);  //设置布局
		jpanel.add(jbutton1);    //将所设置的加入面板
		jpanel.add(jbutton2);
		jpanel.add(lab1);
		jpanel.add(lab2);
		jpanel.add(jtfl );  
		jpanel.add(jp);
		jframe.add(jpanel);   //将面板加到此窗口中
		jframe.setResizable(false);  //设置窗口不可更改大小
		jframe.getDefaultCloseOperation();   //默认关闭
		//jframe.setDefaultCloseOperation(1);
	}
	public static void main(String[] args) {
		QQlogin qq = new QQlogin("QQ");
		Myclendar myclendar = new Myclendar();
	}
}
