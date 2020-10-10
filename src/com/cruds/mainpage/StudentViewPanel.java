package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;
import com.cruds.db.StudentDAO;

public class StudentViewPanel  extends JPanel{

	JButton btnback;
	JFrame parent;
	JPanel currentpanel;
	JTable table;
	JScrollPane scrollPane;


	Vector<String> colNames= new Vector<>();

	public StudentViewPanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;


		btnback=new JButton("BACK");


		colNames.add("USN");
		colNames.add("NAME");


		StudentDAO dao=new StudentDAO();
		table=new JTable(new DefaultTableModel(dao.getAllStudentsForJTable(),colNames));
		scrollPane=new JScrollPane(table);
		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				parent.remove(currentpanel);
				parent.add(new MainPanel(parent));
				parent.revalidate();

			}
		});

		currentpanel.add(scrollPane);
		currentpanel.add(btnback);

	}


}
