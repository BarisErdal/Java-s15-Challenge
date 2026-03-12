package entity.concrete;

import entity.Book;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {

    private List<Book> books= new ArrayList<>()


    @Override
    public String whoyouare() {
        return "Reader";
    }
}
