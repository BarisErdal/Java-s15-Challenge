package entity.concrete;

import entity.Book;

public class StudyBooks extends Book {

    private  String subject;
    private String gradeLevel;

    public StudyBooks(String bookID, String dateOfPurchase, boolean status, String edition, double price, String name, String author, String subject, String gradeLevel) {
        super(bookID, dateOfPurchase, status, edition, price, name, author);
        this.subject = subject;
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String getCategory() {
        return "Ders Kitabı";
    }



    @Override
    public void display(){

        super.display();
        System.out.printf("   Ders     : %-28s %n", subject);
        System.out.printf("    Seviye   : %-28s %n", gradeLevel);
        System.out.println(" --------- --------------------------");
    }



    public String getSubject()    { return subject; }
    public String getGradeLevel() { return gradeLevel; }
    public void setSubject(String s)    { this.subject    = s; }
    public void setGradeLevel(String g) { this.gradeLevel = g; }
}
