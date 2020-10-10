package com.cruds.db;

import static org.junit.Assert.*;

import org.junit.Test;

import javatest.Book;

public class TestBookDAO 
{

	@Test
	public void insert() {
		BookDAO dao=new BookDAO();
		Book b=new Book("3","malgudi days","story","1");
		assertTrue(dao.insert(b));
	}
	
	
}
