import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Myclendar extends JFrame {
	JLabel lab_year = new JLabel("年份"); // 创建指定文本“年份”和“月份”标签对象
	JLabel lab_month = new JLabel("月份");
	JComboBox com_year = new JComboBox(); // 创建年份和月份列表对象
	JComboBox com_month = new JComboBox();
	JLabel lab[] = new JLabel[49]; // 创建49个用来存放日期和星期的空标签数组对象
	JPanel pan1 = new JPanel(); // 创建放置年份和月份列表的面板1对象
	JPanel pan2 = new JPanel(new GridLayout(7, 7)); // 创建放置日期和星期的面板2对象
	JPanel pan = new JPanel(new BorderLayout()); // 创建放置面板1，2的主面板对象，且设置其为边界布局管理
	ImageIcon imageIcon = new ImageIcon(); // 创建图标格式对象
	Calendar calendar = Calendar.getInstance(); // 创建日历类返回的当前时间下的日历对象（getInstance()是返回当前时间下的日历）

	Myclendar() {// 将组件放置在面板上，然后将组件具体，再把该面板放置在设置好的窗口上
		com_year.setBackground(Color.WHITE); // 将列表背景色设置为白色
		com_month.setBackground(Color.WHITE);
		pan1.setOpaque(false); // 设置面板1透明
		pan1.add(lab_year); // 将年月标签和列表组件依次放置在面板1上
		pan1.add(com_year);
		pan1.add(lab_month);
		pan1.add(com_month);
		for (int i = 0; i < 49; i++) {
			lab[i] = new JLabel(""); // 初始化标签
			pan2.add(lab[i]); // 将空格标签加到面板2上
		}
		pan.add(pan1, BorderLayout.NORTH);// 将面板1放在总面板上，而且放到它的北面
		pan.add(pan2, BorderLayout.CENTER);// 将面板2放在总面板上，而且放到它的中间
		this.setContentPane(pan); // 把总面板放在内容窗格上
		this.init(); // 将面板中年月日星期具体化并进行监听
		com_year.addActionListener(new ClockActionListener()); // 给年月列表添加时间监听器，当作出选择时ActionListener将接收一个ActionEvent
		com_month.addActionListener(new ClockActionListener());
		imageIcon = new ImageIcon("C:\\Users\\CR\\Pictures\\1.gif"); // 根据图片创建一个图标对象
		JLabel imagelabel = new JLabel(imageIcon);// 把图标显示在一个标签里面
		imagelabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
		this.getLayeredPane().add(imagelabel, new Integer(Integer.MIN_VALUE));// 把放背景图片的标签放在分层面板底层
		pan.setOpaque(false); // 设置面板１，２和总面板透明，防止掩盖背景图片
		pan1.setOpaque(false);
		pan2.setOpaque(false);
		this.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight()); // 根据图片大小设置窗口大小
		this.setTitle("背景日历");
		this.setVisible(true); // 设置窗口可见
		this.setResizable(false); // 设置窗口大小不可更改
	}

	public void init() { // 设置我的日历具体年月日星期
		for (int i = 0; i < 10000; i++) { // 为年月列表添加项
			com_year.addItem("" + i);
		}
		for (int i = 1; i < 13; i++) {
			com_month.addItem("" + i);
		}
		int year_now, month_now, firstday_month; // 设置当前年月和当前月第一天等变量
		year_now = calendar.get(Calendar.YEAR); // 将返回的日历类中年份赋值给year_now变量
		month_now = calendar.get(Calendar.MONTH); // 将返回的日历类中月份赋值给month_now变量
		com_year.setSelectedIndex(year_now); // 选择列表中第year_now项
		com_month.setSelectedIndex(month_now); // 选择列表中第month_now项
		String week[] = { "日", "一 ", "二", "三", "四", "五", "六" };// 设置星期赋值给数组week[]
		for (int i = 0; i < 7; i++) { // 将星期依次放置在标签中
			lab[i].setText(week[i]); // 将星期文本依次放置在标签中（setText(String
										// text)方法是定义组件中所显示的文本字符串）
		}
		for (int i = 0; i < 49; i = i + 7) {
			lab[i].setForeground(Color.WHITE); // 将所有星期日前景色设置为白色
		}
		for (int i = 6; i < 49; i = i + 7) { // 将所有星期日设为黄色
			lab[i].setForeground(Color.WHITE); // 将所有星期六前景色设置为白色
		}
		firstday_month = getFirstDayOfMonth(year_now, month_now); // 由我的日历中getFirstDayOfMonth方法的到当前月第一天
		addDay(year_now, month_now, firstday_month); // 由我的日历中addDay方法得到放置在日历上的所有日期，如果列表没有选择某项，则如这两行显示日期
	}

	class ClockActionListener implements ActionListener { // 设置继承监听接口的时间监听类
		public void actionPerformed(ActionEvent e) { // 对列表发生操作时调用，即列表选择某项时调用
			int year_now, month_now, firstday_month; // 年月日变量
			year_now = Integer.parseInt(com_year.getSelectedItem().toString());// 将返回的当前所选项的整型值赋值给year_now
			month_now = Integer.parseInt(com_month.getSelectedItem().toString()) - 1; // 将返回的当前所选项的整型值赋值给month_now
			firstday_month = getFirstDayOfMonth(year_now, month_now); // 由我的日历中getFirstDayOfMonth方法得到当前月第一天
			addDay(year_now, month_now, firstday_month); // 由我的日历中addDay方法得到对应的具体日期
		}
	}

	public int getFirstDayOfMonth(int year_now, int month_now) // 由当前年月得到当前月第一天
	{
		int firstday_month;
		calendar.set(year_now, month_now, 1); // 设置/指定我的日历中当前年月和当前年月的第一天
		firstday_month = calendar.get(Calendar.DAY_OF_WEEK);// 返回第一天对应的星期的第几天的值，注意一个星期第一天是星期日
		return firstday_month; // 返回当前月第一天
	}

	public void addDay(int year_now, int month_now, int firstday_month) // 由当前年月和当前月第一天得到当前所有日期
	{
		int firstday = 1; // 设置第一天，最后一天，count等局部变量
		int lastday_month = 0;
		int count = 1;
		calendar.set(year_now, month_now, 1);// 设置/指定我的日历中当前年月和当前月第一天
		lastday_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 获得第一天所在月的最大值并赋值给lastday_month
		for (int j = 7; j < 49; j++) {
			lab[j].setText(""); // 初始化标签
		}
		firstday = firstday_month + 7 - 1; // 得到第一天在标签上的位置
		lastday_month = lastday_month + firstday - 1; // 得到最后一天在标签上的位置
		for (int i = firstday; i <= lastday_month; i++) // 把日期放置到标签上
		{
			lab[i].setText(count + "");
			count++;
		}
	}
}
