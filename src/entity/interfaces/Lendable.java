package entity.interfaces;

public interface Lendable {
    String  getTitle();
    double  getPrice();
    boolean isAvailable();
    void    lend();
    void    returnItem();
    void    display();
    void    updateStatus(boolean status);
}
