package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class TeachersSearchForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField lastnameTxt;
	private String lastname = "";

	public TeachersSearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameTxt.setText("");
			}
		});
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource("eduv2.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 451);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(142, 89, 201, 37);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setBounds(202, 58, 73, 20);
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setForeground(new Color(160, 82, 45));
		contentPane.add(lblLastname);
		
		JButton SearchBtn = new JButton("Αναζήτηση");
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastname = lastnameTxt.getText();
				Main.getTeachersUpdateDeleteForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		SearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SearchBtn.setForeground(new Color(0, 0, 255));
		SearchBtn.setBounds(186, 137, 113, 37);
		contentPane.add(SearchBtn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(102, 40, 289, 161);
		contentPane.add(panel);
		
		JButton insertBtn = new JButton("Εισαγωγή");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersInsertForm().setVisible(true);
				Main.getTeachersSearchForm().setEnabled(false);
			}
		});
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setBounds(186, 238, 113, 37);
		contentPane.add(insertBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(102, 212, 289, 99);
		contentPane.add(panel_1);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(true);
				Main.getTeachersSearchForm().setVisible(false);
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeBtn.setForeground(new Color(0, 0, 255));
		closeBtn.setBounds(302, 345, 89, 37);
		contentPane.add(closeBtn);
	}

	public String getLastname() {
		return lastname;
	}
	
	
}
