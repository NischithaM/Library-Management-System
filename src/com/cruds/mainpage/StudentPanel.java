package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cruds.db.BookDAO;
import com.cruds.db.IssueDAO;
import com.cruds.db.StudentDAO;

import javatest.Author;
import javatest.BMSException;
import javatest.Book;
import javatest.Student;

public class StudentPanel extends JPanel{

	JButton btnCreate,btnback,btnview;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtusn,txtname;
	JLabel lblusn,lblname;

	StudentPanel(JFrame parent)
	{
		this.parent=parent;
		currentpanel=this;

		txtusn=new JTextField(10);
		txtname=new JTextField(10);

		lblusn=new JLabel("USN");
		lblname=new JLabel("NAME");


		btnCreate=new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String usn=txtusn.getText().trim();
				String name=txtname.getText().trim();

				try
				{
					if(usn.equals("") || name.equals("") )
					{
						JOptionPane.showMessageDialog(currentpanel, "inputs cannot be empty", "warning", JOptionPane.WARNING_MESSAGE);
						return;
					}


					Student student=new Student(usn, name);

					StudentDAO dao=new StudentDAO();
					if(dao.insert(student))
					{

						txtusn.setText("");
						txtname.setText("");

						parent.remove(currentpanel);
						parent.add(new StudentViewPanel(parent));
						parent.revalidate();

					}

				}catch(NumberFormatException nfe)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(currentpanel, "inavlid usn", "error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPointerException npe)
				{
					npe.printStackTrace();
				}
				catch(BMSException smse)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(currentpanel, smse.getInfo(), "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnview=new JButton("VIEW STUDENTS");
		btnview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.remove(currentpanel);
				parent.add(new StudentViewPanel(parent));
				parent.revalidate();
				
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

		currentpanel.add(lblname);
		currentpanel.add(txtname);

		currentpanel.add(btnCreate);
		currentpanel.add(btnview);
		currentpanel.add(btnback);
		
		setVisible(true);
	}

}
