package entity.concrete;

import entity.Book;

public class Magazines extends Book {

    private String monthYear;
    private String genre;

    public Magazines(String bookID, String author, String name, double price,
                     String edition, String dateOfPurchase,
                     String monthYear, String genre) {
        super(bookID, author, name, price, edition, dateOfPurchase);
        this.monthYear = monthYear;
        this.genre     = genre;
    }

    @Override
    public String getCategory() {
        return "Magazin";
    }

    @Override
    public void display(){

        super.display();
        System.out.printf("     Ay/Yıl   : %-30s %n", monthYear);
        System.out.printf("     Tür      : %-30s %n", genre);
        System.out.println("  -----------------------------");
    }




    //gettter setters
    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
