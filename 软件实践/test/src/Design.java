import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Design extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design frame = new Design();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Design() {
		getContentPane().setBackground(new Color(0, 250, 154));
		setTitle("\u56FE\u4E66\u9986\uFF08\u7BA1\u7406\u5458\uFF09");
		setForeground(new Color(255, 182, 193));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Design.class.getResource("/img/bg14.jpg")));
		getContentPane().setForeground(new Color(255, 0, 255));
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setForeground(new Color(128, 128, 0));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 36, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 93, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u4E66\u76EE\u589E\u51CF");
		btnNewButton_1.setForeground(new Color(153, 50, 204));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 2, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, btnNewButton);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u4E66\u76EE\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.setForeground(new Color(148, 0, 211));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_2, 0, SpringLayout.NORTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 6, SpringLayout.EAST, btnNewButton_1);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u51FA\u501F\u4FE1\u606F");
		btnNewButton_3.setForeground(new Color(186, 85, 211));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_3, 6, SpringLayout.EAST, btnNewButton_2);
		getContentPane().add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 86, SpringLayout.SOUTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 114, SpringLayout.WEST, getContentPane());
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField, -116, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 85, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 358, SpringLayout.WEST, getContentPane());
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JButton btnNewButton_4 = new JButton("\u4FEE\u6539");
		btnNewButton_4.setForeground(new Color(205, 92, 92));
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -122, SpringLayout.WEST, btnNewButton_4);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_4, -39, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_4, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(btnNewButton_4);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -6, SpringLayout.WEST, textField);
		textField_2.setForeground(new Color(0, 255, 255));
		textField_2.setEditable(false);
		textField_2.setText("\u4E66\u540D");
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(0, 0, 205));
		textField_3.setEditable(false);
		textField_3.setText("\u4F5C\u8005\u540D");
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 12, SpringLayout.EAST, textField);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Design.class.getResource("/img/bg14.jpg")));
		panel.add(lblNewLabel);
	}
}
