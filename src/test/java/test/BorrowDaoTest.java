package test;

import dao.BorrowDao;
import dao.UsersDao;
import exceptions.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Conor
 */
class BorrowDaoTest {


    private UsersDao userDao = new UsersDao("testlibrary");
    private BorrowDao borrowDao;

    @BeforeEach
    void setUp() {
        borrowDao = new BorrowDao("testlibrary");
    }

    /**
     * This tests borrowing the book with no chance of failure
     */
    @Test
    void bookBorrowTest1() throws DaoException {
        int user_id = 1;
        int book_id = 2;

        int rows = borrowDao.bookBorrow(user_id, book_id);

        assertEquals(1,rows);

        int copyLoan=borrowDao.bookBorrow(user_id,book_id);

        assertEquals(0,copyLoan);

        assertEquals(1,borrowDao.returnBook(user_id,book_id));

    }

    @Test
    void returnBook() {
    }

    @Test
    void getAllCurrentLoansByUserID() {
    }

    @Test
    void getAllLoansByUserID() {
    }

    @Test
    void checkingDuplicateLoans() {
    }

    @Test
    void imposeLateFee() {
    }
}