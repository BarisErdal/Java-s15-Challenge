package entity.concrete;

import entity.Book;

import java.util.List;

public class Student extends Reader {

    private String studentNo;
    private String department;

    public Student(String name, List<Book> books, member_Record memberRecord, String memberID, String type, String dateOfMembership, String address, String phoneNo, String studentNo, String department) {
        super(name, books, memberRecord, memberID, "Student", dateOfMembership, address, phoneNo);
        this.studentNo = studentNo;
        this.department = department;
    }

    @Override
    public String whoyouare() {
        return "Öğrenci — " + department + " (" + studentNo + ")";
    }

    //getters---------------------------------


    public String getStudentNo() {
        return studentNo;
    }

    public String getDepartment() {
        return department;
    }
}
