package dao;

import business.Book;
import exceptions.DaoException;

import java.sql.Date;
import java.util.List;

/**
 * @author Mercy
 */

public interface BookDaoInterface {

    /**
     * addBook(with 7 args) method allows admin/staff to add a new book,
     *
     * @param title a string of book's title
     * @param author a string of book's author
     * @param ISBN an int(10) of book's ISBN
     * @param publication_date a date of book's publication date
     * @param qty an int of book's stock
     * @param description a string of book's description
     * @param copy_qty an int of copy of book's stock
     * @return new book id if added else return -1
     * @throws DaoException if failure
     */
    public int addBook(String title, String author, int ISBN, Date publication_date, int qty, String description, int copy_qty) throws DaoException;

    /**
     * addBook(Book newbook) method allows admin/staff to add a new book,
     *
     * @param newbook the new <code>Book</code> to be added
     * @return new book id if added else return -1
     * @throws DaoException if failure
     */
    public int addBook(Book newbook) throws DaoException;

    /**
     * getAllBook method return a list of all book in library,
     *
     * @return a list of all book in library
     * @throws DaoException if failure
     */
    public List<Book> getAllBook() throws DaoException;

    /**
     * getBookByTitle method return the book that match the title,
     *
     * @param title a string of book's title to find
     * @return Book that match the title else return null if not found
     * @throws DaoException if failure
     */
    public Book getBookByTitle(String title) throws DaoException;

    /**
     * increaseCopyStock method will increase the copy_qty of the book's title when title match,
     *
     * @param increaseAmount an int of number to increase the stock of copy of book
     * @param title  a string of book's title to find
     * @return an int if increase success
     * @throws DaoException if failure
     */
    public int increaseCopyStock(int increaseAmount, String title) throws DaoException;

    /**
     * decreaseCopyStock method will decrease the copy_qty of the book's title when title match,
     *
     * @param decreaseAmount an int of number to decrease the stock of copy of book
     * @param title  a string of book's title to find
     * @return an int if decrease success
     * @throws DaoException if failure
     */
    public int decreaseCopyStock(int decreaseAmount, String title) throws DaoException;

    /**
     * deleteBook method delete a book by book's id from the library.
     * @param bookId an int of book's id to be deleted.
     * @return 1 if deleted else return 0.
     */
    public int deleteBook(int bookId) throws DaoException;

    /**
     * getBookById method return the book that match the book id,
     *
     * @param bookId an int of book's id to find
     * @return Book that match the book's id else return null if not found
     * @throws DaoException if failure
     */
    public Book getBookById(int bookId) throws DaoException;

}
