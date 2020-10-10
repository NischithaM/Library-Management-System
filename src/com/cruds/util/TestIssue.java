package com.cruds.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import javatest.Issue;

public class TestIssue {

	@Test
	public void testIssue() {
		
		Issue issue=new Issue(DateUtil.getCurrentDate(),DateUtil.addToCurrentDate(7));
		/*Date date=DateUtil.getUtilDateFromString("06-10-2020");
		Date date2=DateUtil.addToCurrentDate(7);
		System.out.println(date);
		System.out.println(date2);*/
		
		IssuebookDAO dao=new IssuebookDAO();
		/*int issue_id=dao.create(issue);
		System.out.println(issue_id);
		assertTrue(issue_id>0);*/
		
		Issue issue1=dao.getIssue(6);
		System.out.println(issue1);
		assertNotNull(issue1);
		
	}

}
