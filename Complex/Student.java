package Library;

public class Student extends User{
    
    private static final int MAX_LOANED_BOOKS = 5;

    public Student(String user_id, String name, String email, String phone, String address) {
        super(user_id, name, email, phone, address, "Student");
    }

    @Override
    public int Get_Max_Loaned_Book(){
        return MAX_LOANED_BOOKS;
    }

}
