package com.cruds.mainpage;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import com.cruds.db.BookDAO;


import javatest.Author;
import javatest.BMSException;
import javatest.Book;



public class BookInsertionPanel extends JPanel {

	JButton btnCreate,btnback;;
	JPanel currentpanel;
	JFrame parent;
	JTextField txtbook_isbn,txtbook_title,txtbook_category,txtbook_counts,txtauthor_name,txtauthor_mail;
	JLabel lblbook_isbn,lblbook_title,lblbook_category,lblbook_counts,lblauthor_name,lblauthor_mail;

	final String[] deptId = {"1","2","3","4"};

	final String[] categoryNames = {"Technical","Aptitude","Theory","IT"};
	private JComboBox<String> categoryCombo = new JComboBox<String>(categoryNames);

	public BookInsertionPanel(JFrame parent) {


		this.parent=parent;
		currentpanel=this;

		txtbook_isbn=new JTextField(10);
		txtbook_title=new JTextField(10);
		txtbook_counts=new JTextField(10);
		txtauthor_name=new JTextField(10);
		txtauthor_mail=new JTextField(10);

		lblbook_isbn=new JLabel("BOOK_ISBN");
		lblbook_title=new JLabel("BOOK_TITLE");
		lblbook_counts=new JLabel("NO_OF_BOOKS");
		lblauthor_name=new JLabel("AUTHOR_NAME");
		lblauthor_mail=new JLabel("AUTHOR_MAIL_ID");

		lblbook_category=new JLabel("BOOK_CATEGORY");
		lblbook_category.setPreferredSize(new Dimension(100, 127));
		lblbook_category.setMaximumSize(new Dimension(100, 127));

		categoryCombo.setSelectedIndex(-1);
		categoryCombo.setPreferredSize(new Dimension(140, 22));
		categoryCombo.setMaximumSize(new Dimension(140, 22));

		btnCreate =new JButton("CREATE");

		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				String isbn=txtbook_isbn.getText().trim();
				String title=txtbook_title.getText().trim();
				String category=categoryCombo.getSelectedItem().toString();
				String count=txtbook_counts.getText().trim();
				String authorname=txtauthor_name.getText().trim();
				String mail=txtauthor_mail.getText().trim();

				categoryCombo.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent ae) {

						int index = categoryCombo.getSelectedIndex();
						String names=categoryNames[index];
						lblbook_category.setText(names);


					}
				});


				try
				{
					if(isbn.equals("") || title.equals("") || count.equals("")  || authorname.equals("") || mail.equals("")  )
					{
						JOptionPane.showMessageDialog(currentpanel, "inputs cannot be empty", "warning", JOptionPane.WARNING_MESSAGE);
						return;
					}


					Author author=new Author(authorname, mail);
					Book book=new Book(isbn, title,category, count, author);

					BookDAO dao=new BookDAO();
					if(dao.insert(book))
					{

						txtbook_isbn.setText("");
						txtbook_title.setText("");
						txtbook_counts.setText("");
						txtauthor_name.setText("");
						txtauthor_mail.setText("");
						parent.remove(currentpanel);
						parent.add(new BookViewPanel(parent));
						parent.revalidate();

					}

				}catch(NumberFormatException nfe)
				{
					getToolkit().beep();
					JOptionPane.showMessageDialog(currentpanel, "inavlid book_isbn", "error", JOptionPane.ERROR_MESSAGE);
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

		btnback=new JButton("BACK");
		btnback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.remove(currentpanel);
				parent.add(new MainPanel(parent));
				parent.revalidate();

			}
		});


		currentpanel.add(lblbook_isbn);
		currentpanel.add(txtbook_isbn);

		currentpanel.add(lblbook_title);
		currentpanel.add(txtbook_title);

		currentpanel.add(lblbook_counts);
		currentpanel.add(txtbook_counts);

		currentpanel.add(lblauthor_name);
		currentpanel.add(txtauthor_name);

		currentpanel.add(lblauthor_mail);
		currentpanel.add(txtauthor_mail);

		currentpanel.add(lblbook_category);
		currentpanel.add(categoryCombo);

		currentpanel.add(btnCreate);
		currentpanel.add(btnback);

		setVisible(true);

	}

}
