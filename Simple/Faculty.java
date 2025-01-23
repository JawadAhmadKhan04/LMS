package Library;

public class Faculty extends User {

    private static final int MAX_LOANED_BOOKS = 10; // Maximum book allowed to take loan

    public Faculty(String user_id, String name, String email, String phone, String address) {
        super(user_id, name, email, phone, address, "Faculty");
    }

    @Override
    public int Get_Max_Loaned_Book() {
        return MAX_LOANED_BOOKS;
    }
}
