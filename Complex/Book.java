package Library;

public abstract class Book implements Loanable {
    protected String BookID;
    protected String Title;
    protected String Author;
    protected String ISBN;
    protected String Genre;
    protected int Publication_Year;
    protected boolean Loan_Status;
    protected double Base_Loan_Fee;
    protected String book_type;
    // PersistanceHandler pHandler;

    public Book(String bookId, String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee, boolean l_s, String b_t) {
        this.BookID = bookId;
        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Publication_Year = publicationYear;
        this.Genre = genre;
        this.Loan_Status = l_s; // initially not loaned
        this.Base_Loan_Fee = baseLoanFee;
        this.book_type = b_t;
    }

    public void Display_Book_Details(){
        System.out.println("Book ID: " + BookID);
        System.out.println("Title: " + Title);
        System.out.println("Author: " + Author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Publication Year: " + Publication_Year);
        System.out.println("Genre: " + Genre);
        System.out.println("Loan Status: " + (Loan_Status ? "Loaned" : "Available"));
        System.out.println("Base Loan Fee: " + Base_Loan_Fee);
        System.out.println("Book Type: " + book_type);
    }

    public abstract double Calculate_Loan_Fee(int duration);

    public boolean Is_Loanable() {
        return !Loan_Status;
    }

    public void Set_Loan_Status(boolean l_s){
        this.Loan_Status = l_s;
    }

    public String Get_Title() {
		return Title;
	}

	public boolean Is_Loaned() {
		return Loan_Status;
	}

	public Object Get_Book_ID() {
		return BookID;
	}

	public void Set_Loan(boolean loan) {
		Loan_Status = loan;		
	}

}
