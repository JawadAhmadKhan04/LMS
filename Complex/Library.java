package Library;

import java.io.IOException;
import java.util.*;
public class Library {
    private PersistanceHandler persistanceHandler;
    private ArrayList<Book> books; // Total books in the library
    private ArrayList<User> users; // Total Users of the library
    private double Late_Fee;

    public Library(double l_f, int choice) {
        books = new ArrayList<>();
        users = new ArrayList<>();
        this.Late_Fee = l_f;
        if (choice == 0 ){
            this.persistanceHandler = new FileSystem();
        }
        else {
            this.persistanceHandler = new MySqlSystem();
        }

        // this.persistanceHandler = p;
    }

    public void LoanExtension(String userIdForExtension, String bookIDForExtension, int extensionDays) {
        this.persistanceHandler.LoanExtension(userIdForExtension, bookIDForExtension, extensionDays);

    }

    public void view_loaned_books(String u) {
        this.persistanceHandler.Loaned_Book_Detail(u);
    }

    public void find_user_name(String finder){
        this.persistanceHandler.find_user_name(finder);
        return;
    }

    public void find_user_id(String finder){
        this.persistanceHandler.find_user_id(finder);
        return;
    }

    public void find_book_id(String finder){
        this.persistanceHandler.find_book_id(finder);
        return;
    }

    public void find_book_name(String finder){
        this.persistanceHandler.find_book_name(finder);
        return;
    }

    public void find_author(String finder){
        this.persistanceHandler.find_author(finder);
        return;
    }
    
    
    

    public int book_id_start(){
        return this.persistanceHandler.book_id_start();
    }

    public int user_id_start(){
        return this.persistanceHandler.user_id_start();
    }

    // Adding a book to the library
    public void Add_Book(Book book){
        try {
            this.persistanceHandler.saveBooks(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // books.add(book);
        System.out.println("Book added to the Library " + book.Get_Title());
    }

    // Removing a book from the library
    public void Remove_book(String bk_id) {
        // Book book = Find_Book_By_ID(bk_id);
        // if (book != null && book.Is_Loaned() == false) {
        //     books.remove(book);
        //     System.out.println("Book with title " + book.Get_Title() + " has been removed.");
        // }
        // else {
        //     System.out.println("Book " + book.Get_Title() + " is not in the Library, or has been loaned out.");
        // }
        this.persistanceHandler.RemoveBook(bk_id);
    }

    // Displaying all the available books as well as the books which are not available
    public void Display_Available_Books() {
        // System.out.println("All Present available Books are: ");
        // for (int i = 0; i < books.size(); i++) {
        //     if (books.get(i).Is_Loaned() == false) {
        //         books.get(i).Display_Book_Details();
        //         System.out.println("-------------------------------------------------");
        //     }
        // }
        // System.out.println("The Following books cannot be loaned out.");
        // for (int i = 0; i < books.size(); i++) {
        //     if (books.get(i).Is_Loaned() == true) {
        //         books.get(i).Display_Book_Details();
        //         System.out.println("-------------------------------------------------");
        //     }
        // }
        this.persistanceHandler.displayAvailableBooks();
    }

    // Adding a new user in the library
    public void Add_User(User user){
        // users.add(user);
        this.persistanceHandler.AddUser(user);
        System.out.println("User " + user.Get_User_Name() + " has been added.");
    }


    // Displaying an alreasdy present user
    public void Display_Users() {
        System.out.println("All Registered Users in the System are: ");
        // for (int i = 0; i < this.users.size(); i++) {
        //     users.get(i).Display_User_Details();
        //     System.out.println("-----------------------------------------------");
        // }
        this.persistanceHandler.DisplayUsers();
    }

    // Loaning a book to the user
    public void Loan_Book(String book_id, String user_id, int duration) {
        this.persistanceHandler.LoanBook(user_id, book_id, duration);
        // Book book = Find_Book_By_ID(book_id); // seeing if book is present in the library
        // User user = Find_User_By_ID(user_id); // seeing if user is present in the library
        // if (book != null && user != null && book.Is_Loaned() == false) { // checking if the book can be loaned out or not
        //     if (user.Loan_Book(book, duration)) {
        //         book.Set_Loan(true);
        //         System.out.println("Book " + book.Get_Title() + " loaned to " + user.Get_User_Name());
        //     }
        //     else {
        //         System.out.println(user.Get_User_Name() + " loan capacity has exceeded.");
        //     }
        // }
        // else {
        //     System.out.println("Error: Book/user not Found/Available");
        // }
    }

    // Returning a book back to the library
    public void Return_Book(String book_id, String user_id, int overdue) {
        this.persistanceHandler.Return_Book(user_id, book_id, overdue);
        // Book book = Find_Book_By_ID(book_id); // seeing if book is present in the library
        // User user = Find_User_By_ID(user_id); // seeing if book is present in the library
        // if (book != null && user != null && user.Return_Book(book, overdue)) {
        //     book.Set_Loan(false);
        //     double fine = overdue * Late_Fee;
        //     System.out.println("Book " + book.Get_Title() + " returned by " + user.Get_User_Name());
        //     if (fine > 0) {
        //         System.out.println("Late Fee: $" + fine);
        //     }
        // }
        // else {
        //     System.out.println("Error. Book/User not Found");
        // }
    }

    // Displaying all the books loaned out to a user
    public void Display_User_Loaned_Books(String user_id) {
        User user = Find_User_By_ID(user_id);
        if (user != null) {
            user.Loaned_Books();
        }
        else {
            System.out.println("User not Found.");
        }
    }

    // Private Methods
    private Book Find_Book_By_ID(String bk_id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).Get_Book_ID().equals(bk_id)) {
                return books.get(i);
            }
        }
        return null;   
    }

    private User Find_User_By_ID(String user_id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).Get_User_ID().equals(user_id)) {
                return users.get(i);
            }
        }
        return null;   
    }


}
