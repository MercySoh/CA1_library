package dao;

import business.Payment;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author conor
 */
class PaymentDaoTest {

    /**
     * Getting Payment History based on the UserID,
     * under normal conditions
     */
    @Test
    void getPaymentHistoryFromUserID() {
        PaymentDao paymentDao = new PaymentDao("testlibrary");
        int user_id = 3;

        List<Payment> actualBorrow = paymentDao.getPaymentHistoryFromUserID(user_id);
        // Getting expected Payments
        List<Payment> expPayments = new ArrayList<>();
        expPayments.add(new Payment(2, 3,
                2,
                6,
                "4560789612308900",
                LocalDate.of(2027, 01, 20)
        ));
        assertEquals(expPayments, actualBorrow);
    }

    /**
     * This tests getPaymentHistoryFromUserID_NonExistentUser
     * where it is under abnormal conditions
     */
    @Test
    void getPaymentHistoryFromUserID_NonExistentUser() {
        PaymentDao paymentDao = new PaymentDao("testlibrary");

        List<Payment> expectedPay = new ArrayList<>();
        List<Payment> currentActual = paymentDao.getPaymentHistoryFromUserID(333);
        ;
        assertEquals(expectedPay, currentActual);
    }

    /**
     * This tests makePayment
     * where it is under normal conditions
     */
    @Test
    void makePayment() throws DaoException {
        PaymentDao paymentDao = new PaymentDao("testlibrary");
        int user_id = 3;

        int actual = paymentDao.makePayment(3,3,3,
                "4560789612308900", LocalDate.of(2027, 01, 20).toString() );

        assertEquals(1, actual);


        List<Payment> actualPayment = paymentDao.getPaymentHistoryFromUserID(user_id);

        List<Payment> expectedPayment = new ArrayList<>();
        expectedPayment.add(new Payment(2, 3,
                2,
                6,
                "4560789612308900",
                LocalDate.of(2027, 01, 20)));


        assertEquals(expectedPayment, actualPayment);
    }
}