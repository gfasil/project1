package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
final public class Book implements Serializable {
	
	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
	private static final long serialVersionUID = 6110690276685962829L;
	private List<BookCopy> copies=addcopy1();
	private List<Author> authors= new ArrayList<>();
	private String isbn;
	private String title;
	private int maxCheckoutLength;
	public List<BookCopy> addcopy1() {
		List<BookCopy> temp=new ArrayList<>();
		 temp.add(new BookCopy(this, 1, true));
		 return temp;
		 
	}
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
	//	copies = new ArrayList<>();
		copies.add(new BookCopy(this, 1, true));
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public void updateCopies(BookCopy copy) {
		
	}

	
	public List<Integer> getCopyNums() {
		
		return null;
		
	}
	
	public void addCopy() {
		
		copies.add(new BookCopy(this, copies.size()+1, true));
	}
	
	
	/*@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		Book b = (Book)ob;
		return b.isbn.equals(isbn);
	}*/
	
	
	public boolean isAvailable() {
		return false;
	}
	@Override
	public String toString() {
		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
	}
	
	public int getNumCopies() {
	//	System.out.println("The number of elements is : " + copies == null ? 0 :+copies.size());
		return 1;
	}
	
	public String getTitle() {
		return title;
	}
	public List<BookCopy> getCopies() {
		return copies;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
public String getAuthors1() {
		
		String str = "";
		for(Author i: authors) {
			str += i.getFirstName() + i.getLastName() + ", ";
		}
		return str;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public BookCopy getNextAvailableCopy() {	
		return this.getCopies().get(this.copies.size()-1);
	}
	
	public BookCopy getCopy(int copyNum) {
		
		return null;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	
	
}
