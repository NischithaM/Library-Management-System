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
import com.cruds.db.IssueDAO;

import javatest.BMSException;

public class BooksIssuedListPanel extends JPanel {
	
	JButton btnsearch,btnback;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtusn;
	JLabel lblusn;
	JTable table;
	Vector<String> colNames= new Vector<>();
	JScrollPane scrollPane;
	String usn;	
	
    public BooksIssuedListPanel(JFrame parent) {
		this.parent=parent;
		currentpanel=this;
		table=new JTable();
		
		 txtusn=new JTextField(10);
		 lblusn=new JLabel("USN");
		
		IssueDAO dao=new IssueDAO();
		 
		 colNames.add("USN");
		 colNames.add("BOOK ISBN");
		 colNames.add("ISSUED DATE");
		 colNames.add("RETURNDATE");
		 table=new JTable(new DefaultTableModel(dao.getListIssueBookForJTable(usn),colNames));
		 scrollPane=new JScrollPane(table);
		 
		 btnsearch=new JButton("SEARCH");
		 btnback=new JButton("BACK");
		 btnsearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				usn=txtusn.getText().trim();
			    
				try
				{
					if(usn.equals(""))
					{
						JOptionPane.showMessageDialog(currentpanel, "please select student","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					Vector<Vector<String>> data = dao.getListIssueBookForJTable(usn); 
			
					if(data.size() > 0)
					{
						
						txtusn.setText("");
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

		 
		 currentpanel.add(lblusn);
		 currentpanel.add(txtusn);
		 currentpanel.add(btnsearch);
		 currentpanel.add(btnback);
		 currentpanel.add(scrollPane);

	}
	
}
