import entity.concrete.Librarian;
import entity.concrete.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Kütüphane");

        Librarian librarian = new Librarian("Admin Kütüphaneci", "LIB001", "1234");
        Library library   = new Library("Merkez Kütüphane", librarian);
    StudyBooks kitap1 = new StudyBooks(
                "B001", "Yilmaz Ozturk",
                "Java ile Nesne Yonelimli Programlama",
                85.0, "3.Baski", "2022-01-10",
                "Yazilim Muhendisligi", "Universite");

        Magazines kitap4 = new Magazines(
                "B004", "Editor Ekibi",
                "National Geographic Turkiye",
                25.0, "Aylik", "2024-02-01",
                "Subat 2024", "Doga & Bilim");




        Student uye1 = new Student(
                "Mehmet Yilmaz", "M001", "2023-09-01",
                "Kizilay, Ankara", "5551112233",
                "20231001", "Bilgisayar Muh.");


        Faculty uye2 = new Faculty(
                "Dr. Zeynep Arslan", "M002", "2020-01-15",
                "Kadikoy, Istanbul", "5559998877",
                "FAC001", "Muhendislik Fakultesi");

        uye2.borrowBook(kitap1);


        System.out.println(uye2.getBooks());









    }
}