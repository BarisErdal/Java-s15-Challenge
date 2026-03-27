package entity.concrete;

import entity.Book;

public class StudyBooks extends Book {

    private  String subject;
    private String gradeLevel;

    public StudyBooks(String bookID, String author, String name, double price,
                      String edition, String dateOfPurchase,
                      String subject, String gradeLevel) {
        super(bookID, author, name, price, edition, dateOfPurchase);
        this.subject    = subject;
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


// ----------getter setters
    public String getSubject()    { return subject; }
    public String getGradeLevel() { return gradeLevel; }
    public void setSubject(String s)    { this.subject    = s; }
    public void setGradeLevel(String g) { this.gradeLevel = g; }
}
