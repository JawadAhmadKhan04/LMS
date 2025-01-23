package Library;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MySqlSystem extends PersistanceHandler {

    private final String url = "jdbc:mysql://localhost:3306/task2";
    private final String user = "root";
    private final String pwd = "Jawad4693!";

    private Connection conn = null;

    MySqlSystem(){
        try {
            this.conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection established to MySQL!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int book_id_start(){
        int maxBookId = 1; // Initialize to a default value (0 or any suitable value)
        try {
            String query = "SELECT MAX(CAST(BookID AS UNSIGNED)) AS Book " + 
                           "FROM Books " + 
                           "WHERE BookID IS NOT NULL AND BookID != '';";
            
            Statement display = conn.createStatement();
            ResultSet resultSet = display.executeQuery(query);
            
            if (resultSet.next()) { // Check if there is a result
                maxBookId = resultSet.getInt("Book"); // Retrieve the maximum user ID
            }
            return maxBookId+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxBookId; // Return the maximum user ID
    }

    @Override
public void find_user_name(String finder) {
    String searchsql = "SELECT * FROM Users WHERE Name = ? ORDER BY UserID ASC"; // Use the correct table name
    boolean found = false;
    try (PreparedStatement preparedStatement = conn.prepareStatement(searchsql)) {
        preparedStatement.setString(1, finder); // Set the parameter

        try (ResultSet resultSet = preparedStatement.executeQuery()) { // Execute the prepared statement and obtain the ResultSet

            // Check if there is a result
            while (resultSet.next()) { // Iterate over each row of the result set
                // Retrieve user details
                found = true;
                String userID1 = resultSet.getString("UserID");
                String name1 = resultSet.getString("Name");
                String email1 = resultSet.getString("Email");
                String phoneNumber1 = resultSet.getString("PhoneNumber");
                String address1 = resultSet.getString("Address");
                String userType1 = resultSet.getString("Subscription");

                // Display user details
                System.out.println("User Details:");
                System.out.println("User ID: " + userID1);
                System.out.println("Name: " + name1);
                System.out.println("Email: " + email1);
                System.out.println("Phone Number: " + phoneNumber1);
                System.out.println("Address: " + address1);
                System.out.println("User Type: " + userType1);
                System.out.println("---------------------------");
            }
        }
    } catch (SQLException e) {
        // Handle SQL exception
        e.printStackTrace();
        System.out.println("An error occurred while retrieving user details.");
    }
    if( found == false) {
        System.out.println("No user by name: " + finder);
    }
}

      @Override
    public void LoanExtension(String userIdForExtension, String bookIDForExtension, int extensionDays) {
     

        // SQL to check if the book is loaned by the user and get the loan end date and
        // book type
        String loanedBookQuery = "SELECT lb.ReturnDate, b.BookType " +
                "FROM Loans lb " +
                "JOIN Books b ON lb.BookID = b.BookID " +
                "WHERE lb.UserID = ? AND lb.BookID = ?";

        try (PreparedStatement loanedBookStatement = conn.prepareStatement(loanedBookQuery)) {
            loanedBookStatement.setString(1, userIdForExtension);
            loanedBookStatement.setString(2, bookIDForExtension);

            ResultSet resultSet = loanedBookStatement.executeQuery();

            if (resultSet.next()) {
                LocalDate loanEndDate = resultSet.getDate("ReturnDate").toLocalDate();
                String bookType = resultSet.getString("BookType");

                // Check if the book is a TextBook and can be extended
                if (!"TextBook".equalsIgnoreCase(bookType)) {
                    System.out.println("Only TextBooks can be extended.");
                } else {
                    // Update the loan end date in the LoanedBooks table
                    LocalDate newLoanEndDate = loanEndDate.plusDays(extensionDays); // Extend the loan end date

                    String updateLoanEndDateQuery = "UPDATE Loans SET ReturnDate = ? WHERE UserID = ? AND BookID = ?";
                    try (PreparedStatement updateStatement = conn
                            .prepareStatement(updateLoanEndDateQuery)) {
                        updateStatement.setDate(1, java.sql.Date.valueOf(newLoanEndDate));
                        updateStatement.setString(2, userIdForExtension);
                        updateStatement.setString(3, bookIDForExtension);

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println(
                                    "Loan extended successfully. New loan end date: " + newLoanEndDate);
                        } else {
                            System.out.println("Failed to extend loan.");
                        }
                    }
                }
            } else {
                System.out.println("Book not found in user's loaned books.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while managing loan extensions.");
        }
    }

    @Override
    public void Loaned_Book_Detail(String userIdForDetails) {
        Boolean userExists2 = false;
        String checkUserQuery1 = "SELECT COUNT(*) FROM Users, Books WHERE UserID = ? ORDER BY BookID ASC";
        try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery1)) {
            checkUserStatement.setString(1, userIdForDetails);
            ResultSet userResultSet = checkUserStatement.executeQuery();

            if (userResultSet.next()) {
                userExists2 = userResultSet.getInt(1) > 0; // Check if count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the user.");
        }
        if (userExists2) {

            // SQL query to retrieve loan details for the user
            String loanDetailsQuery = "SELECT lb.BookID, b.Title, lb.LoanDate, lb.ReturnDate " +
                    "FROM Loans lb " +
                    "JOIN Books b ON lb.BookID = b.BookID " +
                    "WHERE lb.UserID = ?" +
                    "ORDER BY CAST(b.BookID AS UNSIGNED) ASC";

            try (PreparedStatement loanDetailsStatement = conn.prepareStatement(loanDetailsQuery)) {
                loanDetailsStatement.setString(1, userIdForDetails);
                ResultSet resultSet = loanDetailsStatement.executeQuery();

                // Check if there are any loan records for this user
                if (resultSet.next()) {
                    // Print header
                    System.out.println("Loan Details for User ID: " + userIdForDetails);
                    System.out.println("---------------------------------------------------");



                    // Reset the cursor to the beginning of the ResultSet
                    do {
                        String bookID1 = resultSet.getString("BookID");
                        String title = resultSet.getString("Title");
                        LocalDate loanStartDate = resultSet.getDate("LoanDate").toLocalDate();
                        LocalDate loanEndDate = resultSet.getDate("ReturnDate").toLocalDate();

                        // Print loan details
                        System.out.println("BookID: " + bookID1);
                        System.out.println("Title : " + title);
                        System.out.println("Start : "+ loanStartDate);
                        System.out.println("End   : " + loanEndDate);
                        System.out.println("---------------------------------------------------");

                    } while (resultSet.next());
                } else {
                    System.out.println("No loan details found for User ID: " + userIdForDetails);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("An error occurred while retrieving loan details.");
            }
        } else {
            // If the user does not exist
            System.out.println("User not found.");
        }
    }




    @Override
    public void find_book_name(String finder){
        boolean found = false;
        String query = "SELECT * FROM Books WHERE Title = ? ORDER BY BookID ASC";
    
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, finder);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    found = true;
                    // Retrieve book details
                    String bookID1 = resultSet.getString("BookID");
                    String title1 = resultSet.getString("Title");
                    String author1 = resultSet.getString("Author");
                    String ISBN1 = resultSet.getString("ISBN");
                    String publicationYear1 = resultSet.getString("PublicationYear");
                    String genre1 = resultSet.getString("Genre");
                    double baseLoanFee1 = resultSet.getDouble("BaseLoanFee");
                    boolean loanStatus = resultSet.getBoolean("LoanStatus");
                    String booktype = resultSet.getString("BookType");
    
                    // Print the book details
                    System.out.println("Book Details:");
                    System.out.println("Book ID: " + bookID1);
                    System.out.println("Title: " + title1);
                    System.out.println("Author: " + author1);
                    System.out.println("ISBN: " + ISBN1);
                    System.out.println("Publication Year: " + publicationYear1);
                    System.out.println("Genre: " + genre1);
                    System.out.println("Loan Status: " + loanStatus);
                    System.out.println("Base Loan Fee: " + baseLoanFee1);
                    System.out.println("Book Type: " + booktype);
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("No book found with Name: " + finder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the book details.");
        }
        if (found == false){
            System.out.println("No Book with Name: " + finder);
        }
    }

    @Override
    public void find_user_id(String finder){
        String searchsql = "SELECT * FROM Users WHERE UserID = ? ORDER BY UserID ASC"; // Use the correct table name
        boolean found = false;
        try (PreparedStatement preparedStatement = conn.prepareStatement(searchsql)) {
            preparedStatement.setString(1, finder); // Set the parameter
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) { // Execute the prepared statement and obtain the ResultSet
    
                // Check if there is a result
                while (resultSet.next()) { // Iterate over each row of the result set
                    // Retrieve user details
                    found = true;
                    String userID1 = resultSet.getString("UserID");
                    String name1 = resultSet.getString("Name");
                    String email1 = resultSet.getString("Email");
                    String phoneNumber1 = resultSet.getString("PhoneNumber");
                    String address1 = resultSet.getString("Address");
                    String userType1 = resultSet.getString("Subscription");
    
                    // Display user details
                    System.out.println("User Details:");
                    System.out.println("User ID: " + userID1);
                    System.out.println("Name: " + name1);
                    System.out.println("Email: " + email1);
                    System.out.println("Phone Number: " + phoneNumber1);
                    System.out.println("Address: " + address1);
                    System.out.println("User Type: " + userType1);
                    System.out.println("---------------------------");
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
            System.out.println("An error occurred while retrieving user details.");
        }
        if( found == false) {
            System.out.println("No user by ID: " + finder);
        }
    }

    @Override
    public void find_book_id(String bookId) {
        boolean found = false;
        String query = "SELECT * FROM Books WHERE BookID = ?";
    
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, bookId);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    found = true;
                    // Retrieve book details
                    String bookID1 = resultSet.getString("BookID");
                    String title1 = resultSet.getString("Title");
                    String author1 = resultSet.getString("Author");
                    String ISBN1 = resultSet.getString("ISBN");
                    String publicationYear1 = resultSet.getString("PublicationYear");
                    String genre1 = resultSet.getString("Genre");
                    double baseLoanFee1 = resultSet.getDouble("BaseLoanFee");
                    boolean loanStatus = resultSet.getBoolean("LoanStatus");
                    String booktype = resultSet.getString("BookType");
    
                    // Print the book details
                    System.out.println("Book Details:");
                    System.out.println("Book ID: " + bookID1);
                    System.out.println("Title: " + title1);
                    System.out.println("Author: " + author1);
                    System.out.println("ISBN: " + ISBN1);
                    System.out.println("Publication Year: " + publicationYear1);
                    System.out.println("Genre: " + genre1);
                    System.out.println("Loan Status: " + loanStatus);
                    System.out.println("Base Loan Fee: " + baseLoanFee1);
                    System.out.println("Book Type: " + booktype);
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("No book found with ID: " + bookId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the book details.");
        }
        if (found == false){
            System.out.println("No Book with ID: " + bookId);
        }
    }
    

    @Override
    public void find_author(String finder){
        boolean found = false;
        String query = "SELECT * FROM Books WHERE Author = ? ORDER BY BookID ASC";
    
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, finder);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    found = true;
                    // Retrieve book details
                    String bookID1 = resultSet.getString("BookID");
                    String title1 = resultSet.getString("Title");
                    String author1 = resultSet.getString("Author");
                    String ISBN1 = resultSet.getString("ISBN");
                    String publicationYear1 = resultSet.getString("PublicationYear");
                    String genre1 = resultSet.getString("Genre");
                    double baseLoanFee1 = resultSet.getDouble("BaseLoanFee");
                    boolean loanStatus = resultSet.getBoolean("LoanStatus");
                    String booktype = resultSet.getString("BookType");
    
                    // Print the book details
                    System.out.println("Book Details:");
                    System.out.println("Book ID: " + bookID1);
                    System.out.println("Title: " + title1);
                    System.out.println("Author: " + author1);
                    System.out.println("ISBN: " + ISBN1);
                    System.out.println("Publication Year: " + publicationYear1);
                    System.out.println("Genre: " + genre1);
                    System.out.println("Loan Status: " + loanStatus);
                    System.out.println("Base Loan Fee: " + baseLoanFee1);
                    System.out.println("Book Type: " + booktype);
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println("No book found with Author: " + finder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the book details.");
        }
        if (found == false){
            System.out.println("No Book with Author: " + finder);
        }
    }

    @Override
    public int user_id_start() {
        int maxUserId = 1; // Initialize to a default value (0 or any suitable value)
        try {
            String query = "SELECT MAX(CAST(UserID AS UNSIGNED)) AS MaxUserID " + 
                           "FROM Users " + 
                           "WHERE UserID IS NOT NULL AND UserID != '';";
            
            Statement display = conn.createStatement();
            ResultSet resultSet = display.executeQuery(query);
            
            if (resultSet.next()) { // Check if there is a result
                maxUserId = resultSet.getInt("MaxUserID"); // Retrieve the maximum user ID
            }
            return maxUserId+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxUserId; // Return the maximum user ID
    }
    


    @Override
    public void displayAvailableBooks() {
        try {
            String query = "SELECT * FROM Books WHERE loanStatus = 0 ORDER BY BookID ASC";

            Statement display = conn.createStatement();
            ResultSet resultSet = display.executeQuery(query);

            // Display available books
            System.out.println("Available Books:");

            while (resultSet.next()) {

                // Only display non-loaned books

                String bookID1 = resultSet.getString("BookID");
                String title1 = resultSet.getString("Title");
                String author1 = resultSet.getString("Author");
                String ISBN1 = resultSet.getString("ISBN");
                String publicationYear1 = resultSet.getString("PublicationYear");
                String genre1 = resultSet.getString("Genre");
                // String booktyp1 = resultSet.getString("BookType");
                double baseLoanFee1 = resultSet.getDouble("BaseLoanFee");
                boolean loanStatus = resultSet.getBoolean("LoanStatus");
                String booktype = resultSet.getString("BookType");

                // Print the book details
                System.out.println("Book ID: " + bookID1);
                System.out.println("Title: " + title1);
                System.out.println("Author: " + author1);
                System.out.println("ISBN: " + ISBN1);
                System.out.println("Publication Year: " + publicationYear1);
                System.out.println("Genre: " + genre1);
                System.out.println("Loan Status: " + loanStatus);
                System.out.println("Base Loan Fee: " + baseLoanFee1);
                System.out.println("Book type: " + booktype);

                System.out.println("-----------------------------------");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveBooks(Book books) throws IOException {
            String sql = "INSERT INTO Books (BookID, Title, Author, ISBN, PublicationYear, Genre, LoanStatus, BaseLoanFee, BookType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
    
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, books.BookID);
                preparedStatement.setString(2, books.Title);
                preparedStatement.setString(3, books.Author);
                preparedStatement.setString(4, books.ISBN);
                preparedStatement.setInt(5, books.Publication_Year);
                preparedStatement.setString(6, books.Genre);
                preparedStatement.setBoolean(7, books.Loan_Status);
                preparedStatement.setDouble(8, books.Base_Loan_Fee);
                preparedStatement.setString(9, books.book_type);
                
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Book added successfully.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
        }
    
    

        @Override
        public void RemoveBook(String idToRemove) {
            // SQL queries
            String checkLoanStatusQuery = "SELECT LoanStatus FROM Books WHERE BookID = ?";
            String deleteFromBookQuery = "DELETE FROM Books WHERE BookID = ?";
    
            try (PreparedStatement checkLoanStatusStatement = conn.prepareStatement(checkLoanStatusQuery)) {
                // Step 1: Check loan status from the Book table
                checkLoanStatusStatement.setString(1, idToRemove);
                ResultSet loanStatusResult = checkLoanStatusStatement.executeQuery();
    
                if (loanStatusResult.next()) {
                    boolean loanStatus = loanStatusResult.getBoolean("LoanStatus");
    
                    // Proceed only if the book is not on loan
                    if (!loanStatus) {
    
                        // Step 4: Delete from the main Book table
                        try (PreparedStatement deleteBookStatement = conn
                                .prepareStatement(deleteFromBookQuery)) {
                            deleteBookStatement.setString(1, idToRemove);
                            int rowsAffected = deleteBookStatement.executeUpdate();
    
                            if (rowsAffected > 0) {
                                System.out.println("Book with ID " + idToRemove
                                        + " was deleted successfully from the Book table.");
                            } else {
                                System.out.println("No book found with ID " + idToRemove + " in the Book table.");
                            }
                        }
                    } else {
                        System.out.println("Book ID " + idToRemove
                                + "cannot be deleted its loaned.");
                    }
    
                } else {
                    System.out.println("No book found with ID " + idToRemove + ".");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void DisplayUsers() {
            String searchsql = "SELECT * FROM Users ORDER BY UserID ASC"; // Note: Use "User" as the table name

            try (PreparedStatement preparedStatement = conn.prepareStatement(searchsql)) {
                ResultSet resultSet = preparedStatement.executeQuery(); // Execute the prepared statement

                // Check if there is a result
                while (resultSet.next()) { // Iterate over each row of the result set
                    // Retrieve user details
                    String userID1 = resultSet.getString("UserID");
                    String name1 = resultSet.getString("Name");
                    String email1 = resultSet.getString("Email");
                    String phoneNumber1 = resultSet.getString("PhoneNumber");
                    String address1 = resultSet.getString("Address");
                    String userType1 = resultSet.getString("Subscription");

                    // Display user details
                    System.out.println("User Details:");
                    System.out.println("User ID: " + userID1);
                    System.out.println("Name: " + name1);
                    System.out.println("Email: " + email1);
                    System.out.println("Phone Number: " + phoneNumber1);
                    System.out.println("Address: " + address1);
                    System.out.println("User Type: " + userType1);
                    System.out.println("---------------------------");
                }
            } catch (SQLException e) {
                // Handle SQL exception
                e.printStackTrace();
                System.out.println("An error occurred while retrieving user details.");
            }
        }


    @Override
    public void AddUser(User user) {
      
                String sql = "INSERT INTO Users (UserID, Name, Email, PhoneNumber, Address, Subscription, Fine) VALUES (?, ?, ?, ?, ?, ?, ?);";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, user.UserID);
                    preparedStatement.setString(2, user.Name);
                    preparedStatement.setString(3, user.Email);
                    preparedStatement.setString(4, user.Phone_Number);
                    preparedStatement.setString(5, user.Address);
                    preparedStatement.setString(6, user.subscription);
                    preparedStatement.setDouble(7, user.Loan_Fees);

                    // Execute the insert query
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User registration failed.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("User added successfully.");

    }

    @Override
    public void LoanBook(String userID, String bookID, int duration) {
        // Scanner scanner = new Scanner(System.in);
        // SQL queries to check existence of user and book
        String checkUserQuery = "SELECT COUNT(*) FROM Users WHERE UserID = ?";
        String checkBookQuery = "SELECT COUNT(*) FROM Books WHERE BookID = ? AND LoanStatus = 0 AND bookType != 'Reference Book'";

        boolean userExists = false;
        boolean bookExists = false;

        // Check if user exists
        try (PreparedStatement checkUserStatement = conn.prepareStatement(checkUserQuery)) {
            checkUserStatement.setString(1, userID);
            ResultSet userResultSet = checkUserStatement.executeQuery();

            if (userResultSet.next()) {
                userExists = userResultSet.getInt(1) > 0; // Check if count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the user.");
        }

        // Check if book exists
        try (PreparedStatement checkBookStatement = conn.prepareStatement(checkBookQuery)) {
            checkBookStatement.setString(1, bookID);
            ResultSet bookResultSet = checkBookStatement.executeQuery();

            if (bookResultSet.next()) {
                bookExists = bookResultSet.getInt(1) > 0; // Check if count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the book.");
        }

        // Proceed if both user and book exist
        if (userExists && bookExists) {

            LocalDate startDate = LocalDate.now();
            // int days = (int) getDoubleInput(scanner, "Enter the number of Days for loan: ", 1, 365);
            LocalDate endDate = startDate.plusDays(duration);

            // SQL queries to insert loan record and update book status
            String insertLoanQuery = "INSERT INTO Loans (UserID, BookID, LoanDate, ReturnDate) VALUES (?, ?, ?, ?)";
            String updateBookQuery = "UPDATE Books SET LoanStatus = 1 WHERE BookID = ?"; 

            try (PreparedStatement insertLoanStatement = conn.prepareStatement(insertLoanQuery);
                    PreparedStatement updateBookStatement = conn.prepareStatement(updateBookQuery)) {

                // Insert loan record
                insertLoanStatement.setString(1, userID);
                insertLoanStatement.setString(2, bookID);
                insertLoanStatement.setDate(3, java.sql.Date.valueOf(startDate)); 
                insertLoanStatement.setDate(4, java.sql.Date.valueOf(endDate)); 
                insertLoanStatement.executeUpdate();

                // Update book status
                updateBookStatement.setString(1, bookID);
                updateBookStatement.executeUpdate();

                // Add the book to the user's loaned books

                System.out.println("Book loaned successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("An error occurred while loaning the book.");
            }

        } else {
            if (!userExists) {
                System.out.println("User not found with ID: " + userID);
            }
            if (!bookExists) {
                System.out.println("Book not found with ID: " + bookID);
            }
        }

    }

    @Override
    public void Return_Book(String userID, String bookID, int overdue) {
                       // SQL query to check loan status and return details
                       String loanCheckQuery = "SELECT lb.LoanDate, lb.ReturnDate, b.BaseLoanFee " +
                       "FROM Loans lb " +
                       "JOIN Books b ON lb.BookID = b.BookID " +
                       "WHERE lb.UserID = ? AND lb.BookID = ?";

               try (PreparedStatement loanCheckStatement = conn.prepareStatement(loanCheckQuery)) {
                   loanCheckStatement.setString(1, userID);
                   loanCheckStatement.setString(2, bookID);

                   ResultSet resultSet = loanCheckStatement.executeQuery();

                   if (resultSet.next()) {
                       LocalDate loanEndDate = resultSet.getDate("ReturnDate").toLocalDate();
                       LocalDate loanStartDate = resultSet.getDate("loanDate").toLocalDate();
                       double baseLoanFee = resultSet.getDouble("BaseLoanFee");

                       // Assume the book is returned today
                       LocalDate returnDate = LocalDate.now();

                       // Calculate loan cost based on the number of loaned days
                       long daysLoaned = ChronoUnit.DAYS.between(loanStartDate, returnDate);
                       double totalLoanCost = baseLoanFee * daysLoaned;

                       // Calculate late fee (assuming $5 per day after loan end date)
                       double lateFee = 0;
                    //    long daysLate = ChronoUnit.DAYS.between(loanEndDate, returnDate);
                       if (overdue > 0) {
                           lateFee = overdue * 5;
                       }

                       // Remove the loan entry and update book status
                       String deleteLoanQuery = "DELETE FROM Loans WHERE UserID = ? AND BookID = ?";
                       try (PreparedStatement deleteLoanStatement = conn.prepareStatement(deleteLoanQuery)) {
                           deleteLoanStatement.setString(1, userID);
                           deleteLoanStatement.setString(2, bookID);
                           deleteLoanStatement.executeUpdate();
                       }

                       String updateBookStatusQuery = "UPDATE Books SET LoanStatus = 0 WHERE BookID = ?";
                       try (PreparedStatement updateBookStatement = conn
                               .prepareStatement(updateBookStatusQuery)) {
                           updateBookStatement.setString(1, bookID);
                           updateBookStatement.executeUpdate();
                           System.out.println("Book returned successfully. Total Loan Cost: $" + totalLoanCost
                                   + ". Late Fee: $" + lateFee);
                       }
                   } else {
                       System.out.println("Book not found in user's loaned books or not currently loaned.");
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
   }

  
}
