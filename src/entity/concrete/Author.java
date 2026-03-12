
package entity.concrete;

import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

private List<String> bookIDs=new ArrayList<>();

    public Author(String name, List<String> bookIDs) {
        super(name);
        this.bookIDs = bookIDs;
    }

    public List<String> getBookIDs() {
        return bookIDs;
    }

    public void newBook(String bookID) {
        bookIDs.add(bookID);
        System.out.println("  ► Author'a kitap eklendi: " + bookID);
    }

    public void showBook() {
        System.out.println("  ****** " + getName() + " — Kitapları: " + bookIDs);
    }

public void removeBook(String bookID){bookIDs.remove(bookID);}
    @Override
    public String whoyouare() { return "Author"; }
}

