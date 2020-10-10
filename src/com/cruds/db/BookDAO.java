package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javatest.BMSException;
import javatest.Book;
import javatest.DataBaseConnectionManager;

public class BookDAO {

	public Vector<Vector<String>> getAllBooksForJTable()
	{
		String sql="select b.book_isbn,b.book_title,b.book_category,b.no_of_books,a.author_name,a.author_mail_id  from book b ,author a where b.book_isbn= a.book_isbn";

		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs != null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				data.add(row);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		//System.out.println("List size" + data.size());
		return data;
	}

	public Vector<Vector<String>> getAllBooksTitleJTable(String book_title) //obtaining data from book database.
	{

		book_title = "%" + book_title + "%";
		String sql="select book_isbn,book_title,book_category,no_of_books from book where book_title like ?";
		//Book b=null;

		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, book_title);
			ResultSet rs=ps.executeQuery();

			while(rs != null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				data.add(row);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}


	public Vector<Vector<String>> getAllBooksAuthorsTable(String author_name) //obtaining data from book database.
	{

		author_name = "%" + author_name + "%";
		String sql="select b.book_isbn,b.book_title,b.book_category ,b.no_of_books from book b ,author a where b.book_isbn = a.book_isbn and a.author_name like ? group by a.author_name";


		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, author_name);
			ResultSet rs=ps.executeQuery();

			while(rs != null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				data.add(row);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public Vector<Vector<String>> getAllBooksCategoryJTable(String book_category)
	{

		String sql="select book_isbn,book_title,book_category,no_of_books from book where book_category = ?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, book_category);
			ResultSet rs=ps.executeQuery();

			while(rs != null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				data.add(row);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}




	public boolean insert(Book b)
	{
		String sql="insert into book(book_isbn,book_title,book_category,no_of_books) values(?,?,?,?)";
		String sql1="insert into author(author_name,author_mail_id,book_isbn) values (?,?,?)";



		int rows=0,row2=0;;

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, b.getBook_isbn());
			ps.setString(2, b.getBook_title());
			ps.setString(3, b.getBook_category());
			ps.setString(4, b.getNo_of_books());
			rows=ps.executeUpdate();

			ps=con.prepareStatement(sql1);
			ps.setString(1, b.getAuthor().getAuthor_name());
			ps.setString(2, b.getAuthor().getAuthor_mailid());
			ps.setString(3, b.getBook_isbn());
			row2=ps.executeUpdate();


		}
		catch(SQLException e)
		{
			e.printStackTrace();

			if(e.getMessage().contains("duplicate"))
			{
				throw new BMSException(b.getBook_isbn() +"already exits ! duplicate entry !");
			}
			else
			{
				throw new BMSException(e.getMessage() +"  please contact system admin");
			}
		}

		return rows>0 && row2 >0;
	}


	public boolean delete(String isbn)
	{
		String sql="delete from book where book_isbn=?";
		int rows=0;

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, isbn);
			rows=ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return rows>0;
	}



	public Vector<Vector<String>> getAllTitleJTable(String title) 
	{


		String sql="select book_title from book";

		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, title);
			ResultSet rs=ps.executeQuery();

			while(rs != null && rs.next())
			{
				row=new Vector<>();
				row.add(rs.getString(1));
				data.add(row);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}


}
