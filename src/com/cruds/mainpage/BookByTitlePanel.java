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
import javatest.Book;

public class BookByTitlePanel extends JPanel{

	JButton btnsearch,btnback;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtbook_title;
	JLabel lblbook_title;
	JTable table;
	Vector<String> colNames= new Vector<>();
	JScrollPane scrollPane;
	String title;	
	
	public BookByTitlePanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;
		table=new JTable();
		
		 txtbook_title=new JTextField(10);
		 lblbook_title=new JLabel("BOOK_TITLE");
		
		 
		 BookDAO dao=new BookDAO();
		 
		 colNames.add("BOOK_ISBN");
		 colNames.add("BOOK TITLE");
		 colNames.add("BOOK_CATEGORY");
		 colNames.add("NO_OF_BOOKS");
		//table=new JTable(new DefaultTableModel(dao.getAllBooksTitleJTable(title),colNames));
		 table=new JTable();
		 scrollPane=new JScrollPane(table);
		 
		 table.setVisible(false);
		 
		 btnsearch=new JButton("SEARCH");
		 btnback=new JButton("BACK");
		 btnsearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				 title=txtbook_title.getText().trim();
			    
				try
				{
					if(title.equals(""))
					{
						JOptionPane.showMessageDialog(currentpanel, "please enter title","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					Vector<Vector<String>> data = dao.getAllBooksTitleJTable(title); 
			
					if(data.size() > 0)
					{
						
						txtbook_title.setText("");
						
						//table=new JTable(new DefaultTableModel(data,colNames));
						table.setVisible(true);
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
		 
		 
		  
		 btnback.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					parent.remove(currentpanel);
					parent.add(new MainPanel(parent));
					parent.revalidate();
					
				}
			});

		 
		 currentpanel.add(lblbook_title);
		 currentpanel.add(txtbook_title);
		 currentpanel.add(btnsearch);
		 currentpanel.add(btnback);
		 currentpanel.add(scrollPane);

	}
	
}
