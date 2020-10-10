package com.cruds.mainpage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import com.cruds.db.StudentDAO;
import com.cruds.util.DateUtil;

import javatest.BMSException;
import javatest.Issue;

public class IssueBookPanel extends JPanel {

	JButton btnissue,btnback ,btnusn, btntitle;
	JFrame parent;
	JPanel currentpanel;
	JTable table,table1;
	JScrollPane scrollpane,scrollpane1;

	JTextField txtbook_title,txtbook_isbn,txtusn;
	JLabel lblbook_title,lblbook_isbn,lblusn;
	String  studentid, title;
	boolean issueid;

	Vector<String> colNames= new Vector<>();
	Vector<String> colNames1= new Vector<>();

	IssueBookPanel(JFrame parent)
	{
		this.parent=parent;
		currentpanel=this;

		txtbook_isbn=new JTextField(10);
		txtusn=new JTextField(10);
		txtbook_title=new JTextField(10);

		lblbook_isbn=new JLabel("BOOK_ISBN");
		lblusn=new JLabel("USN");
		lblbook_title=new JLabel("BOOK_TITLE");


		IssueDAO dao=new IssueDAO();
		colNames.add("USN");
		colNames.add("NAME");
		table=new JTable(new DefaultTableModel(dao.getAllStudentsForJTable(studentid),colNames));
		scrollpane=new JScrollPane(table);

		BookDAO dao1=new BookDAO();
		colNames1.add("BOOK_ISBN");
		colNames1.add("BOOK_TITLE");
		colNames1.add("BOOK_CATEGORY");
		colNames1.add("NO_OF_BOOKS");
		table1=new JTable(new DefaultTableModel(dao1.getAllBooksTitleJTable(title),colNames1));
		scrollpane1=new JScrollPane(table1);

		btnusn=new JButton("SEARCH USN");
		btnusn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentid=txtusn.getText().trim();
				try
				{
					if(studentid.equals(""))
					{
						JOptionPane.showMessageDialog(currentpanel, "please enter usn","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}

					Vector<Vector<String>> data = dao.getAllStudentsForJTable(studentid);


					if(data.size() > 0)
					{

						txtusn.setText("");
						table.setModel(new DefaultTableModel(data, colNames));
						//JOptionPane.showMessageDialog(currentpanel, "successful", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
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

		btntitle=new JButton("SEARCH BOOK");
		btntitle.addActionListener(new ActionListener() {

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

					Vector<Vector<String>> titledata = dao1.getAllBooksTitleJTable(title);

					if(titledata.size() > 0)
					{

						txtbook_title.setText("");
						table1.setModel(new DefaultTableModel(titledata, colNames1));
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

		btnissue =new JButton("ISSUE");

		btnissue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index=table.getSelectedRow();
				int index1=table1.getSelectedRow();

				if(title== null || studentid==null)
				{
					JOptionPane.showMessageDialog(currentpanel, "please select details","WARNING",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String selrollno= (String)table.getModel().getValueAt(index,0);
				String selisbn=(String)table1.getModel().getValueAt(index1, 0);
				String count=(String) table1.getModel().getValueAt(index1, 3);
				//System.out.println(count);
				int bcount=Integer.parseInt(count);
				//System.out.println(bcount);
				IssueDAO dao=new IssueDAO();
				Issue issue=new Issue(DateUtil.getCurrentDate(),DateUtil.addToCurrentDate(7),selrollno,selisbn);
				int issueid = dao.create(issue);	
				if(dao.updateBookCount(selisbn)>0)
				{
					if(issueid > 0)
					{
						table.setModel(new DefaultTableModel());
						table1.setModel(new DefaultTableModel());
						JOptionPane.showMessageDialog(currentpanel, issueid+" issued successfully", "success", JOptionPane.INFORMATION_MESSAGE);
						//bcount--;
					}
					//System.out.println(bcount);
				}
				else
				{
					JOptionPane.showMessageDialog(currentpanel, "book unavailable","ERROR",JOptionPane.ERROR_MESSAGE);
					return;
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

		currentpanel.add(lblusn);
		currentpanel.add(txtusn);
		currentpanel.add(btnusn);

		currentpanel.add(lblbook_title);
		currentpanel.add(txtbook_title);
		currentpanel.add(btntitle);

		currentpanel.add(btnissue);
		currentpanel.add(btnback);
		currentpanel.add(scrollpane);
		currentpanel.add(scrollpane1);
	}

}

