package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javatest.BMSException;
import javatest.DataBaseConnectionManager;
import javatest.Student;

public class StudentDAO {
	
		
		public boolean insert(Student s)
		{
		String sql="insert into student(usn,name) values(?,?)";
		
		int rows=0;
		
		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,s.getUsn());
			ps.setString(2,s.getName());
			
			rows=ps.executeUpdate();
			
		 }
			catch(SQLException e)
		{
				e.printStackTrace();
				
				if(e.getMessage().contains("duplicate"))
				{
					throw new BMSException(s.getUsn() +"already exits ! duplicate entry !");
				}
				else
				{
					throw new BMSException(e.getMessage() +"  please contact system admin");
				}
		}
		
		return rows>0;
	}
		
		
	public Vector<Vector<String>> getAllStudentsForJTable()
	{
		String sql="select usn,name from Student ";
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
				
				data.add(row);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return data;
	}
	
}
