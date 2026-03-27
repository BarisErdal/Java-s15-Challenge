package entity.concrete;

import entity.Book;

import java.util.List;

public class Student extends Reader {

    private String studentNo;
    private String department;

    public Student(String name, String memberID, String dateOfMembership,
                   String address, String phoneNo,
                   String studentNo, String department) {
        super(name, memberID, "Student", dateOfMembership, address, phoneNo);
        this.studentNo  = studentNo;
        this.department = department;
    }

    @Override
    public String whoyouare() {
        return "Öğrenci — " + department + " (" + studentNo + ")";
    }

    //getters--setters-------------------------------


    public String getStudentNo() {
        return studentNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
