import entity.concrete.Librarian;
import entity.concrete.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Librarian librarian = new Librarian("Admin Kütüphaneci", "LIB001", "1234");
        Library library   = new Library("Merkez Kütüphane", librarian);
    /* StudyBooks kitap1 = new StudyBooks(
                "B001", "Yilmaz Ozturk",
                "Java ile Nesne Yonelimli Programlama",
                85.0, "3.Baski", "2022-01-10",
                "Yazilim Muhendisligi", "Universite");*/
       // newBook(kitap1);
    }
}