package dao;

import business.Borrow;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowDaoTest {


    /**
     * This tests deleteBorrow1
     * where it is under normal conditions
     */
    @Test
    void deleteBorrow1() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");

        borrowDao.bookBorrow(2, 3);
        borrowDao.returnBook(2,3);

        //Rows affected
        assertEquals(1, borrowDao.deleteLoan(2,3));
    }

    /**
     * This tests deleteBorrow1
     * where it is under abnormal conditions
     * where the values are not in the database
     */
    @Test
    void deleteBorrow2() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");


        assertEquals(1, borrowDao.deleteLoan(200,300));
    }


    /**
     * This tests borrowing the book with no chance of failure
     * Run into problems during the borrowBook test.
     */
    @Test
    void bookBorrowTest1() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");
        int user_id = 2;
        int book_id = 1;
        int actual = borrowDao.bookBorrow(user_id, book_id);
        assertEquals(1, actual);


        List<Borrow> actualBorrow = borrowDao.getAllCurrentLoansByUserID(user_id);

        List<Borrow> expectedBorrow = new ArrayList<>();
        expectedBorrow.add(new Borrow(1, user_id, book_id, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));

        // deleting the loan for the next test
        borrowDao.deleteLoan(2,1);
        assertEquals(expectedBorrow, actualBorrow);
    }

    /**
     * This bookBorrowTest2 borrowing the book with failure
     * occurring, with a duplicate book
     */
    @Test
    void bookBorrowTest2() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");

        // The same book being borrowed three times
        borrowDao.bookBorrow(1, 1);
        borrowDao.bookBorrow(1, 1);
        borrowDao.bookBorrow(1, 1);

        List<Borrow> actual = borrowDao.getAllCurrentLoansByUserID(1);

        List<Borrow> expectedBorrow = new ArrayList<>();
        expectedBorrow.add(new Borrow(1, 1, 1, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));
        // deleting the loan for the next test
        borrowDao.deleteLoan(1,1);
        assertEquals(expectedBorrow, actual);
    }


    /**
     * This returnBook_test1 returning the book under
     * normal conditions
     */
    @Test
    void returnBook_test1() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");
        borrowDao.bookBorrow(2,1);
        int user_id = 2;
        int book_id = 1;
        int actual = borrowDao.returnBook(user_id, book_id);
        assertEquals(1, actual);


        List<Borrow> actualReturn = borrowDao.getAllCurrentLoansByUserID(user_id);

        List<Borrow> expectedBorrow = new ArrayList<>();
        expectedBorrow.add(new Borrow(1, user_id, book_id, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));
        // deleting the loan for the next test
        borrowDao.deleteLoan(2,1);
        assertEquals(expectedBorrow, actualReturn);
    }

    /**
     * This tests getAllCurrentLoansByUserID
     * where it is under normal conditions
     */
    @Test
    void getAllCurrentLoansByUserID() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");
        int user_id = 3;
        int book_id = 2;
        borrowDao.bookBorrow(user_id, book_id);

        // Getting expected Borrowed Books
        List<Borrow> actualBorrow = borrowDao.getAllCurrentLoansByUserID(user_id);

        List<Borrow> expectedBorrow = new ArrayList<>();
        expectedBorrow.add(new Borrow(1, user_id, book_id, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));
        // deleting the loan for the next test
        borrowDao.deleteLoan(3,2);
        assertEquals(expectedBorrow, actualBorrow);
    }

    /**
     * This tests getAllCurrentLoansByUserID
     * where it is under abnormal conditions
     */
    @Test
    void getAllCurrentLoansByUserID_NonExistantUser() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");

        List<Borrow> expectedBorrow = new ArrayList<>();
        List<Borrow> currentActual = borrowDao.getAllCurrentLoansByUserID(333);
;       assertEquals(expectedBorrow, currentActual);
    }

    /**
     * This tests getAllLoansByUserID
     * where it is under normal conditions
     */
    @Test
    void getAllLoansByUserID() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");
        int user_id = 3;
        int book_id = 2;
        borrowDao.bookBorrow(user_id, book_id);

        // Getting expected Borrowed Books
        List<Borrow> actualBorrow = borrowDao.getAllLoansByUserID(user_id);

        List<Borrow> expectedBorrow = new ArrayList<>();
        expectedBorrow.add(new Borrow(1, user_id, book_id, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));
        // deleting the loan for the next test
        borrowDao.deleteLoan(3,2);
        assertEquals(expectedBorrow, actualBorrow);
    }

    /**
     * This tests getAllLoansByUserID
     * where it is under abnormal conditions,
     * where the Userid does not exist
     */
    @Test
    void getAllLoansByUserID2() throws DaoException {
        BorrowDao borrowDao = new BorrowDao("testlibrary");

        List<Borrow> actualBorrow = borrowDao.getAllLoansByUserID(222);

        List<Borrow> expectedBorrow = new ArrayList<>();
        assertEquals(expectedBorrow, actualBorrow);
    }


}
