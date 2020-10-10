package javatest;

import java.util.Date;

public class Issue 
{

	private int issue_id;
	private Date issue_date;
	private Date return_date;

	private String usn;
	private String isbn;

	public Issue(Date issue_date, Date return_date, String usn, String isbn) {
		super();
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.usn = usn;
		this.isbn = isbn;
	}

	public Issue(int issue_id, Date issue_date, Date return_date) {
		super();
		this.issue_id = issue_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
	}

	public Issue( Date issue_date, Date return_date) {
		super();
		this.issue_date = issue_date;
		this.return_date = return_date;
	}

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Issue [issue_id=" + issue_id + ", issue_date=" + issue_date + ", return_date=" + return_date + ", usn="
				+ usn + ", isbn=" + isbn + "]";
	}

}
