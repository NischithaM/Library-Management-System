package javatest;

public class Book
{

	private String book_isbn;
	private String book_title;
	private String book_category;
	private String no_of_books;
	
	private Author author;
	
	private Student student;
	private Issue issue;
	
	
	public Book(String book_isbn) {
		super();
		this.book_isbn = book_isbn;
	}

	public Book(String isbn, String book_title, String book_category, String count) {
		super();
		this.book_isbn = isbn;
		this.book_title = book_title;
		this.book_category = book_category;
		this.no_of_books = count;
	}

	public Book(String book_isbn, String book_title, String book_category, String count, Author author) {
		super();
		
		this.book_isbn = book_isbn;
		this.book_title = book_title;
		this.book_category = book_category;
		this.no_of_books=count;
		this.author = author;
		
	}
	public Book(String book_isbn, Student student, Issue issue) {
		super();
		this.book_isbn = book_isbn;
		/*this.book_title = book_title;
		this.author = author;*/
		this.student = student;
		this.issue = issue;
	}

	public String getBook_isbn() {
		return book_isbn;
	}


	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}


	public String getBook_title() {
		return book_title;
	}


	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}


	public String getBook_category() {
		return book_category;
	}


	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}


	public String getNo_of_books() {
		return no_of_books;
	}


	public void setNo_of_books(String no_of_books) {
		this.no_of_books = no_of_books;
	}

	
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "Book [book_isbn=" + book_isbn + ", book_title=" + book_title + ", book_category=" + book_category
				+ ", no_of_books=" + no_of_books + ", author=" + author + ", student=" + student + ", issue=" + issue
				+ "]";
	}
	
	
	
}
