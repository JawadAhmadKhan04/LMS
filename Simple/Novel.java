package Library;
public class Novel extends Book {
    private static boolean Extendable = false;
    private double Flat_Rate;

    public Novel(String bookId, String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        super(bookId, title, author, isbn, publicationYear, genre, baseLoanFee, false);
    }

    // Calculating the loan cost
    public double Calculate_Loan_Fee(int duration) {
        return Flat_Rate;
    }

	public void Loan_Book(User user) {
	}

	public void Return_Book(User user) {
	}

	public static boolean Is_Extendable() {
		return Extendable;
	}

}

