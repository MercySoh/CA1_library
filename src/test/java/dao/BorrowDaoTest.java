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
     * This tests borrowing the book with no chance of failure
     * Run into problems during the borrowBook test.
     */
    @Test
    void bookBorrowTest1() throws DaoException {
         BorrowDao borrowDao = new BorrowDao("testlibrary");
            int user_id = 2;
            int book_id = 1;
       int actual=  borrowDao.bookBorrow(user_id,book_id);
            assertEquals(1, actual);

            // Verify that the user has the expected current loan
            List<Borrow> actualBorrow = borrowDao.getAllCurrentLoansByUserID(user_id);

            // Prepare the expected Borrow object
            List<Borrow> expectedBorrow = new ArrayList<>();
            expectedBorrow.add(new Borrow(1, user_id, book_id, LocalDate.now(), LocalDate.now().plusWeeks(4), null, 0));

            assertEquals(expectedBorrow, actualBorrow);
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
