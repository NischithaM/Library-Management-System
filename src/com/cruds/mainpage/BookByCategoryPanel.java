package com.cruds.mainpage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class BookByCategoryPanel extends JPanel{

	JButton btnsearch,btnback;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtbook_category;
	JLabel lblbook_category;
	JTable table;
	Vector<String> colNames= new Vector<>();
	JScrollPane scrollPane;
	final String[] deptId = {"1","2","3","4"};
	final String[] categoryNames = {"Technical","Aptitude","Theory","IT"};
	private JComboBox<String> categoryCombo = new JComboBox<String>(categoryNames);
	String selCategory;

	public BookByCategoryPanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;

		BookDAO dao=new BookDAO();
		// String category = null;
		colNames.add("BOOK_ISBN");
		colNames.add("BOOK TITLE");
		colNames.add("BOOK_CATEGORY");
		colNames.add("NO_OF_BOOKS");

		table=new JTable();

		scrollPane=new JScrollPane(table);


		txtbook_category=new JTextField(10);
		lblbook_category=new JLabel("BOOK_CATEGORY");
		lblbook_category.setPreferredSize(new Dimension(100, 127));
		lblbook_category.setMaximumSize(new Dimension(100, 127));



		categoryCombo.setSelectedIndex(-1);
		categoryCombo.setPreferredSize(new Dimension(140, 22));
		categoryCombo.setMaximumSize(new Dimension(140, 22));

		categoryCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent ae) {

				int index = categoryCombo.getSelectedIndex();
				selCategory = categoryNames[index];
				//String names=categoryNames[index];
				//lblbook_category.setText(names);

			}
		});

		btnsearch=new JButton("SEARCH");
		btnsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(selCategory == null)
					{
						JOptionPane.showMessageDialog(currentpanel, "please select category","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}

					Vector<Vector<String>> data = dao.getAllBooksCategoryJTable(selCategory); 

					if(data.size() > 0)
					{
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
		currentpanel.add(lblbook_category);
		currentpanel.add(categoryCombo);
		currentpanel.add(btnsearch);
		currentpanel.add(btnback);
		currentpanel.add(scrollPane);

	}

}
