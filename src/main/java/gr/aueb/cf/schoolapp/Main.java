package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.viewcontroller.Menu;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersInsertForm;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersSearchForm;
import gr.aueb.cf.schoolapp.viewcontroller.TeachersUpdateDeleteForm;

import java.awt.EventQueue;

public class Main {

	private static Menu menu;
	private static TeachersSearchForm teachersSearchForm;
	private static TeachersInsertForm teachersInsertForm;
	private static TeachersUpdateDeleteForm teachersUpdateDeleteForm;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setVisible(true);
					
					teachersSearchForm = new TeachersSearchForm();
					teachersSearchForm.setVisible(false);
					
					teachersInsertForm = new TeachersInsertForm();
					teachersInsertForm.setVisible(false);
					
					teachersUpdateDeleteForm = new TeachersUpdateDeleteForm();
					teachersUpdateDeleteForm.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}

	public static TeachersSearchForm getTeachersSearchForm() {
		return teachersSearchForm;
	}

	public static TeachersInsertForm getTeachersInsertForm() {
		return teachersInsertForm;
	}

	public static TeachersUpdateDeleteForm getTeachersUpdateDeleteForm() {
		return teachersUpdateDeleteForm;
	}
	
	
}
