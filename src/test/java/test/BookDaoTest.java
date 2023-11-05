package test;

import business.Book;
import dao.BookDao;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookDaoTest {

    @Test
    public void addBook_7args() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("addBook with 7 arguments");
        String title = "TestNewBook";
        String author = "Pinky White";
        int ISBN = 1234567890;
        Date publication_date = new Date(2023,05,19);
        int qty = 3;
        String description = "Test New Book description";
        int copy_qty = 0;

        int result = bookDao.addBook(title,author,ISBN,publication_date,qty,description,copy_qty);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = bookDao.deleteBook(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    @Test
    public void addBook_7args_AddFail() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("addBook with 7 arguments failed");
        String title = "TestNewBookFail";
        String author = "Benny Ben";
        int ISBN = 1876543210;
        Date publication_date = new Date(2023,04,19);
        int qty = 3;
        String description = "Test New Book Fail description";
        int copy_qty = 0;

        int expResult = -1;
        int result = bookDao.addBook(title,author,ISBN,publication_date,qty,description,copy_qty);
        assertEquals(expResult, result);

        if (result == -1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "has NOT changed by trying to remove what was added and failing");
            int rowsDeleted = bookDao.deleteBook(result);
            assertEquals(rowsDeleted, 0);
        }
    }
    @Test
    public void addBook_Book() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("addBook with Book");
        String title = "TestNewBook2";
        String author = "Pinky White2";
        int ISBN = 1789456123;
        Date publication_date = new Date(2023,03,20);
        int qty = 2;
        String description = "Test New Book description2";
        int copy_qty = 0;
        Book nb = new Book(title,author,ISBN,publication_date,qty,description,copy_qty);

        int result = bookDao.addBook(nb);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = bookDao.deleteBook(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    @Test
    void getAllBook() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("getAllBook");
        ArrayList<Book> result = (ArrayList<Book>) bookDao.getAllBook();
        assertEquals(6, result.size());
    }

    @Test
    void getBookByTitle() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("getBookByTitle");
        String title = "Look Inside Food";
        Book expResult = new Book(6,"Look Inside Food","Emily Bone",140958206,2015-06-01,3,"It is important for children to understand food - where it comes from, whats in it, and how it affects our bodies. ",3
        );
        Book result = bookDao.getBookByTitle(title);
        assertEquals(expResult, result);
    }

    @Test
    void getBookByTitle_NotFound() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("getBookByTitle_NotFound");
        String title = "BookTestNotFound";
        Book expResult = null;
        Book result = bookDao.getBookByTitle(title);
        assertEquals(expResult, result);
    }

    @Test
    void increaseCopyStock() {
    }

    @Test
    void decreaseCopyStock() {
    }

    @Test
    public void deleteBook() throws DaoException {
            BookDao bookDao = new BookDao("testlibrary");
            System.out.println("deleteBookById");
            Book b = new Book(4,"The Little Book of Student Food", "Alastair Williams",1787830241,2019-08-08,3,"This pocket-sized collection of helpful guidance and satisfying recipes will help students eat well and affordably",0
            );
            int id = b.getId();
            int expResult = 1;

            int result = bookDao.deleteBook(id);
            assertEquals(expResult, result);

            if (result == 1) {
                System.out.println("Method returned appropriately, confirming database "
                        + "changed by trying to select what was deleted");
                Book selectedBook = bookDao.getBookById(b.getId());
                assertEquals(null, selectedBook);

                // If the delete action was successful, add it back into the database again
                if (selectedBook == null) {
                    bookDao.addBook(b);
                }
            }
    }

    @Test
    public void getBookById() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("getBookById");
        int id = 3;
        Book expResult = new Book(3,"Ginger Pig Christmas Cook Book","Tim Wilson" ,1784729191,2023-10-05,3,"More than 80 delicious recipes for the perfect Christmas from acclaimed sustainable butcher Ginger Pig.",0
);
        Book result = bookDao.getBookById(id);
        assertEquals(expResult, result);
    }

    @Test
    public void getBookById_NotFound() throws DaoException {
        BookDao bookDao = new BookDao("testlibrary");
        System.out.println("getBookById");
        int id = 10;
        Book expResult = null;
        Book result = bookDao.getBookById(id);
        assertEquals(expResult, result);
    }
}