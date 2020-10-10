package com.cruds.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javatest.DataBaseConnectionManager;
import javatest.Issue;

public class IssuebookDAO {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return issue;
	}

	
	public int create(Issue issue)
	{
		String sql="insert into issue(issue_date,return_date) values(?,?)";
		int issue_id=0;
		
		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, DateUtil.getSQLDate(issue.getIssue_date()));
			ps.setDate(2,DateUtil.getSQLDate(issue.getReturn_date()));
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
}
