package entity.concrete;

import entity.Book;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {

    private List<Book> books= new ArrayList<>();
    private member_Record memberRecord;

    public Reader(String name, String memberID, String type,
                  String dateOfMembership, String address, String phoneNo) {
        super(name);
        this.books        = new ArrayList<>();
        // Composition
        this.memberRecord = new member_Record(memberID, type, dateOfMembership,
                name, address, phoneNo);
    }

    @Override
    public String whoyouare() {
        return "Reader - "+ memberRecord.getType();
    }

    public boolean purchaseBook(Book book) {
        if (!memberRecord.canBorrow()) {
            System.out.println( getName() + " 5 kitap limitine ulaştı!");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println( book.getTitle() + "' şu anda başkasında.");
            return false;
        }
        book.lend();
        book.changeOwner(memberRecord.getMemberID());
        books.add(book);
        memberRecord.incBookIssued(book.getBookID(), book.getPrice());
        return true;
    }
    public boolean borrowBook(Book book) {
        return purchaseBook(book);
    }

    public boolean returnBook(Book book) {
        if (!memberRecord.hasBorrowed(book.getBookID())) {
            System.out.println(" Bu kitap bu üyede kayıtlı değil.");
            return false;
        }
        book.returnItem();
        books.removeIf(b -> b.getBookID().equals(book.getBookID()));
        memberRecord.decBookIssued(book.getBookID(), book.getPrice());
        return true;
    }

    public void showBook() {
        if (books.isEmpty()) {
            System.out.println("Üyede kayıtlı ödünç kitap yok.");
            return;
        }
        System.out.println( getName() + " adlı üyenin ödünç kitapları :");
        books.forEach(b -> System.out.println("     " + b));
    }

    // Getters-------------------
    public member_Record getMemberRecord() { return memberRecord; }
    public List<Book>    getBooks()        { return new ArrayList<>(books); }
}
