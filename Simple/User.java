package Library;

import java.util.*;

public abstract class User {
    protected String subscription;
    protected String UserID;
    protected String Name;
    protected String Email;
    protected List<Book> Loaned_Books; // keeps track of all the books loanes by a single user
    protected double Loan_Fees;
    protected String Phone_Number;
    protected String Address;

    public abstract int Get_Max_Loaned_Book();

    public User(String userID, String name, String email, String phone, String address, String sub) {
        this.UserID = userID;
        this.Name = name;
        this.Email = email;
        this.Phone_Number = phone;
        this.Address = address;
        this.Loaned_Books = new ArrayList<>();
        this.Loan_Fees = 0.0;
        this.subscription = sub;
    }


    public boolean Loan_Book(Book book, int duration){
        if (Loaned_Books.size() < Get_Max_Loaned_Book() && book.Is_Loanable()) {// Check if the number of currently loaned books is less than the maximum allowed and if the book is loanable
            Loaned_Books.add(book); // Add the book to the list of loaned books
            book.Set_Loan_Status(true); // Setting the book's loan status to true so another person doesnot issue the same book again
            Loan_Fees = Loan_Fees + (book.Calculate_Loan_Fee(duration)); // Calculate the loan fee for the duration and add it to the total loan fees, depending on the loan cost
            System.out.println(String.format("You have been loaned %d books", Loaned_Books.size())); // Printing a message indicating the number of loaned books by the user
            return true; // book successfully loaned
        }
        
        System.out.println(String.format("You have already loaned the max number of books, i.e. %d", Get_Max_Loaned_Book())); // maximum number of books have already been loaned out
        return false;
    }

    public boolean Return_Book(Book book, int overdue) {
        if (Loaned_Books.contains(book)){ // Checking if the book is in the list of loaned books
            Loaned_Books.remove(book); // Removing the book from the list of loaned books
            Loan_Fees = Loan_Fees + (book.Calculate_Loan_Fee(overdue));  // Calculate the loan fee for the overdue period and add it to the total loan fees
            book.Set_Loan_Status(false); // Set the book's loan status to false so it can be loaned again
            System.out.println(String.format("Book: '%s' has been returned", book.Title));
            return true; // Return true indicating the book was successfully returned
        }
        System.out.println(String.format("Book: '%s' not found in the loaned items", book.Title));
        // Return false indicating the book could not be returned
        return false;
    }

    public double Calculate_Loan_Fee(){
        return Loan_Fees;
    }

    public String Get_User_Name(){
        return this.Name;
    }

    public String Get_User_ID(){
        return this.UserID;
    }

    // Displaying all user details
    public void Display_User_Details(){
        System.out.println("User ID: " + UserID);
        System.out.println("Name: " + Name);
        System.out.println("Subscription Type: " + subscription);
        System.out.println("Email: " + Email);
        System.out.println("Phone Number: " + Phone_Number);
        System.out.println("Address: " + Address);
        System.out.println("Loan Fees: " + Loan_Fees);
        System.out.println("Loaned Books of the User: ");
        this.Loaned_Books();
    }

    // The books loaned out by the user
    public void Loaned_Books() {
        for (int i = 0; i < this.Loaned_Books.size(); i++) {
            Loaned_Books.get(i).Display_Book_Details();
        }
    }

}

