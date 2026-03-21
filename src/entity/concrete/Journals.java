package entity.concrete;

import entity.Book;

public class Journals extends Book {

    private String issueNumber;
    private String publisher;


    public Journals(String bookID, String author, String name, double price,
                    String edition, String dateOfPurchase,
                    String issueNumber, String publishingHouse) {
        super(bookID, author, name, price, edition, dateOfPurchase);
        this.issueNumber     = issueNumber;
        this.publisher = publishingHouse;
    }

    @Override
    public String getCategory() {
        return "Akademik Dergi";
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("    Sayı     : %-28s %n", issueNumber);
        System.out.printf("    Yayınevi : %-28s %n", publisher);
        System.out.println(" --------------------------------------");

    }


    //getter setters


    public String getIssueNumber() {
        return issueNumber;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
