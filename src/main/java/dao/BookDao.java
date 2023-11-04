package dao;

import business.Book;
import exceptions.DaoException;

import java.util.List;

public class BookDao extends Dao implements BookDaoInterface  {

    public BookDao(String databaseName) {super(databaseName);}


    @Override
    public boolean addBook(Book newbook) throws DaoException {
        return false;
    }

    @Override
    public List<Book> getAllBook() throws DaoException {
        return null;
    }

    @Override
    public Book getBookByTitle(String title) throws DaoException {
        return null;
    }

    @Override
    public int increaseCopyStock(int increaseAmount) throws DaoException {
        return 0;
    }

    @Override
    public int decreaseCopyStock(int decreaseAmount) throws DaoException {
        return 0;
    }
}
