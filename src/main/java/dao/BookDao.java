package dao;

import business.Book;

import java.util.List;

public class BookDao extends Dao implements BookDaoInterface  {

    public BookDao(String databaseName) {super(databaseName);}

    @Override
    public boolean addBook(Book newbook) {
        return false;
    }

    @Override
    public List<Book> getAllBook() {
        return null;
    }

    @Override
    public Book getBookByTitle(String title) {
        return null;
    }

    @Override
    public int increaseCopyStock(int increaseAmount) {
        return 0;
    }

    @Override
    public int decreaseCopyStock(int decreaseAmount) {
        return 0;
    }
}
