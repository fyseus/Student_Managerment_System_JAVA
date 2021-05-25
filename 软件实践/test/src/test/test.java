package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class test implements ActionListener{
	JButton btn01,btn02;
    JTextField file0,file1,file2,file3;
    JPanel panel,imagePanel;
    JFrame frm;
    public void maketest(){
        frm = new JFrame("图书馆");
        frm.setSize(1000,800);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        panel = new JPanel(null);//主题容器
        btn01 = new JButton("返回");
        btn01.setBounds(0, 0, 100, 50);
        panel.add(btn01);
        btn02 = new JButton("已借书籍");
        btn02.setBounds(0, 50, 100, 30);
        panel.add(btn02);
        frm.setContentPane(panel);
        frm.setVisible(true);
        JButton btn03 = new JButton("搜索书籍");
        btn03.setBounds(100,50,100,30);
        panel.add(btn03);
        JButton btn04 = new JButton("借书历史");
        btn04.setBounds(200,50,100,30);
        panel.add(btn04);
        btn01.addActionListener(this);
        btn02.addActionListener(this);
        btn03.addActionListener(this);
        btn04.addActionListener(this);
   }
    public void image() {
    	ImageIcon background=new ImageIcon("src/img/bg14.jpg");
    	JLabel lb = new JLabel(background);
    	lb.setBounds(200, 40, 600, 600);
    	lb.setIcon(background);
    	frm.add(lb);
    	frm.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
    	 //int oldNum1 = Integer.parseInt(file1.getText());
         //int newNum1 = oldNum1;
    	 //int oldNum2 = Integer.parseInt(file3.getText());
         //int newNum2 = oldNum2;
         if (e.getActionCommand().equals("返回")){
        	 frm.dispose();
         }
         else if (e.getActionCommand().equals("已借书籍")) {
         }
         else if (e.getActionCommand().equals("搜索书籍")) {
         }
         else if (e.getActionCommand().equals("借书历史")) {
         }
    }
   public static void main(String args[]) {
        test i = new test();
        i.maketest();
        i.image();
    }
}
