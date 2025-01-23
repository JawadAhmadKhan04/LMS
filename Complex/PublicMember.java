package Library;


public class PublicMember extends User{
    private static final int MAX_LOANED_BOOKS = 3;
    private float Higher_Base_Fee;

    public PublicMember(String user_id, String name, String email, String phone, String address, float fee) {
        super(user_id, name, email, phone, address, "Public Member");
        this.Higher_Base_Fee = fee;
    }

    @Override
    public int Get_Max_Loaned_Book(){
        return MAX_LOANED_BOOKS;
    }

    @Override
    public boolean Loan_Book(Book book, int duration){
        if (Loaned_Books.size() < Get_Max_Loaned_Book() && book.Is_Loanable()) {
            Loaned_Books.add(book);
            book.Set_Loan_Status(true);
            Loan_Fees = Loan_Fees + ((book.Calculate_Loan_Fee(duration) + (Higher_Base_Fee * duration)));
            System.out.println(String.format("You have been loaned %d books", Loaned_Books.size()));
            return true;
        }
        System.out.println(String.format("You have already loaned the max number of books, i.e. %d", Get_Max_Loaned_Book()));
        return false;
    }

    @Override
    public boolean Return_Book(Book book, int overdue){
        if (Loaned_Books.contains(book)){
            Loaned_Books.remove(book);
            Loan_Fees = Loan_Fees + ((book.Calculate_Loan_Fee(overdue) + (Higher_Base_Fee * overdue)));
            book.Set_Loan_Status(false);
            System.out.println(String.format("Book: %s has been Returned", book.Title));
            return true;
        }
        else {
            System.out.println(String.format("Book: %s not found in the Loaned Items", book.Title));
            return false;
        }
    }
}
