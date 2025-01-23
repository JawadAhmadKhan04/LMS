package Library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class FileSystem extends PersistanceHandler {
    // public static final String books_file = "books.txt";
    // public static final String users_file = "users.txt";
    private final File booksFile;
    private final File usersFile;
    public FileSystem() {
        this.booksFile = new File("Books_data.csv");
        this.usersFile = new File("Users_data.csv");
        try {
            if (!usersFile.exists()) {
                usersFile.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try {
            if (!booksFile.exists()) {
                booksFile.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void DisplayUsers() {
        // boolean userFound = false;

        // Reading user details from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(this.usersFile))) {
            String line;

            // Reading the file line by line
            while ((line = reader.readLine()) != null) {
                // Splitting the line to extract user details
                String[] userDetails = line.split(",");

                // Check if the details are correctly formatted (6 fields expected)
                if (userDetails.length == 7) {
                    String storedUserID = userDetails[0];

                    // Check if the current line's userID matches the provided userID
                    // if (storedUserID.equals(userID)) {
                        String name = userDetails[1];
                        String email = userDetails[2];
                        String phoneNumber = userDetails[3];
                        String address = userDetails[4];
                        String userType = userDetails[5];
                        String fine = userDetails[6];
                        // Displaying the user details in a readable format
                        System.out.printf(
                                "User ID: %s\nName: %s\nEmail: %s\nPhone Number: %s\nAddress: %s\nUser Type: %s\nFine to be paid: %s\n\n",
                                storedUserID, name, email, phoneNumber, address, userType, fine);

                        // Mark user as found and break out of the loop
                        // userFound = true;
                        // break;
                    // }
                } else {
                    System.out.println("Invalid data format found in the file.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
            e.printStackTrace();
        }

        // If the user was not found
        // if (!userFound) {
        //     System.out.println("User ID not found.");
        // }
    }

    @Override
    public void find_user_name(String finder){
        if (!usersFile.exists()) {
            System.out.println("No User with the name: " + finder);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.usersFile))) {
            String line;
            boolean found = false;
            // Reading the file line by line
            while ((line = reader.readLine()) != null) {
                // Splitting the line to extract user details
                String[] userDetails = line.split(",");

                // Check if the details are correctly formatted (6 fields expected)
                if (userDetails.length == 7) {
                    String storedUserID = userDetails[0];

                    // Check if the current line's userID matches the provided userID
                    // if (storedUserID.equals(userID)) {
                        String name = userDetails[1];
                        String email = userDetails[2];
                        String phoneNumber = userDetails[3];
                        String address = userDetails[4];
                        String userType = userDetails[5];
                        String fine = userDetails[6];
                        // Displaying the user details in a readable format

                        if (finder.trim().equals( name.trim())) {
                            found = true;
                            System.out.printf(
                                "User ID: %s\nName: %s\nEmail: %s\nPhone Number: %s\nAddress: %s\nUser Type: %s\nFine to be paid: %s\n\n",
                                storedUserID, name, email, phoneNumber, address, userType, fine);
                        }
                        // Mark user as found and break out of the loop
                        // userFound = true;
                        // break;
                    // }
                } else {
                    System.out.println("Invalid data format found in the file.");
                }
            }
            if (found == false){
                System.out.println("No User with the name: " + finder);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
            e.printStackTrace();
        }
         
    }

    @Override
    public void find_user_id(String finder){
        if (!usersFile.exists()) {
            System.out.println("No User with the ID: " + finder);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.usersFile))) {
            String line;
            boolean found = false;
            // Reading the file line by line
            while ((line = reader.readLine()) != null) {
                // Splitting the line to extract user details
                String[] userDetails = line.split(",");

                // Check if the details are correctly formatted (6 fields expected)
                if (userDetails.length == 7) {
                    String storedUserID = userDetails[0];

                    // Check if the current line's userID matches the provided userID
                    // if (storedUserID.equals(userID)) {
                        String name = userDetails[1];
                        String email = userDetails[2];
                        String phoneNumber = userDetails[3];
                        String address = userDetails[4];
                        String userType = userDetails[5];
                        String fine = userDetails[6];
                        // Displaying the user details in a readable format

                        if (finder.trim().equals( storedUserID.trim())) {
                            found = true;
                            System.out.printf(
                                "User ID: %s\nName: %s\nEmail: %s\nPhone Number: %s\nAddress: %s\nUser Type: %s\nFine to be paid: %s\n\n",
                                storedUserID, name, email, phoneNumber, address, userType, fine);
                        }
                        // Mark user as found and break out of the loop
                        // userFound = true;
                        // break;
                    // }
                } else {
                    System.out.println("Invalid data format found in the file.");
                }
            }
            if (found == false){
                System.out.println("No User with the ID: " + finder);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the user file.");
            e.printStackTrace();
        }         
    }


    @Override
    public void find_book_name(String finder){
        if (!booksFile.exists()) {
            System.out.println("No Book with the Name: " + finder);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9) {
                    String bookID = details[0];
                    String title = details[1];
                    String author = details[2];
                    String ISBN = details[3];
                    String genre = details[4];
                    String bookType = details[5];
                    String publicationYear = details[6];
                    boolean loanstatus = Boolean.parseBoolean(details[7]);
                    double baseLoanFee = Double.parseDouble(details[8]);
                    // Only display non-loaned books
                    // Assuming loanStatus is derived from some other logic, in this case, skipping
                    // "reference" books
                    if (finder.trim().equals(title.trim())) {
                        found = true;
                        System.out.println("Book ID: " + bookID);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("ISBN: " + ISBN);
                        System.out.println("Publication Year: " + publicationYear);
                        System.out.println("Genre: " + genre);
                        System.out.println("Base Loan Fee: " + baseLoanFee);
                        System.out.println("Book Type: " + bookType);
                        System.out.println("Loan Status: " + loanstatus);

                        System.out.println("-----------------------------------");
                    }
                }
            }
            if (found == false) {
                System.out.println("No Book with the Name: " + finder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void find_book_id(String finder){
        if (!booksFile.exists()) {
            System.out.println("No Book with the ID: " + finder);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            boolean found = false;
            // System.out.println("FINDINGGGG" + finder);
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9) {
                    
                    String bookID = details[0];
                    String title = details[1];
                    String author = details[2];
                    String ISBN = details[3];
                    String genre = details[4];
                    String bookType = details[5];
                    String publicationYear = details[6];
                    boolean loanstatus = Boolean.parseBoolean(details[7]);
                    double baseLoanFee = Double.parseDouble(details[8]);
                    // Only display non-loaned books
                    // Assuming loanStatus is derived from some other logic, in this case, skipping
                    // "reference" books
                    // System.out.println("DONNEEE " + bookID);
                    if (finder.trim().equals( bookID.trim())) {
                        found = true;
                        System.out.println("Book ID: " + bookID);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("ISBN: " + ISBN);
                        System.out.println("Publication Year: " + publicationYear);
                        System.out.println("Genre: " + genre);
                        System.out.println("Base Loan Fee: " + baseLoanFee);
                        System.out.println("Book Type: " + bookType);
                        System.out.println("Loan Status: " + loanstatus);

                        System.out.println("-----------------------------------");
                    }
                }
            }
            if (found == false) {
                System.out.println("No Book with the ID: " + finder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void find_author(String finder){
        if (!booksFile.exists()) {
            System.out.println("No Book with the Author: " + finder);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9) {
                    String bookID = details[0];
                    String title = details[1];
                    String author = details[2];
                    String ISBN = details[3];
                    String genre = details[4];
                    String bookType = details[5];
                    String publicationYear = details[6];
                    boolean loanstatus = Boolean.parseBoolean(details[7]);
                    double baseLoanFee = Double.parseDouble(details[8]);
                    // Only display non-loaned books
                    // Assuming loanStatus is derived from some other logic, in this case, skipping
                    // "reference" books
                    if (finder.trim().equals( author.trim())) {
                        found = true;
                        System.out.println("Book ID: " + bookID);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("ISBN: " + ISBN);
                        System.out.println("Publication Year: " + publicationYear);
                        System.out.println("Genre: " + genre);
                        System.out.println("Base Loan Fee: " + baseLoanFee);
                        System.out.println("Book Type: " + bookType);
                        System.out.println("Loan Status: " + loanstatus);

                        System.out.println("-----------------------------------");
                    }
                }
            }
            if (found == false) {
                System.out.println("No Book with the Author: " + finder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int book_id_start(){
        if (!booksFile.exists()) {
            return 1;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            String bookId = "1";            
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9) {
                    bookId = details[0];                  
                }
            }
            return (Integer.parseInt(bookId))+1;
            // System.out.println("BOOKID: " + bookId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int user_id_start(){
        if (!usersFile.exists()) {
            return 1;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            String userId = "1";            
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7) {
                    userId = details[0];                  
                }
            }
            return (Integer.parseInt(userId))+1;
            // System.out.println("BOOKID: " + bookId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }


    private String bookToString(Book book) {
        return String.join(",", book.BookID, book.Title, book.Author, book.ISBN,
                           String.valueOf(book.Publication_Year), book.Genre,
                           String.valueOf(book.Loan_Status),
                           String.valueOf(book.Base_Loan_Fee), book.book_type);
    }

    private Book stringToBook(String line) {
        String[] parts = line.split(",");
        return new ReferenceBook(parts[0], parts[1], parts[2], parts[3], 
                        Integer.parseInt(parts[4]), parts[5], 
                        Double.parseDouble(parts[6]));
    }

    // @Override
    // public ArrayList<Book> loadBooks() throws IOException {
    //     ArrayList<Book> books = new ArrayList<>();
    //     File file = new File(books_file);
    //     if (!file.exists()) {
    //         return books;
    //     }
    //     try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             books.add(stringToBook(line));
    //         }
    //     }
    //     return books;
    // }

    @Override
    public void displayAvailableBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            System.out.println("Available Books:");

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 9) {
                    String bookID = details[0];
                    String title = details[1];
                    String author = details[2];
                    String ISBN = details[3];
                    String genre = details[4];
                    String bookType = details[5];
                    String publicationYear = details[6];
                    boolean loanstatus = Boolean.parseBoolean(details[7]);
                    double baseLoanFee = Double.parseDouble(details[8]);
                    // Only display non-loaned books
                    // Assuming loanStatus is derived from some other logic, in this case, skipping
                    // "reference" books
                    if (!bookType.equalsIgnoreCase("Reference Book") && !loanstatus) {
                        System.out.println("Book ID: " + bookID);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("ISBN: " + ISBN);
                        System.out.println("Publication Year: " + publicationYear);
                        System.out.println("Genre: " + genre);
                        System.out.println("Base Loan Fee: " + baseLoanFee);
                        System.out.println("Book Type: " + bookType);
                        System.out.println("Loan Status: " + loanstatus);

                        System.out.println("-----------------------------------");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    @Override
    public void saveBooks(Book books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(booksFile, true))) {
            // Writing the book details to the file with default loanStatus = false
            String bookDetails = String.format("%s,%s,%s,%s, %s,%s,%s,%s,%.2f%n",
                    books.BookID, books.Title, books.Author, books.ISBN, books.Genre, books.book_type, books.Publication_Year, books.Loan_Status, books.Base_Loan_Fee);
            writer.write(bookDetails);
            System.out.println("Book added successfully to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
public void RemoveBook(String idToRemove) {
    File tempFile = new File("tempfile.csv");
    
    BufferedReader reader = null;
    BufferedWriter writer = null;

    try {
        reader = new BufferedReader(new FileReader(booksFile));
        writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] details = line.split(",");
            if (details.length == 9) { // Assuming loanStatus is the 9th field
                String bookID = details[0];
                boolean loanStatus = Boolean.parseBoolean(details[8]);

                // Check if the book ID matches and it is not on loan
                if (bookID.equals(idToRemove)) {
                    if (loanStatus) {
                        System.out.println("Book with ID " + idToRemove + " cannot be removed as it is currently on loan.");
                        found = true;
                        break; // Stop searching if the book is on loan
                    } else {
                        found = true;
                        System.out.println("Book with ID " + idToRemove + " has been removed.");
                        continue; // Skip writing this book to the temp file
                    }
                }
            }
            writer.write(line); // Write the book details to the temp file if not removing
            writer.newLine();
        }

        if (!found) {
            System.out.println("No book found with ID " + idToRemove + ".");
        } 
            reader.close();
            writer.close();

            // Close file handles and perform replacement
            if (booksFile.delete()) { // Delete the original file before renaming
                if (tempFile.renameTo(booksFile)) {
                    System.out.println("Book removal operation completed.");
                } else {
                    System.out.println("Error renaming the temporary file.");
                }
            } else {
                System.out.println("Error deleting the original file.");
            }
        

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

private String getBookType(String bookID) {
    File booksFile = new File("Books_data.csv");
    try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split(",");
            if (details.length >= 3 && details[0].equals(bookID)) {
                return details[5]; // Assuming the third field is the book type
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null; // Return null if book not found
}

@Override
    public void LoanExtension(String userIdForExtension, String bookIDForExtension, int extensionDays) {
        
        File loanedBooksFile = new File("loanedbooks.csv");
        if (!loanedBooksFile.exists()) {
            System.out.println("Book has not been loaned out");
            return;
        }
        File tempFile = new File("tempLoanedBooks.csv");
        boolean bookFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(loanedBooksFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String userID = details[0];
                String bookID = details[1];
                LocalDate loanEndDate = LocalDate.parse(details[2]);

                // Check if this is the loan record for the given user and book
                if (userIdForExtension.equals(userID) && bookIDForExtension.equals(bookID)) {
                    bookFound = true;

                    // Retrieve the book type from the Books file
                    String bookType = getBookType(bookID); // Assumes a helper function to get the book type

                    // Check if it's a textbook and can be extended
                    if (!"TextBook".equalsIgnoreCase(bookType)) {
                        System.out.println("Only TextBooks can be extended.");
                        writer.write(line);
                        writer.newLine();
                    } else {
                  

                        // Extend the loan period
                        LocalDate newLoanEndDate = loanEndDate.plusDays(extensionDays);
                        details[3] = newLoanEndDate.toString(); // Update loan end date in the array

                        writer.write(String.join(",", details)); // Write updated line
                        writer.newLine();

                        System.out.println("Loan extended successfully. New loan end date: " + newLoanEndDate);
                    }
                } else {
                    // Write the line as is if it doesn't match the loan being extended
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!bookFound) {
                System.out.println("Book not found in user's loaned books.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while managing loan extensions.");
        }

        // Replace original file with updated temp file
        if (!loanedBooksFile.delete() || !tempFile.renameTo(loanedBooksFile)) {
            System.out.println("Error updating the loaned books file.");
        }
    }


    @Override
    public void AddUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.usersFile, true))) {
            // Writing the book details to the file with default loanStatus = false
            String userDetails = String.format("%s,%s,%s,%s,%s,%s, %.2f%n",
                    user.UserID, user.Name, user.Email, user.Phone_Number, user.Address, user.subscription, user.Loan_Fees);
            writer.write(userDetails);
            System.out.println("user added successfully to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LoanBook(String userID, String bookID, int duration) {
        File loanedBooksFile;
        File usersFile;
        loanedBooksFile = new File("loanedbooks.csv");
        usersFile = new File("Users_data.csv");
        try {
            if (!loanedBooksFile.exists()) {
                loanedBooksFile.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean userExists = false;
        boolean bookExists = false;
        boolean isLoaned = false;
        String bookType = null;

        // Check if user exists
        try (BufferedReader userReader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(userID)) {
                    userExists = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the user.");
        }

        // Check if book exists and not on loan
        try (BufferedReader bookReader = new BufferedReader(new FileReader(booksFile))) {
            String line;
            while ((line = bookReader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equals(bookID)) {
                    bookExists = true;
                    // Check loan status and book type
                    if (Boolean.parseBoolean(bookDetails[8]) || bookDetails[5].equalsIgnoreCase("Reference Book")) {
                        isLoaned = true; // The book is either on loan or a reference book
                    } else {
                        bookType = bookDetails[5]; // Get the book type for potential use
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the book.");
        }

        // Proceed if both user and book exist and the book is not loaned
        if (userExists && bookExists && !isLoaned) {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(duration);

            // Record the loan in the LoanedBooks file
            try (BufferedWriter loanWriter = new BufferedWriter(new FileWriter(loanedBooksFile, true))) {
                String loanDetails = String.format("%s,%s,%s,%s%n", userID, bookID, startDate, endDate);
                loanWriter.write(loanDetails);
                loanWriter.flush(); // Ensure the data is written to the file

                // Update book status in the books file
                updateBookStatus(bookID, true); // Set loanStatus to true

                System.out.println("Book loaned successfully.");
            } catch (IOException e) {
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
            if (isLoaned) {
                System.out.println(
                        "Book with ID " + bookID + " cannot be loaned as it is currently on loan or a reference book.");
            }
        }
    }

    
    private String getBookTitleById(String bookID) {
        // This method should search the books file and return the title based on the
        // bookID.
        try (BufferedReader bookReader = new BufferedReader(new FileReader("Books_data.csv"))) {
            String line;
            while ((line = bookReader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equals(bookID)) {
                    return bookDetails[1]; // Assuming title is the second element
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown Title"; // Default return if book not found
    }

    @Override
    public void Loaned_Book_Detail(String userIdForDetails) {
        File loanedBooksFile;
        File usersFile;
        loanedBooksFile = new File("loanedbooks.csv");
        usersFile = new File("Users_data.csv");
        try {
            if (!loanedBooksFile.exists()) {
                loanedBooksFile.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean userExists = false;

        // Check if user exists
        try (BufferedReader userReader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(userIdForDetails)) {
                    userExists = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for the user.");
        }

        if (userExists) {
            // Retrieve loan details for the user
            boolean loanFound = false;
            try (BufferedReader loanReader = new BufferedReader(new FileReader(loanedBooksFile))) {
                String line;
                System.out.println("Loan Details for User ID: " + userIdForDetails);
                System.out.println("---------------------------------------------------");

                while ((line = loanReader.readLine()) != null) {
                    String[] loanDetails = line.split(",");
                    if (loanDetails[0].equals(userIdForDetails)) {
                        loanFound = true;
                        String bookID = loanDetails[1];
                        LocalDate loanStartDate = LocalDate.parse(loanDetails[2]);
                        LocalDate loanEndDate = LocalDate.parse(loanDetails[3]);

                        // Assuming you have a method to get the book title by ID
                        String title = getBookTitleById(bookID);

                        // Print loan details
                        System.out.println("BookID: " + bookID);
                        System.out.println("Title : " + title);
                        System.out.println("Start : "+ loanStartDate);
                        System.out.println("End   : " + loanEndDate);
                        System.out.println("---------------------------------------------------");

                    }
                }

                if (!loanFound) {
                    System.out.println("No loan details found for User ID: " + userIdForDetails);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An error occurred while retrieving loan details.");
            }
        } else {
            // If the user does not exist
            System.out.println("User not found.");
        }
    }

    private void updateBookStatus(String bookID, boolean loanStatus) {
        File tempFile = new File("tempfile.csv");
        boolean found = false;

        try (BufferedReader bookReader = new BufferedReader(new FileReader(booksFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;

            while ((line = bookReader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equals(bookID)) {
                    // Update the loanStatus
                    bookDetails[8] = String.valueOf(loanStatus); // Assuming the loanStatus is the 9th field
                    found = true;
                }
                writer.write(String.join(",", bookDetails));
                writer.newLine();
            }

            if (!found) {
                System.out.println("No book found with ID " + bookID + " to update status.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating the book status.");
        }

        // Replace the original file with the updated temp file
        if (booksFile.delete()) {
            if (!tempFile.renameTo(booksFile)) {
                System.out.println("Error in renaming the temporary file to the original file.");
            }
        } else {
            System.out.println(
                    "Failed to delete the original file. Please check file permissions or if it's still in use.");
        }
    }

    private double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        double input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextDouble();
            if (input >= min && input <= max) {
                break; // Valid input
            } else {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        }
        return input;
    }


    private double getBaseLoanFee(String bookID) {
        double baseLoanFee = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFile))) {
            String line;
    
            // Read book details from the file
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 9) { // Check if there are at least 9 fields
                    if (details[0].trim().equals(bookID)) { // Assuming bookID is the first field
                        try {
                            baseLoanFee = Double.parseDouble(details[8].trim()); // Assuming baseLoanFee is the ninth field
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid base loan fee value for book ID: " + bookID);
                        }
                        break; // Exit loop once found
                    }
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the base loan fee.");
        }
        return baseLoanFee;
    }
    


    @Override
    public void Return_Book(String userID, String bookID, int overdue) {
        File loanedBooksFile = new File("loanedbooks.csv");
        if (!loanedBooksFile.exists()) {
            System.out.println("Book has not been loaned");
            return;
        }
        File tempFile = new File("tempLoanedBooks.csv");
        boolean bookFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(loanedBooksFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String loanedBookID = details[1];
                String loanedUserID = details[0];
                LocalDate loanStartDate = LocalDate.parse(details[2]);
                LocalDate loanEndDate = LocalDate.parse(details[3]);
    
                // Check if this is the loaned book for the given user
                if (loanedUserID.equals(userID) && loanedBookID.equals(bookID)) {
                    bookFound = true;
                    // Assume the book is returned today
                    LocalDate returnDate = LocalDate.now();
    
                    // Retrieve the base loan fee from the Books file
                    double baseLoanFee = getBaseLoanFee(bookID); // Assumes a helper function to get the fee
    
                    // Calculate the total loan cost based on the number of loaned days
                    long daysLoaned = ChronoUnit.DAYS.between(loanStartDate, returnDate);
                    double totalLoanCost = baseLoanFee * daysLoaned;
    
                    // Calculate late fee (assuming $5 per day after loan end date)
                    double lateFee = overdue * 5;
                    // long daysLate = ChronoUnit.DAYS.between(loanEndDate, returnDate);
                    // if (daysLate > 0) {
                    //     lateFee = daysLate * 5;
                    // }

    
                    // Output the total cost and late fee
                    System.out.println("Book returned successfully. Total Loan Cost: $" + totalLoanCost
                            + ". Late Fee: $" + lateFee);
    
                    // Skip writing this entry (removing the loan)
                } else {
                    // Write the entry back to the temp file if it's not the returned book
                    writer.write(line);
                    writer.newLine();
                }
            }
    
            if (!bookFound) {
                System.out.println("Book not found in user's loaned books or not currently loaned.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Replace the original loaned books file with the updated temp file
        if (!loanedBooksFile.delete() || !tempFile.renameTo(loanedBooksFile)) {
            System.out.println("Error updating the loaned books file.");
        }
        // Update the book's loan status in the Books.csv file
        updateBookStatus(bookID, false); // Assumes the same method to update the loan status
    }
    

}


