package dao;

import business.Payment;
import exceptions.DaoException;

import java.util.List;

/**
 * @author Conor
 */
public interface PaymentDaoInterface {
    /**
     * This method allows the user to view their history loans of payments that they
     * have paid
     *
     * @param user_id the user ID supplied of the individual who holds the payments
     * @return A List containing a history of User payments
     */
    List<Payment> getPaymentHistoryFromUserID(int user_id);

    /**
     * This method will the ensure the User makes their payment
     * for any outstanding late fee for borrowing a book
     *
     * @param user_id     the users user_id
     * @param book_id     the users book_id that they borrowed
     * @param fine        the fine that has to be paid
     * @param card_number the card number used to make the payment
     * @param expiry_date the cards expiry date
     * @return the updated row of the added payment
     */
    public int makePayment(int user_id, int book_id, int fine, String card_number, String expiry_date) throws DaoException;


}
