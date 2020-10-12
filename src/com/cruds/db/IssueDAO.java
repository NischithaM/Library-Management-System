package com.cruds.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.cruds.util.DateUtil;

import javatest.BMSException;
import javatest.Book;
import javatest.DataBaseConnectionManager;
import javatest.Issue;
import javatest.Student;



public class IssueDAO {

	public int create(Issue issue)
	{
		String sql="insert into issue(usn,issue_date,return_date,book_isbn) values(?,?,?,?)";
		int issue_id=0;
		
		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, issue.getUsn());
			ps.setDate(2, DateUtil.getSQLDate(issue.getIssue_date()));
			ps.setDate(3,DateUtil.getSQLDate(issue.getReturn_date()));
			ps.setString(4, issue.getIsbn());
			ps.executeUpdate();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rs!= null && rs.next())
			{
				issue_id=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return issue_id;
	}

	public Issue getIssue(int id)
	{
		String sql="select issue_date,return_date from issue where issue_id=?";
		Issue issue=null;
		
		try(Connection conn=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs != null && rs.next())//while for more than one
			{
				issue=new Issue(id, rs.getDate(1),rs.getDate(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return issue;
	}


	public Vector<Vector<String>> getListIssueBookForJTable(String usn) //obtaining data from student database.
	{
		
		usn = "%" + usn + "%";
		String sql="select usn , book_isbn ,issue_date, return_date from issue where usn like ?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,usn);
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

	public int updateBookCount(String isbn)
	{
		int status=0;
		int count=0,issued=0;
		String sql="select no_of_books from book where book_isbn=?";
		String sql1="update book set no_of_books=? where book_isbn=?";
		

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				count=rs.getInt("no_of_books");
				//issued=rs.getInt("issue_id");
			}

			if(count > 0)
			{
				PreparedStatement ps1=con.prepareStatement(sql1);
				ps1.setInt(1, count-1);
				//ps1.setInt(2,issued+1);
				ps1.setString(2,isbn);

				status=ps1.executeUpdate();
			}
			else
			{
				System.out.println("book unavailable");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	}
	
	public Vector<Vector<String>> getAllBooksTitleJTable(String book_title) //obtaining data from book database.
	{
		int count;
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


	public Vector<Vector<String>> getAllStudentsForJTable(String usn) 
	{

		usn = "%" + usn + "%";
		String sql="select usn,name from Student where usn like ?";
		Vector<String> row=new Vector<>();
		Vector<Vector<String>> data=new Vector<>();
		
		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, usn);
			ResultSet rs=ps.executeQuery();
			
			while(rs != null && rs.next())
			{
				
				row=new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				
				data.add(row);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return data;
	}

}

