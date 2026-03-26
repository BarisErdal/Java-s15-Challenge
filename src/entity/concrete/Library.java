package entity.concrete;

import entity.Book;

import entity.concrete.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {


    private String    libraryName;
    private Librarian librarian;
    private Map<String, Book> bookMap;
    private Map<String, Reader> readerMap;
    //  Hangi kitap kimde
    private Map<String, String> loanMap;
    private List<String> transactionHistory;



    public Library(String libraryName, Librarian librarian) {
        this.libraryName        = libraryName;
        this.librarian          = librarian;
        this.bookMap            = new HashMap<>();
        this.readerMap          = new HashMap<>();
        this.loanMap            = new HashMap<>();
        this.transactionHistory = new ArrayList<>();


    }



    public boolean newBook(Book book) {


        if (bookMap.containsKey(book.getBookID())) {
            System.out.println("Bu ID zaten kayitli: " + book.getBookID());
            return false;
        }


        bookMap.put(book.getBookID(), book);


        transactionHistory.add("[EKLEME] " + book.getTitle());

        System.out.println("Kitap eklendi: " + book.getTitle());
        return true;
    }



    public boolean removeBook(String bookID) {


        if (!bookMap.containsKey(bookID)) {
            System.out.println("Kitap bulunamadi: " + bookID);
            return false;
        }

        Book book = bookMap.get(bookID);
        bookMap.remove(bookID);

        System.out.println("Kitap silindi: " + book.getTitle());
        return true;
    }



    public boolean updateBook(String bookID, String yeniAd, String yeniYazar,
                              double yeniFiyat, String yeniBaski) {


        if (!bookMap.containsKey(bookID)) {
            System.out.println("Kitap bulunamadi: " + bookID);
            return false;
        }

        Book book = bookMap.get(bookID);

        // Kullanici bos birakmadiysa guncelle
        if (yeniAd != null && !yeniAd.equals("")) {
            book.setName(yeniAd);
        }

        if (yeniYazar != null && !yeniYazar.equals("")) {
            book.setAuthor(yeniYazar);
        }

        if (yeniFiyat > 0) {
            book.setPrice(yeniFiyat);
        }

        if (yeniBaski != null && !yeniBaski.equals("")) {
            book.setEdition(yeniBaski);
        }

        System.out.println("Kitap guncellendi: " + book.getTitle());
        return true;
    }



    public Book findBookByID(String bookID) {
        return bookMap.get(bookID);
    }



    public List<Book> findBookByName(String arananIsim) {

        List<Book> sonuclar = new ArrayList<>();


        for (Book book : bookMap.values()) {


            String kitapAdi = book.getName().toLowerCase();
            String aranan   = arananIsim.toLowerCase();

            if (kitapAdi.contains(aranan)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }



    public List<Book> findBookByAuthor(String yazar) {

        List<Book> sonuclar = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getAuthor().equals(yazar)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }



    public List<Book> findBookByCategory(String kategori) {

        List<Book> sonuclar = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getCategory().equals(kategori)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }



    public void showBook() {

        if (bookMap.isEmpty()) {
            System.out.println("Sistemde hic kitap yok.");
            return;
        }

        System.out.println("=== TUM KITAPLAR ===");

        for (Book book : bookMap.values()) {
            System.out.println(book);
        }
    }



    public boolean lendBook(String memberID, String bookID) {

        // Uyeyi bul
        Reader reader = readerMap.get(memberID);
        if (reader == null) {
            System.out.println("Uye bulunamadi: " + memberID);
            return false;
        }

        // Kitabi bul
        Book book = bookMap.get(bookID);
        if (book == null) {
            System.out.println("Kitap bulunamadi: " + bookID);
            return false;
        }

        // Kutuphaneciyi kullanarak odunc ver
        boolean basarili = librarian.issueBook(reader, book);

        if (basarili) {
            // Hangi kitap kimde map'ine kaydet
            loanMap.put(bookID, memberID);

            // Gecmise yaz
            transactionHistory.add("[ODUNC] " + reader.getName() + " -> " + book.getTitle());
        }

        return basarili;
    }



    public boolean takeBackBook(String memberID, String bookID) {


        Reader reader = readerMap.get(memberID);
        if (reader == null) {
            System.out.println("Uye bulunamadi: " + memberID);
            return false;
        }


        Book book = bookMap.get(bookID);
        if (book == null) {
            System.out.println("Kitap bulunamadi: " + bookID);
            return false;
        }


        String kitabiAlanUyeID = loanMap.get(bookID);

        if (kitabiAlanUyeID == null) {
            System.out.println("Bu kitap hic odunc verilmemis.");
            return false;
        }

        if (!kitabiAlanUyeID.equals(memberID)) {
            System.out.println("Bu kitap bu uyede kayitli degil.");
            return false;
        }


        boolean basarili = librarian.returnBook(reader, book);

        if (basarili) {

            loanMap.remove(bookID);

            transactionHistory.add("[IADE] " + reader.getName() + " -> " + book.getTitle());
        }

        return basarili;
    }



    public boolean addReader(Reader reader) {

        String memberID = reader.getMemberRecord().getMemberID();

        // Ayni ID'den zaten var mi?
        if (readerMap.containsKey(memberID)) {
            System.out.println("Uye zaten kayitli: " + memberID);
            return false;
        }

        readerMap.put(memberID, reader);
        System.out.println("Uye eklendi: " + reader.getName());
        return true;
    }



    public Reader getReader(String memberID) {
        return readerMap.get(memberID);  // yoksa null doner
    }



    public List<Reader> getAllReaders() {
        List<Reader> liste = new ArrayList<>();

        for (Reader reader : readerMap.values()) {
            liste.add(reader);
        }

        return liste;
    }




    public List<Book> getBooks() {
        List<Book> liste = new ArrayList<>();

        for (Book book : bookMap.values()) {
            liste.add(book);
        }

        return liste;
    }



    // ISLEM GECMISI

    public void printHistory() {

        if (transactionHistory.isEmpty()) {
            System.out.println("Islem gecmisi bos.");
            return;
        }

        System.out.println("=== ISLEM GECMISI ===");

        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.println((i + 1) + ". " + transactionHistory.get(i));
        }
    }



    // AKTIF ODUNCLER

    public void printActiveLoans() {

        if (loanMap.isEmpty()) {
            System.out.println("Aktif odunc yok.");
            return;
        }

        System.out.println("=== AKTIF ODUNCLER ===");

        for (String bookID : loanMap.keySet()) {
            String memberID = loanMap.get(bookID);

            Book   book   = bookMap.get(bookID);
            Reader reader = readerMap.get(memberID);

            System.out.println("Kitap: " + book.getTitle() + "  -->  Uye: " + reader.getName());
        }
    }



    // GETTERLAR

    public String    getName()      { return libraryName; }
    public Librarian getLibrarian() { return librarian; }
    public Map<String, String> getLoanMap() { return loanMap; }






}