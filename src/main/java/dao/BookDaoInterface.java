package dao;

import business.Book;
import exceptions.DaoException;

import java.util.List;

public interface BookDaoInterface {

    public boolean addBook(Book newbook) throws DaoException;

    public List<Book> getAllBook() throws DaoException;

    public Book getBookByTitle(String title) throws DaoException;

    public int increaseCopyStock(int increaseAmount) throws DaoException;

    public int decreaseCopyStock(int decreaseAmount) throws DaoException;

}
