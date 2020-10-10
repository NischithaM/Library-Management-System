package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;

public class BookViewPanel extends JPanel{

	JButton btnback;
	JFrame parent;
	JPanel currentpanel;
	JTable table;
	JScrollPane scrollPane;


	Vector<String> colNames= new Vector<>();

	public BookViewPanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;


		btnback=new JButton("BACK");


		colNames.add("BOOK_ISBN");
		colNames.add("BOOK_TITLE");
		colNames.add("BOOK_CATEGORY");
		colNames.add("NO_OF_BOOKS");
		colNames.add("AUTHOR NAME");
		colNames.add("AUTHOR MAILID");

		BookDAO dao2=new BookDAO();
		table=new JTable(new DefaultTableModel(dao2.getAllBooksForJTable(),colNames));
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
