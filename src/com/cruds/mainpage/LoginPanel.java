package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cruds.db.UserDAO;

import javatest.BMSException;
import javatest.User;

public class LoginPanel extends JPanel {

	JTextField txtusername;
	JPasswordField password;
	JLabel lbluser,lblpassword;
	JFrame parent;
	JPanel currentpanel;
	JButton btnsubmit,btncancel;
	String username,pwd;
	
	public LoginPanel(JFrame parent) {
		
		this.parent=parent;
		currentpanel=this;
		
		txtusername=new JTextField(10);
		lbluser=new JLabel("USER NAME");
		
		password=new JPasswordField(10);
		lblpassword=new JLabel("PASSWORD");
		
		UserDAO dao=new UserDAO();
		
		btnsubmit=new JButton("SUBMIT");
		btnsubmit.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				username=txtusername.getText().trim();
				pwd=password.getText().trim();
				
				try
				{
					if(username.equals("") || pwd.equals(""))
					{
						JOptionPane.showMessageDialog(currentpanel, "please enter credentials","WARNING",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					//Vector<Vector<String>> data = dao.authenticate();
					User u=new User(username, pwd);
					
					if(dao.authenticate(u))
					{
						
						JOptionPane.showMessageDialog(currentpanel, "LOGIN SUCCESSFULL", "success", JOptionPane.INFORMATION_MESSAGE);
						parent.remove(currentpanel);
						parent.add(new MainPanel(parent));
						parent.revalidate();
					}
					
					else
					{
						txtusername.setText("");
						password.setText("");
						JOptionPane.showMessageDialog(currentpanel, "invalid credentials","ERROR",JOptionPane.ERROR_MESSAGE);	
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
		
		btncancel=new JButton("CANCEL");
		btncancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		currentpanel.add(lbluser);
		currentpanel.add(txtusername);
		
		currentpanel.add(lblpassword);
		currentpanel.add(password);
		currentpanel.add(btnsubmit);
		currentpanel.add(btncancel);
	}
	
}
