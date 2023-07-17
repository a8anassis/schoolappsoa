package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private static Connection connection;

	public Menu() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource("eduv2.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		setBackground(new Color(239, 239, 239));
		setTitle("Μενού Διαχείρισης Σχολικού Συστήματος");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 239, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.setBounds(23, 112, 48, 48);
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setVisible(true);
				Main.getMenu().setEnabled(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(teachersBtn);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(23, 179, 48, 48);
		contentPane.add(studentsBtn);
		
		JLabel lblEdyQuality2 = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblEdyQuality2.setBounds(61, 23, 347, 48);
		lblEdyQuality2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEdyQuality2.setForeground(new Color(0, 128, 128));
		contentPane.add(lblEdyQuality2);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setBounds(81, 125, 103, 24);
		lblTeachers.setForeground(new Color(139, 69, 19));
		lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTeachers);
		
		JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
		lblStudents.setBounds(81, 189, 150, 29);
		lblStudents.setForeground(new Color(160, 82, 45));
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblStudents);
		
		JLabel lblEduQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblEduQuality1.setBounds(63, 25, 347, 48);
		lblEduQuality1.setForeground(new Color(0, 0, 0));
		lblEduQuality1.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblEduQuality1);
	}
}
