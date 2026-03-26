package entity.concrete;

import entity.Book;

public class Librarian {
    private String name;
    private String password;
    private String employeeID;

    public Librarian(String name, String password, String employeeID) {
        this.name = name;
        this.password = password;
        this.employeeID = employeeID;
    }

    public boolean verifyMember(Reader reader) {
        return reader != null && reader.getMemberRecord() != null;
    }


    public boolean issueBook(Reader reader, Book book){

        if(!verifyMember(reader)){
            System.out.println("Üye doğrulanamadı!");
            return false;
        }
        boolean canReaderBorrowBook=reader.borrowBook(book);
        if (canReaderBorrowBook) {
            System.out.println(" Kütüphaneci [" + name + "] kitabı ödünç verdi.");
            createBill(reader.getMemberRecord(), book, "ÖDÜNÇ");
        }
        return canReaderBorrowBook;

    }



    public boolean returnBook(Reader reader, Book book) {
        boolean ok = reader.returnBook(book);
        if (ok) {
            System.out.println(" Kütüphaneci [" + name + "] kitabı teslim aldı.");
            createBill(reader.getMemberRecord(), book, "İADE");
        }
        return ok;
    }

    public double calculateFine(int overdueDays) {
        double finePerDay = 2.50;
        double fine = overdueDays * finePerDay;
        System.out.printf("  Gecikme cezası: %d gün × %.2f TL = %.2f TL%n",
                overdueDays, finePerDay, fine);
        return fine;
    }


    public void createBill(member_Record record, Book book, String operation) {
        System.out.println(" ------------  FATURA----------");
        System.out.printf ("  Kütüphaneci : %-20s %n", name);
        System.out.printf ("  Üye         : %-20s %n", record.getMemberName());
        System.out.printf ("  Üye ID      : %-20s %n", record.getMemberID());
        System.out.printf ("  Kitap       : %-20s %n", book.getTitle());
        System.out.printf ("  İşlem       : %-20s %n", operation);
        System.out.printf ("  Tutar       : %-20s %n", String.format("%.2f TL", book.getPrice()));
        System.out.printf (" Toplam Borç : %-20s║%n", String.format("%.2f TL", record.getTotalBill()));
        System.out.println("  ------------------FATURA SONU--------------------");
    }



    public boolean authenticate(String password) { return this.password.equals(password); }

    public String getName() {
        return name;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}
