package entity;

import entity.interfaces.Lendable;

public abstract class Book implements Lendable {


    private String bookID;
    private String author;
    private String name;
    private double price;
    private boolean status;
    private String edition;
    private String dateOfPurchase;
    private String currentOwner;

    public Book(String bookID, String dateOfPurchase, boolean status, String edition, double price, String name, String author) {
        this.bookID = bookID;
        this.currentOwner = null;
        this.dateOfPurchase = dateOfPurchase;
        this.status = true;
        this.edition = edition;
        this.price = price;
        this.name = name;
        this.author = author;
    }


    public String getTitle() {
        return name;
    }


    public String getAuthor() {
        return author;
    }


    public void changeOwner(String memberID) {
        this.currentOwner = memberID;
    }


    public String getOwner() {
        return currentOwner;
    }


}
