package dao;

import business.Book;

import java.util.List;

public interface BookDaoInterface {

    public boolean addBook(Book newbook);

    public List<Book> getAllBook();

    public Book getBookByTitle(String title);

    public int increaseCopyStock(int increaseAmount);

    public int decreaseCopyStock(int decreaseAmount);

}
