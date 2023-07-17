package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.List;
import java.util.Objects;


public class TeachersUpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;

	ITeacherDAO dao = new TeacherDAOImpl();
	ITeacherService teacherService = new TeacherServiceImpl(dao);

	private int listSize = 0;
	private int listPosition = 0;
	private List<Teacher> teachers;

	public TeachersUpdateDeleteForm() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource("eduv2.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

				try {
					teachers = teacherService.getTeachersByLastname(Main.getTeachersSearchForm().getLastname());

					listSize = teachers.size();
					if (listSize == 0) {
						JOptionPane.showMessageDialog(null, "No Teachers Found", "Info", JOptionPane.ERROR_MESSAGE);
						Main.getTeachersSearchForm().setEnabled(true);
						Main.getTeachersUpdateDeleteForm().setVisible(false);
						return;
					}
					int listPosition = 0;
					idTxt.setText(Integer.toString(teachers.get(listPosition).getId()));
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
				} catch (TeacherDAOException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error in getting teachers", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 22, 17, 14);
		contentPane.add(lblNewLabel);
		
		idTxt = new JTextField();
		idTxt.setBackground(new Color(255, 255, 183));
		idTxt.setEditable(false);
		idTxt.setBounds(109, 19, 44, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(53, 61, 49, 14);
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(109, 58, 151, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Επώνυμο");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(38, 100, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(109, 97, 151, 20);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 317, 125);
		contentPane.add(panel);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSize > 0) {
					listPosition = 0;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});


		URL firstBtnUrl = classLoader.getResource("firstrecord.png");
		ImageIcon icon = new ImageIcon(firstBtnUrl);
		firstBtn.setIcon(icon);

		//setIconImage(Toolkit.getDefaultToolkit().getImage(url));

		firstBtn.setBounds(69, 151, 49, 36);
		contentPane.add(firstBtn);
		
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition > 0) {
					listPosition--;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});

		//setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png")));
		prevBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
		prevBtn.setBounds(119, 151, 49, 36);
		contentPane.add(prevBtn);
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPosition <= listSize - 2) {
					listPosition++;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});
		//nextBtn.setIcon(setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
		nextBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));
		nextBtn.setBounds(169, 151, 49, 36);
		contentPane.add(nextBtn);
		
		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSize > 0) {
					listPosition = listSize - 1;
					idTxt.setText(String.format("%s", teachers.get(listPosition).getId()));
					lastnameTxt.setText(teachers.get(listPosition).getLastname());
					firstnameTxt.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});


		lastBtn.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
		lastBtn.setBounds(219, 151, 49, 36);
		contentPane.add(lastBtn);
		// setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png")));
		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(new Color(0, 0, 255));
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTxt.getText().trim();
				String lastname = lastnameTxt.getText().trim();
				String firstname = firstnameTxt.getText().trim();

				if (lastname.equals("") || firstname.equals("") || id.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					TeacherUpdateDTO teacherDTO = new TeacherUpdateDTO();
					teacherDTO.setId(Integer.parseInt(id));
					teacherDTO.setFirstname(firstname);
					teacherDTO.setLastname(lastname);

					Teacher teacher = teacherService.updateTeacher(teacherDTO);
					JOptionPane.showMessageDialog(null, "Teacher with id " + teacher.getId()
							+ " was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
				} catch (TeacherDAOException | TeacherNotFoundException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		updateBtn.setBounds(99, 225, 100, 46);
		contentPane.add(updateBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int response;
					String idStr = idTxt.getText();
					int id = Integer.parseInt(idStr);

					response = JOptionPane.showConfirmDialog (null, "Είστε σίγουρος;",
							"Warning", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION){
						teacherService.deleteTeacher(id);
						JOptionPane.showMessageDialog (null, "Teacher was deleted successfully",
								"DELETE", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (TeacherDAOException | TeacherNotFoundException e1) {
					String message = e1.getMessage();
					JOptionPane.showMessageDialog (null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(202, 225, 100, 46);
		contentPane.add(deleteBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersSearchForm().setEnabled(true);
				Main.getTeachersUpdateDeleteForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(305, 225, 100, 46);
		contentPane.add(closeBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 202, 390, 1);
		contentPane.add(separator);
	}
}
