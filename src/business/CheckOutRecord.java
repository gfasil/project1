package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord {
	
	List<CheckOutEntry> books=new ArrayList<>();
		
	CheckOutRecord(LibraryMember m,BookCopy b){
	int duedate=	b.getBook().getMaxCheckoutLength();
		books.add(new CheckOutEntry(b,LocalDate.now(),LocalDate.now().plusDays(duedate)));
		m.setCr(this);
		
		
	}

	@Override
	public String toString() {
		return "CheckOutRecord [books=" + books + "]";
	}
	
}
