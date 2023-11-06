package dao;

import business.BookCategory;
import exceptions.DaoException;

import java.util.List;

public interface BookCategoryDaoInterface {

    /**
     * addBookCatergory(with 2args) method allows admin/staff to add book to a new catergory,
     *
     * @param bookId an int of book's id to add to category
     * @param catId an int of category's id to be added in book
     * @return new bookCategory's id if added else return -1
     * @throws DaoException if failure
     */
    public int addBookCatergory(int bookId, int catId) throws DaoException;

    /**
     * addBookCatergory(BookCategory newBookCategory) method allows admin/staff to add a book to category,
     *
     * @param newBookCategory the new <code>BookCategory</code> to be added
     * @return new bookCategory's id if added else return -1
     * @throws DaoException if failure
     */
    public int addBookCatergory(BookCategory newBookCategory) throws DaoException;

    /**
     * getAllBookCategory method return a list of all book in all category in library,
     *
     * @return a list of all book in all category in library
     * @throws DaoException if failure
     */
    public List<BookCategory> getAllBookCategory() throws DaoException;

    /**
     * getBookCategoryByBookId method return the bookCategory that match the book id,
     *
     * @param bookId an int of book's id to find
     * @return bookCategory that match the book's id else return null if not found
     * @throws DaoException if failure
     */

    public BookCategory getBookCategoryByBookId(int bookId) throws DaoException;

    /**
     * getBookCategoryByCategoryId method return the bookCategory that match the category id,
     *
     * @param categoryId an int of book's id to find
     * @return bookCategory that match the category's id else return null if not found
     * @throws DaoException if failure
     */

    public BookCategory getBookCategoryByCategoryId(int categoryId) throws DaoException;
    /**
     * deleteBookCategory method delete a bookCategory by book's id from the library.
     * @param bookId an int of book's id to be deleted.
     * @return 1 if deleted else return 0.
     * @throws DaoException if failure
     */
    public int deleteBookCategory(int bookId) throws DaoException;
}
