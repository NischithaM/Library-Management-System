package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.BookDAO;

import javatest.BMSException;

public class BookByAuthorPanel extends JPanel{

	JButton btnsearch,btnback;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtbook_author;
	JLabel lblbook_author;
	JTable table;
	Vector<String> colNames= new Vector<>();
	JScrollPane scrollPane;
	String name;
	public BookByAuthorPanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;

		txtbook_author=new JTextField(10);
		lblbook_author=new JLabel("BOOK_AUTHOR");

		BookDAO dao=new BookDAO();
		colNames.add("BOOK_ISBN");
		colNames.add("BOOK TITLE");
		colNames.add("BOOK_CATEGORY");
		colNames.add("NO_OF_BOOKS");
		table=new JTable();
		scrollPane=new JScrollPane(table);


		btnsearch=new JButton("SEARCH");
		btnsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 name=txtbook_author.getText().trim();


				try
				{
					if(name.equals(""))
					{
						JOptionPane.showMessageDialog(currentpanel, "please enter author name","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}

					Vector<Vector<String>> data = dao.getAllBooksAuthorsTable(name); 

					if(data.size() > 0)
					{

						txtbook_author.setText("");

						table.setModel(new DefaultTableModel(data, colNames));
						return;
					}

					else
					{
						JOptionPane.showMessageDialog(currentpanel, "search not found","ERROR",JOptionPane.ERROR_MESSAGE);	
					}
				}
				catch(NumberFormatException nfe)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(currentpanel, "invalid data","ERROR",JOptionPane.ERROR_MESSAGE);

				}
				catch(BMSException bmse)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(currentpanel, bmse.getInfo(),"ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnback=new JButton("BACK");
		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				parent.remove(currentpanel);
				parent.add(new MainPanel(parent));
				parent.revalidate();

			}
		});


		currentpanel.add(lblbook_author);
		currentpanel.add(txtbook_author);
		currentpanel.add(btnsearch);
		currentpanel.add(btnback);
		currentpanel.add(scrollPane);
	}

}
