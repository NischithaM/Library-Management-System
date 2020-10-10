package com.cruds.mainpage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	
	JButton btnViewBook,btnSearchbytitle,btnAddBook,btnAddBack,btnsearchbyauthor,btnsearchbycategory,btnissue,btnviewissue,btnstudent;
	JPanel panel;
	JFrame parent;
	
	
	MainPanel(JFrame parent)
	{
		 panel= this;
		 this.parent = parent;
			
			btnAddBook =new JButton("ADD BOOK");
			btnViewBook=new JButton("VIEW BOOKS");
			btnAddBack=new JButton("BACK");
			btnSearchbytitle=new JButton("SEARCH BY TITLE");
			btnsearchbyauthor=new JButton("SEARCH BY AUTHOR");
			btnsearchbycategory=new JButton("SEARCH BY CATEGORY");
			btnstudent=new JButton("ADD STUDENT");
			btnissue=new JButton("ISSUE BOOK");
			btnviewissue=new JButton("VIEW ISSUED BOOK");
		
			
			btnAddBook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					parent.remove(panel);
					parent.add(new BookInsertionPanel(parent));
					parent.revalidate();
				}
				
			});
			
			btnViewBook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new BookViewPanel(parent));
					parent.revalidate();
					}
			});
			
			btnSearchbytitle.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new BookByTitlePanel(parent));
					parent.revalidate();
				
				}
			});
			
			btnsearchbyauthor.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new BookByAuthorPanel(parent));
					parent.revalidate();
				}
			});

			btnsearchbycategory.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new BookByCategoryPanel(parent));
					parent.revalidate();
					}
			});
			
			btnstudent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new StudentPanel(parent));
					parent.revalidate();
				}
			});
			
			btnissue.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					parent.remove(panel);
					parent.add(new IssueBookPanel(parent));
					parent.revalidate();
				}
			});

			btnviewissue.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.remove(panel);
					parent.add(new BooksIssuedListPanel(parent));
					parent.revalidate();
					
				}
			});
		
			
			panel.add(btnAddBook);//add to jpanel
			panel.add(btnViewBook);
			panel.add(btnSearchbytitle);
			panel.add(btnsearchbyauthor);
	        panel.add(btnsearchbycategory);
	        panel.add(btnstudent);
			panel.add(btnissue);
			panel.add(btnviewissue);
			
			//add(panel);//add jpnael to frame
			//setVisible(true);
	}

}
