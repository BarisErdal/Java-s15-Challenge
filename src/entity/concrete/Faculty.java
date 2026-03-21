package entity.concrete;

import entity.Book;

import java.util.List;

public class Faculty extends Reader {

    private String staffID;
    private String facultyName;

    public Faculty(String name, String memberID, String dateOfMembership,
                   String address, String phoneNo,
                   String staffID, String facultyName) {
        super(name, memberID, "Faculty", dateOfMembership, address, phoneNo);
        this.staffID     = staffID;
        this.facultyName = facultyName;
    }


    public String whoyouare() {
        return "Öğretim Görevlisi — " + facultyName + " (" + staffID + ")";
    }

    public String getStaffID() {
        return staffID;
    }

    public String getFacultyName() {
        return facultyName;
    }
}
