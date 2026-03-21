

import entity.Book;

import entity.concrete.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    // --- Kutuphanenin temel bilgileri ---
    private String    libraryName;
    private Librarian librarian;

    // --- Kitaplari saklayan map: kitap ID'si → Kitap nesnesi ---
    private Map<String, Book> bookMap;

    // --- Uyeleri saklayan map: uye ID'si → Reader nesnesi ---
    private Map<String, Reader> readerMap;

    // --- Hangi kitap kimde: kitap ID'si → uye ID'si ---
    private Map<String, String> loanMap;

    // --- Islem gecmisi listesi ---
    private List<String> transactionHistory;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public Library(String libraryName, Librarian librarian) {
        this.libraryName        = libraryName;
        this.librarian          = librarian;
        this.bookMap            = new HashMap<>();
        this.readerMap          = new HashMap<>();
        this.loanMap            = new HashMap<>();
        this.transactionHistory = new ArrayList<>();

        loadSampleData();
    }


    // =========================================================
    // KITAP EKLEME
    // =========================================================
    public boolean newBook(Book book) {

        // Ayni ID'den zaten var mi kontrol et
        if (bookMap.containsKey(book.getBookID())) {
            System.out.println("Bu ID zaten kayitli: " + book.getBookID());
            return false;
        }

        // Map'e ekle
        bookMap.put(book.getBookID(), book);

        // Gecmise kaydet
        transactionHistory.add("[EKLEME] " + book.getTitle());

        System.out.println("Kitap eklendi: " + book.getTitle());
        return true;
    }


    // =========================================================
    // KITAP SILME
    // =========================================================
    public boolean removeBook(String bookID) {

        // Kitap var mi kontrol et
        if (!bookMap.containsKey(bookID)) {
            System.out.println("Kitap bulunamadi: " + bookID);
            return false;
        }

        // Once kitabi bul, sonra map'ten sil
        Book book = bookMap.get(bookID);
        bookMap.remove(bookID);

        System.out.println("Kitap silindi: " + book.getTitle());
        return true;
    }


    // =========================================================
    // KITAP GUNCELLEME
    // =========================================================
    public boolean updateBook(String bookID, String yeniAd, String yeniYazar,
                              double yeniFiyat, String yeniBaski) {

        // Kitap var mi kontrol et
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


    // =========================================================
    // KITAP ARAMA — ID ile
    // =========================================================
    public Book findBookByID(String bookID) {
        return bookMap.get(bookID);  // yoksa null doner
    }


    // =========================================================
    // KITAP ARAMA — Isim ile
    // =========================================================
    public List<Book> findBookByName(String arananIsim) {

        List<Book> sonuclar = new ArrayList<>();

        // Tum kitaplara tek tek bak
        for (Book book : bookMap.values()) {

            // Kucuk harfe cevirerek karsilastir
            String kitapAdi = book.getName().toLowerCase();
            String aranan   = arananIsim.toLowerCase();

            if (kitapAdi.contains(aranan)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }


    // =========================================================
    // KITAP ARAMA — Yazara gore
    // =========================================================
    public List<Book> findBookByAuthor(String yazar) {

        List<Book> sonuclar = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getAuthor().equals(yazar)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }


    // =========================================================
    // KITAP ARAMA — Kategoriye gore
    // =========================================================
    public List<Book> findBookByCategory(String kategori) {

        List<Book> sonuclar = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getCategory().equals(kategori)) {
                sonuclar.add(book);
            }
        }

        return sonuclar;
    }


    // =========================================================
    // TUM KITAPLARI LISTELE
    // =========================================================
    public void showBook() {

        if (bookMap.isEmpty()) {
            System.out.println("Sistemde hic kitap yok.");
            return;
        }

        System.out.println("=== TUM KITAPLAR ===");

        for (Book book : bookMap.values()) {
            System.out.println(book);   // Book'un toString() metodu calisir
        }
    }


    // =========================================================
    // ODUNC VERME
    // =========================================================
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


    // =========================================================
    // IADE ALMA
    // =========================================================
    public boolean takeBackBook(String memberID, String bookID) {

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

        // Bu kitap gercekten bu uyede mi kayitli?
        String kitabiAlanUyeID = loanMap.get(bookID);

        if (kitabiAlanUyeID == null) {
            System.out.println("Bu kitap hic odunc verilmemis.");
            return false;
        }

        if (!kitabiAlanUyeID.equals(memberID)) {
            System.out.println("Bu kitap bu uyede kayitli degil.");
            return false;
        }

        // Kutuphaneciyi kullanarak iade al
        boolean basarili = librarian.returnBook(reader, book);

        if (basarili) {
            // Loan map'ten sil
            loanMap.remove(bookID);

            // Gecmise yaz
            transactionHistory.add("[IADE] " + reader.getName() + " -> " + book.getTitle());
        }

        return basarili;
    }


    // =========================================================
    // UYE EKLEME
    // =========================================================
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


    // =========================================================
    // UYE BULMA
    // =========================================================
    public Reader getReader(String memberID) {
        return readerMap.get(memberID);  // yoksa null doner
    }


    // =========================================================
    // TUM UYELERI GETIR
    // =========================================================
    public List<Reader> getAllReaders() {
        List<Reader> liste = new ArrayList<>();

        for (Reader reader : readerMap.values()) {
            liste.add(reader);
        }

        return liste;
    }


    // =========================================================
    // TUM KITAPLARI GETIR
    // =========================================================
    public List<Book> getBooks() {
        List<Book> liste = new ArrayList<>();

        for (Book book : bookMap.values()) {
            liste.add(book);
        }

        return liste;
    }


    // =========================================================
    // ISLEM GECMISI
    // =========================================================
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


    // =========================================================
    // AKTIF ODUNCLER
    // =========================================================
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


    // =========================================================
    // YARDIMCI GETTERLAR
    // =========================================================
    public String    getName()      { return libraryName; }
    public Librarian getLibrarian() { return librarian; }
    public Map<String, String> getLoanMap() { return loanMap; }

    // ConsoleUI icin gereken ek getterlar
    public List<String> getHistory() { return transactionHistory; }

    public List<String> getCategories() {
        List<String> kategoriler = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (!kategoriler.contains(book.getCategory())) {
                kategoriler.add(book.getCategory());
            }
        }
        return kategoriler;
    }

    public List<String> getAuthors() {
        List<String> yazarlar = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (!yazarlar.contains(book.getAuthor())) {
                yazarlar.add(book.getAuthor());
            }
        }
        return yazarlar;
    }


    // =========================================================
    // ORNEK VERI YUKLEME
    // =========================================================
    private void loadSampleData() {
        System.out.println("[SISTEM] Ornek veriler yukleniyor...");

        // --- Kitaplar ---
        StudyBooks kitap1 = new StudyBooks(
                "B001", "Yilmaz Ozturk",
                "Java ile Nesne Yonelimli Programlama",
                85.0, "3.Baski", "2022-01-10",
                "Yazilim Muhendisligi", "Universite");
        newBook(kitap1);

        StudyBooks kitap2 = new StudyBooks(
                "B002", "Ali Kaya",
                "Veri Yapilari ve Algoritmalar",
                70.0, "2.Baski", "2021-05-20",
                "Bilgisayar Bilimi", "Lisans");
        newBook(kitap2);

        Journals kitap3 = new Journals(
                "B003", "Ahmet Demir",
                "TUBITAK Bilisim Dergisi",
                30.0, "Aylik", "2023-03-01",
                "42", "TUBITAK Yayinlari");
        newBook(kitap3);

        Magazines kitap4 = new Magazines(
                "B004", "Editor Ekibi",
                "National Geographic Turkiye",
                25.0, "Aylik", "2024-02-01",
                "Subat 2024", "Doga & Bilim");
        newBook(kitap4);

        StudyBooks kitap5 = new StudyBooks(
                "B005", "Fatma Celik",
                "Algoritma Tasarimi",
                90.0, "1.Baski", "2020-09-15",
                "Yazilim Muhendisligi", "Yuksek Lisans");
        newBook(kitap5);

        // --- Uyeler ---
        Student uye1 = new Student(
                "Mehmet Yilmaz", "M001", "2023-09-01",
                "Kizilay, Ankara", "5551112233",
                "20231001", "Bilgisayar Muh.");
        addReader(uye1);

        Faculty uye2 = new Faculty(
                "Dr. Zeynep Arslan", "M002", "2020-01-15",
                "Kadikoy, Istanbul", "5559998877",
                "FAC001", "Muhendislik Fakultesi");
        addReader(uye2);

        Student uye3 = new Student(
                "Ayse Kara", "M003", "2024-02-10",
                "Konak, Izmir", "5554445566",
                "20241002", "Elektrik-Elektronik Muh.");
        addReader(uye3);

        System.out.println("[SISTEM] Veriler yuklendi.\n");
    }
}