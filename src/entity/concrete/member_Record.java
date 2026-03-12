package entity.concrete;

import java.util.HashSet;
import java.util.Set;

public class member_Record {
    private static final int MAX_BOOK_LIMIT = 5;
    private String memberID;
    private String type;               // Student / Faculty
    private String dateOfMembership;
    private int    noBooksIssued;
    private String name;
    private String address;
    private String phoneNo;
    private double totalBill;
    private Set<String> borrowedBookIDs;

    public member_Record(String memberID, String type, String dateOfMembership,
                         String name, String address, String phoneNo) {
        this.memberID          = memberID;
        this.type              = type;
        this.dateOfMembership  = dateOfMembership;
        this.noBooksIssued     = 0;
        this.name              = name;
        this.address           = address;
        this.phoneNo           = phoneNo;
        this.totalBill         = 0.0;
        this.borrowedBookIDs   = new HashSet<>();
    }



    public String getMember() {
        return String.format("ID:%s | %s | %s | Kitap:%d/%d | Borç:%.2f TL",
                memberID, name, type, noBooksIssued, MAX_BOOK_LIMIT, totalBill);
    }

    public boolean incBookIssued(String bookID, double price) {
        if (noBooksIssued >= MAX_BOOK_LIMIT) return false;
        borrowedBookIDs.add(bookID);
        noBooksIssued++;
        totalBill += price;
        return true;
    }

    public boolean decBookIssued(String bookID, double price) {
        if (!borrowedBookIDs.contains(bookID)) return false;
        borrowedBookIDs.remove(bookID);
        noBooksIssued--;
        totalBill = Math.max(0, totalBill - price);
        return true;
    }

    public void payBill() {
        System.out.printf("  ► Ödenen tutar: %.2f TL%n", totalBill);
        totalBill = 0.0;
    }

    public boolean canBorrow()          { return noBooksIssued < MAX_BOOK_LIMIT; }
    public boolean hasBorrowed(String id){ return borrowedBookIDs.contains(id); }


// Getters--------------------------------------------------------------------------

    public String  getMemberID()         { return memberID; }
    public String  getType()             { return type; }
    public String  getDateOfMembership() { return dateOfMembership; }
    public int     getNoBooksIssued()    { return noBooksIssued; }
    public int     getMaxBookLimit()     { return MAX_BOOK_LIMIT; }
    public String  getMemberName()       { return name; }
    public String  getAddress()          { return address; }
    public String  getPhoneNo()          { return phoneNo; }
    public double  getTotalBill()        { return totalBill; }
    public Set<String> getBorrowedBookIDs() { return new HashSet<>(borrowedBookIDs); }

//Setters------------------------------------------------------------------

    public void setAddress(String a) { this.address = a; }
    public void setPhoneNo(String p) { this.phoneNo = p; }


    @Override
    public String toString() {
        return "member_Record{" +
                "memberID='" + memberID + '\'' +
                ", type='" + type + '\'' +
                ", dateOfMembership='" + dateOfMembership + '\'' +
                ", noBooksIssued=" + noBooksIssued +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", totalBill=" + totalBill +
                ", borrowedBookIDs=" + borrowedBookIDs +
                '}';
    }
}
