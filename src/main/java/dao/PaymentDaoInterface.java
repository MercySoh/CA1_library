package dao;

import business.Borrow;
import business.Payment;

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



}
