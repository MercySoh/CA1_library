package dao;

import business.Borrow;
import java.util.List;
/**
 * @author Conor
 */
public interface BorrowDaoInterface {

    int bookBorrow(int user_id,int book_id);

    int returnBook(int user_id,int book_id);

    List<Borrow> getAllCurrentLoansByUserID(int user_id);

    List<Borrow> getAllLoansByUserID(int user_id);

    boolean checkingDuplicateLoans(int user_id,int book_id);

    void imposeLateFee(int user_id,int book_id);
}
