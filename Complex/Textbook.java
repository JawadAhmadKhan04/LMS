package Library;

public class Textbook extends Book {
    private static boolean Extendable = true;
    private double Duration_Based_Cost;
    private double Extension_Fee;

    public Textbook(String bookId, String title, String author, String isbn, int publicationYear, String genre, double baseLoanFee) {
        super(bookId, title, author, isbn, publicationYear, genre, baseLoanFee, false, "Textbook");
    }

    // Calculating the loan cost
    public double Calculate_Loan_Fee(int duration) {
        return Base_Loan_Fee + (Duration_Based_Cost * duration);
    }

    public void Extend_Loan() {
        System.out.println(String.format("Loan has been extended for &s", super.Title));
        Duration_Based_Cost += Extension_Fee;
    }

	public void Loan_Book(User user) {
	}

	public void Return_Book(User user) {
	}

	public static boolean Is_Extendable() {
		return Extendable;
	}

	public static void Set_Extendable(boolean extendable) {
		Textbook.Extendable = extendable;
	}

    public void Display_Details() {
        System.out.println(String.format("Book ID: &s", super.BookID));
        System.out.println(String.format("Title: &s", super.Title));
        System.out.println(String.format("Author: &s", super.Author));
        System.out.println(String.format("ISBN: &s", super.ISBN));
        System.out.println(String.format("Publication Year: &i", super.Publication_Year));
        System.out.println(String.format("Genre: &s", super.Genre));
        System.out.println(String.format("Loan Status &s", Printing_Loan_Status()));
        System.out.println(String.format("Base Loan Fee: &s", super.Base_Loan_Fee));
        
    }

    private String Printing_Loan_Status(){
        if (Loan_Status == true) {
            return "Loaned";
        }
        else {
            return "Available for Loan";
        }
    }

}
