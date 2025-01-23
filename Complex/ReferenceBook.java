package Library;


public class ReferenceBook extends Book {
    public ReferenceBook(String bookId, String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        super(bookId, title, author, isbn, publicationYear, genre, baseLoanFee=0, false, "Reference Book");
    }

    public boolean Is_Loanable() {
        return false; // Reference books cannot be loaned
    }

    public double Calculate_Loan_Fee(int duration) {
        return 0; // No loan fee as they cannot be loaned out
    }

	
	public void Loan_Book(User user) {
		// No need to implement as the book cannot be borrowed
		
	}

	public void Return_Book(User user) {
		
		// No need to implement as the book cannot be borrowed
	}

}
