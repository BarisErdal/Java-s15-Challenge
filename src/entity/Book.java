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

    public Book(String bookID, String author, String name, double price,
                String edition, String dateOfPurchase) {
        this.bookID         = bookID;
        this.author         = author;
        this.name           = name;
        this.price          = price;
        this.status         = true;     // yeni kitap rafta
        this.edition        = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.currentOwner   = null;
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

    public void lend() {
        this.status = false;
    }


    public void returnItem() {
        this.status       = true;
        this.currentOwner = null;
    }
    public abstract String getCategory();

    public double getPrice()        { return price; }

    @Override
    public void updateStatus(boolean status){this.status= status;}
    @Override
    public boolean isAvailable()    { return status; }


    @Override
    public void display() {

        String statusText = status ? "Kütüphanede (Rafta)" : "Ödünç Verildi → " + currentOwner;

        System.out.println("\n---  KİTAp Display ---");
        System.out.println("🏷  Kategori : " + getCategory().toUpperCase());
        System.out.println("  Barkod   : #" + bookID);
        System.out.println("  Eser Adı : " + name);
        System.out.println(" Yazar    : " + author);
        System.out.println("  Fiyat    : " + String.format("%.2f TL", price));
        System.out.println("  Baskı    : " + edition + ". Baskı");
        System.out.println(" Alınma   : " + dateOfPurchase);
        System.out.println( " Durum    : " + statusText);
        System.out.println("----------------------------");
    }





    // Getters , Setters
    public String  getBookID()          { return bookID; }
    public String  getName()            { return name; }
    public String  getEdition()         { return edition; }
    public String  getDateOfPurchase()  { return dateOfPurchase; }

    public void setAuthor(String a)     { this.author = a; }
    public void setName(String n)       { this.name   = n; }
    public void setPrice(double p)      { this.price  = p; }
    public void setEdition(String e)    { this.edition = e; }
    public void setDateOfPurchase(String d) { this.dateOfPurchase = d; }

    @Override
    public String toString() {
        return String.format("Book [ID: %s, İsim: %s, Durum: %s]",
                bookID, name, status ? "Rafta" : "Ödünç verilmiş");
    }
}
