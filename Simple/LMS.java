package Library;
import java.util.Scanner;

public class LMS {
    public static void main(String[] args) {
        Library Lib = new Library(2);
        Scanner input_var = new Scanner(System.in);

        // ADDING RANDOM DATA
        // ---------------------------------------------------------------------------------------------------------------
        int start_book_id = 1;
        int start_user_id = 1;
        Book book1 = new Textbook(Integer.toString(start_book_id++), "Java Programming", "Author A", "123-456", 2020, "Programming", 50.0);
        Book book2 = new Novel(Integer.toString(start_book_id++), "Mystery Novel", "Author B", "789-101", 2019, "Mystery", 30.0);
        Book book3 = new Textbook(Integer.toString(start_book_id++), "Data Structures", "Author C", "111-222", 2021, "Computer Science", 55.0);
        Book book4 = new Novel(Integer.toString(start_book_id++), "Romantic Journey", "Author D", "333-444", 2018, "Romance", 25.0);
        Book book5 = new Textbook(Integer.toString(start_book_id++), "Operating Systems", "Author E", "555-666", 2019, "Computer Science", 60.0);
        Book book6 = new Novel(Integer.toString(start_book_id++), "Science Fiction Saga", "Author F", "777-888", 2020, "Science Fiction", 40.0);
        Book book7 = new Textbook(Integer.toString(start_book_id++), "Algorithms", "Author G", "999-000", 2021, "Computer Science", 65.0);
        Book book8 = new Novel(Integer.toString(start_book_id++), "Historical Drama", "Author H", "112-113", 2019, "Historical", 35.0);
        Book book9 = new Textbook(Integer.toString(start_book_id++), "Database Systems", "Author H", "114-115", 2018, "Computer Science", 70.0);
        Book book10 = new Novel(Integer.toString(start_book_id++), "Adventure Tales", "Author J", "116-117", 2020, "Adventure", 45.0);
        Book book11 = new Textbook(Integer.toString(start_book_id++), "Machine Learning", "Author K", "118-119", 2021, "Artificial Intelligence", 75.0);
        Book book12 = new Novel(Integer.toString(start_book_id++), "Thriller Chronicles", "Author L", "120-121", 2019, "Thriller", 50.0);
        Book book13 = new Textbook(Integer.toString(start_book_id++), "Computer Networks", "Author M", "122-123", 2018, "Computer Science", 80.0);
        Book book14 = new Novel(Integer.toString(start_book_id++), "Fantasy Realm", "Author N", "124-125", 2020, "Fantasy", 55.0);
        Book book15 = new Textbook(Integer.toString(start_book_id++), "Artificial Intelligence", "Author O", "126-127", 2021, "Computer Science", 85.0);
        Book book16 = new Novel(Integer.toString(start_book_id++), "Detective Stories", "Author P", "128-129", 2019, "Detective", 60.0);
        Book book17 = new Textbook(Integer.toString(start_book_id++), "Software Engineering", "Author Q", "130-131", 2018, "Engineering", 90.0);
        Book book18 = new Novel(Integer.toString(start_book_id++), "Horror Tales", "Author R", "132-133", 2020, "Horror", 65.0);
        Book book19 = new ReferenceBook(Integer.toString(start_book_id++), "Java Programming Reference Book", "Author A", "123-456", 2020, "Programming", 50.0);

        User user1 = new Student(Integer.toString(start_user_id++), "John Doe", "john@example.com", "1234567890", "123 Street");
        User user2 = new Faculty(Integer.toString(start_user_id++), "Jonathan Vera", "jonathan_vera@gmail.com", "231451232", "Sawan Road G11");
        User user3 = new PublicMember(Integer.toString(start_user_id++), "Simon Davis", "simon@hotmail.com", "128212421", "Falcon Apartment 321", 12);
        User user4 = new Student(Integer.toString(start_user_id++), "Alice Johnson", "alice@example.com", "1122334455", "456 Avenue");
        User user5 = new Faculty(Integer.toString(start_user_id++), "Dr. Emily Brown", "emily_brown@university.edu", "2233445566", "789 Boulevard");
        User user6 = new PublicMember(Integer.toString(start_user_id++), "Michael Scott", "michael_s@outlook.com", "3344556677", "Hilltop Residency 412", 6);
        User user7 = new Student(Integer.toString(start_user_id++), "David Evans", "david@example.com", "4455667788", "303 Lane");
        User user8 = new Faculty(Integer.toString(start_user_id++), "Dr. Susan Lee", "susan_lee@university.edu", "5566778899", "505 Avenue");
        User user9 = new PublicMember(Integer.toString(start_user_id++), "Karen Miller", "karen_m@yahoo.com", "6677889900", "Riverside Apartment 510", 18);
        User user10 = new Student(Integer.toString(start_user_id++), "Brian Wilson", "brian@example.com", "7788990011", "606 Boulevard");
        User user11 = new Faculty(Integer.toString(start_user_id++), "Prof. George Clark", "george_clark@university.edu", "8899001122", "707 Parkway");
        User user12 = new PublicMember(Integer.toString(start_user_id++), "Laura Harris", "laura_h@gmail.com", "9900112233", "Seaview Condominium 220", 10);
        User user13 = new Student(Integer.toString(start_user_id++), "James Taylor", "james@example.com", "0011223344", "808 Road");
        User user14 = new Faculty(Integer.toString(start_user_id++), "Dr. Rachel Adams", "rachel_adams@university.edu", "1122334455", "909 Lane");
        User user15 = new PublicMember(Integer.toString(start_user_id++), "Steven Young", "steven_y@hotmail.com", "2233445566", "Downtown Loft 101", 15);
        
        Lib.Add_Book(book1);
        Lib.Add_Book(book2);
        Lib.Add_Book(book3);
        Lib.Add_Book(book4);
        Lib.Add_Book(book5);
        Lib.Add_Book(book6);
        Lib.Add_Book(book7);
        Lib.Add_Book(book8);
        Lib.Add_Book(book9);
        Lib.Add_Book(book10);
        Lib.Add_Book(book11);
        Lib.Add_Book(book12);
        Lib.Add_Book(book13);
        Lib.Add_Book(book14);
        Lib.Add_Book(book15);
        Lib.Add_Book(book16);
        Lib.Add_Book(book17);
        Lib.Add_Book(book18);
        Lib.Add_Book(book19);

        Lib.Add_User(user1);
        Lib.Add_User(user2);
        Lib.Add_User(user3);
        Lib.Add_User(user4);
        Lib.Add_User(user5);
        Lib.Add_User(user6);
        Lib.Add_User(user7);
        Lib.Add_User(user8);
        Lib.Add_User(user9);
        Lib.Add_User(user10);
        Lib.Add_User(user11);
        Lib.Add_User(user12);
        Lib.Add_User(user13);
        Lib.Add_User(user14);
        Lib.Add_User(user15);
        // ---------------------------------------------------------------------------------------------------------------

        int choice;
        do {
            System.out.println("\n********** Library Management System Menu **********");
            System.out.println("1. Display Available Books");
            System.out.println("2. Display User Details");
            System.out.println("3. Loan a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Add a New Book");
            System.out.println("6. Add a New User");
            System.out.println("7. Exit");
            System.out.println("***************************************************\n");

            System.out.print("Enter your choice: ");
            choice = input_var.nextInt();
            input_var.nextLine(); 
            System.out.println("\n");
            switch (choice) {
                case 1:
                    Lib.Display_Available_Books();       
                    break;
                case 2:
                    Lib.Display_Users();
                    break;
                case 3:
                    System.out.println("User ID: ");
                    String User_ID = input_var.nextLine();
                    System.out.println("Book ID: ");
                    String Book_ID = input_var.nextLine();
                    System.out.println("For how many days do you want this book: ");
                    int duration = input_var.nextInt();
                    Lib.Loan_Book(Book_ID, User_ID, duration);
                    break;
                case 4:
                    System.out.println("Book ID: ");
                    Book_ID = input_var.nextLine();
                    System.out.println("User ID: ");
                    User_ID = input_var.nextLine();
                    System.out.println("Days after due date: ");
                    int late_days = input_var.nextInt();
                    input_var.nextLine();
                    Lib.Return_Book(Book_ID, User_ID, late_days);
                    break;
                
                case 5:
                    // System.out.print("Enter Book ID: ");
                    // Book_ID = input_var.nextLine();
                    System.out.print("Enter Title: ");
                    String Title = input_var.nextLine();
                    System.out.print("Enter Author: ");
                    String Author = input_var.nextLine();
                    System.out.print("Enter ISBN: ");
                    String ISBN = input_var.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int Year = input_var.nextInt();
                    input_var.nextLine(); 
                    System.out.print("Enter Genre: ");
                    String Genre = input_var.nextLine();
                    System.out.print("Enter Base Loan Fee: ");
                    double Base_Fee = input_var.nextDouble();
                    input_var.nextLine(); 

                    System.out.println("Select Book Type:");
                    System.out.println("1. Textbook");
                    System.out.println("2. Novel");
                    System.out.println("Your Choice: ");
                    int Book_Type = input_var.nextInt();
                    input_var.nextLine(); 

                    Book New_Book;
                    if (Book_Type == 1) {
                        New_Book = new Textbook(Integer.toString(start_book_id++), Title, Author, ISBN, Year, Genre, Base_Fee);
                    } else {
                        New_Book = new Novel(Integer.toString(start_book_id++), Title, Author, ISBN, Year, Genre, Base_Fee);
                    }
                    Lib.Add_Book(New_Book);
                    break;
                
                case 6:
                    // System.out.print("Enter User ID: ");
                    // User_ID = input_var.nextLine();
                    System.out.print("Enter Name: ");
                    String Name = input_var.nextLine();
                    System.out.print("Enter Email: ");
                    String Email = input_var.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String Phone = input_var.nextLine();
                    System.out.print("Enter Address: ");
                    String Address = input_var.nextLine();

                    System.out.println("Select User Type:");
                    System.out.println("1. Student");
                    System.out.println("2. Faculty");
                    System.out.println("3. Public Member");
                    System.out.println("Enter choice: ");
                    int userType = input_var.nextInt();
                    input_var.nextLine(); 

                    User New_User;
                    if (userType == 1) {
                        New_User = new Student(Integer.toString(start_user_id++), Name, Email, Phone, Address);
                    } else if (userType == 2) {
                        New_User = new Faculty(Integer.toString(start_user_id++), Name, Email, Phone, Address);
                    } else {
                        System.out.println("Enter Higher Base Fee: ");
                        float Fee = input_var.nextFloat();
                        input_var.nextLine(); 
                        New_User = new PublicMember(Integer.toString(start_user_id++), Name, Email, Phone, Address, Fee);
                    }
                    Lib.Add_User(New_User);
                    break;   
                
                case 7:
                    System.out.println("Exitting the Library System...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        input_var.close();

    }
}
