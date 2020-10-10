package javatest;

public class Author 
{

	private String author_name;
	private String author_mailid;
	
	
	
	public Author(String author_name, String author_mailid) {
		super();
		this.author_name = author_name;
		this.author_mailid = author_mailid;
		
	}

	
	public String getAuthor_name() {
		return author_name;
	}


	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}


	public String getAuthor_mailid() {
		return author_mailid;
	}


	public void setAuthor_mailid(String author_mailid) {
		this.author_mailid = author_mailid;
	}


	@Override
	public String toString() {
		return "Author [author_name=" + author_name + ", author_mailid=" + author_mailid + "]";
	}


	
	
	
	
}
