package Library;

import java.io.IOException;
public abstract class PersistanceHandler {
    // public abstract void saveBook(Book books) throws IOException;
    public abstract void saveBooks(Book books) throws IOException;
    public abstract void displayAvailableBooks();
    public abstract void RemoveBook(String idToRemove); 
    public abstract void DisplayUsers();
    public abstract void AddUser(User user);
    public abstract void LoanBook(String userID, String bookID, int duration);
    public abstract void Return_Book(String userID, String bookID, int overdue);
    public abstract int book_id_start();
    public abstract int user_id_start();
    public abstract void find_user_name(String finder);
    public abstract void find_book_name(String finder);
    public abstract void find_user_id(String finder);
    public abstract void find_book_id(String finder);
    public abstract void find_author(String finder);
    public abstract void LoanExtension(String userIdForExtension, String bookIDForExtension, int extensionDays);
    public abstract void Loaned_Book_Detail(String userIdForDetails);


}
