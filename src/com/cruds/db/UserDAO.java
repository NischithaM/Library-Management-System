package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javatest.DataBaseConnectionManager;
import javatest.User;

public class UserDAO 
{

	public boolean authenticate(User u)
	{
		String sql="select 1 from user where username=? and password=?";
		boolean found = false;

		try(Connection con=DataBaseConnectionManager.getConnection())
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,u.getUsername());
			ps.setString(2,u.getPassword());
			ResultSet rs=ps.executeQuery();
			if(rs!=null && rs.next())
			{
				found=true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return found;
	}
}
