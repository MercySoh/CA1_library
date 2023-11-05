package dao;

import business.Book;
import exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface BookDaoInterface {

    public int addBook(String title, String author, int ISBN, Date publication_date, int qty, String description, int copy_qty) throws DaoException;

    public int addBook(Book newbook) throws DaoException;

    public List<Book> getAllBook() throws DaoException;

    public Book getBookByTitle(String title) throws DaoException;

    public int increaseCopyStock(int increaseAmount, String title) throws DaoException;

    public int decreaseCopyStock(int decreaseAmount, String title) throws DaoException;

    public int deleteBook(int bookId) throws DaoException;

    public Book getBookById(int bookId) throws DaoException;

}
