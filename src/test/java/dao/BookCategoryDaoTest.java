package dao;

import business.BookCategory;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mercy
 */

public class BookCategoryDaoTest {

    /**
     * Test of addCatergory_2args() method, of class BookCategoryDao.
     */
    @Test
    public void addBookCatergory_2args() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("add bookCategory with 2 arguments");
        int bookId = 2;
        int catId =9;

        int result = bookcategoryDao.addBookCatergory(bookId,catId);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = bookcategoryDao.deleteBookCategory(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    /**
     * Test of addCatergory() method, of class BookCategoryDao.
     */
    @Test
    public void addBookCatergory() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("add bookCategory with 2 arguments");
        int bookId = 2;
        int catId =9;
        BookCategory bc = new BookCategory(bookId,catId);

        int result = bookcategoryDao.addBookCatergory(bc);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = bookcategoryDao.deleteBookCategory(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    /**
     * Test of addBookCategory_AddFail() method, of class BookCategoryDao.
     */
    @Test
    public void AddBookCatergory_AddFaild() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("add bookCategory with 2 arguments failed");
        int bookId = 2;
        int catId =9;
        BookCategory bc = new BookCategory(bookId,catId);

        int expResult = -1;
        int result = bookcategoryDao.addBookCatergory(bc);
        assertEquals(expResult, result);

        if (result == -1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "has NOT changed by trying to remove what was added and failing");
            int rowsDeleted = bookcategoryDao.deleteBookCategory(result);
            assertEquals(rowsDeleted, 0);
        }
    }

    /**
     * Test of getAllBookCategory() method, of class BookCategoryDao.
     */
    @Test
    public void getAllBookCategory() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("get All BookCategory");
        ArrayList<BookCategory> result = (ArrayList<BookCategory>) bookcategoryDao.getAllBookCategory();
        assertEquals(12, result.size());
    }

    /**
     * Test of getBookCategoryByBookId() method, of class BookCategoryDao.
     */
    @Test
    public void getBookCategoryByBookId() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("get BookCategory By Book Id");
        int bookId = 3;

        BookCategory expResult = new BookCategory(3,6);
        BookCategory result = bookcategoryDao.getBookCategoryByBookId(bookId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBookCategoryByBookId_NotFound() method, of class CategoryDao.
     */
    @Test
    public void getBookCategoryByBookId_NotFound() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("get BookCategory By Book Id Not Found");
        int bookId = 15;

        BookCategory expResult = null;
        BookCategory result = bookcategoryDao.getBookCategoryByBookId(bookId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBookCategoryByCategoryId() method, of class BookCategoryDao.
     */
    @Test
    public void getBookCategoryByCategoryId() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("get BookCategory By Category Id");
        int catId = 2;

        BookCategory expResult = new BookCategory(2,2);
        BookCategory result = bookcategoryDao.getBookCategoryByCategoryId(catId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBookCategoryByCategoryId_NotFound() method, of class BookCategoryDao.
     */
    @Test
    public void getBookCategoryByCategoryId_NotFound() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("get BookCategory By Category Id Not Found");
        int catId = 13;

        BookCategory expResult = null;
        BookCategory result = bookcategoryDao.getBookCategoryByCategoryId(catId);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteBookCategory() method, of class BookCategoryDao.
     */
    @Test
    public void deleteBookCategory() throws DaoException {
        BookCategoryDao bookcategoryDao = new BookCategoryDao("testlibrary");
        System.out.println("delete BookCategory By Id");
        BookCategory bc = new BookCategory(1,1);

        int id = bc.getId();
        int expResult = 1;

        int result = bookcategoryDao.deleteBookCategory(id);
        assertEquals(expResult, result);

        if (result == 1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "changed by trying to select what was deleted");
            BookCategory selectedBookCategory = bookcategoryDao.getBookCategoryByBookId(bc.getId());
            assertEquals(null, selectedBookCategory);

            if (selectedBookCategory == null) {
                bookcategoryDao.addBookCatergory(bc);
            }
    }
}