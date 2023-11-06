package dao;


import business.Payment;
import exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Conor
 */
public class PaymentDao extends Dao implements PaymentDaoInterface {


    private final UsersDao userDao;

    private final BookDao bookDao;

    public PaymentDao(String dBName) {
        super(dBName);
        userDao = new UsersDao(dBName);
        bookDao = new BookDao(dBName);
    }

    /**
     * This method allows the user to view their history loans of payments that they
     * have paid
     *
     * @param user_id the user ID supplied of the individual who holds the payments
     * @return A List containing a history of User payments
     */
    @Override
    public List<Payment> getPaymentHistoryFromUserID(int user_id) {
        // Checking to ensure there the parameter user_id supplied
        // is present in the database
        if (userDao.findUserByUserID(user_id) == null) {
            return null;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList<>();

        try {

            con = this.getConnection();

            String query = "SELECT * FROM payment WHERE user_id= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int bookID = rs.getInt("book_id");
                LocalDate expiry_date = rs.getDate("expiry_date").toLocalDate();
                String cardnumber = rs.getString("card_number");
                int fine = rs.getInt("fine");

                Payment b = new Payment(id, user_id, bookID, fine, cardnumber, expiry_date);
                payments.add(b);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the getPaymentHistoryFromUserID method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the getPaymentHistoryFromUserID() method");
                System.out.println(e.getMessage());
            }
        }
        return payments;
    }

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
    @Override
    public int makePayment(int user_id, int book_id, int fine, String card_number, String expiry_date) throws DaoException {
        if (userDao.findUserByUserID(user_id) == null || bookDao.getBookById(book_id) == null) {
            return -1;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int newId = -1;

        try {
            con = this.getConnection();

            String query = "INSERT INTO payment(user_id, book_id, fine, card_number,expiry_date) VALUES (?,?,?,?,?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, user_id);
            ps.setInt(2, book_id);
            ps.setInt(3, fine);
            ps.setString(4, card_number);
            ps.setString(5, expiry_date);

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                newId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the makePayment method:");
            System.err.println("\t" + e.getMessage());
            newId = -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the makePayment method:\n" + e.getMessage());
            }
        }
        return newId;
    }

}
