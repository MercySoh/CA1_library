package dao;

import business.Book;
import business.Borrow;
import exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Conor
 */

public class BorrowDao extends Dao implements BorrowDaoInterface {
    // Declaring the Book and User DTO for use for specific
    // operations during a query such as update, or get
    //
    private BookDao bookDao;
    private UsersDao userDao;

    public BorrowDao(String dBName) {
        super(dBName);
        bookDao = new BookDao(dBName);
        userDao = new UsersDao(dBName);
    }

    /**
     * This method allows the user to borrow a book,
     * within 4 weeks, this will decrease the quantity of the books
     * available copies
     *
     * @param user_id the user ID supplied of the individual borrowing the book
     * @param book_id the book ID supplied of the books identifier
     * @return the rows affected in the Borrow table, 1 is for if successful and 0 is when
     * there is failure
     */
    @Override
    public int bookBorrow(int user_id, int book_id) throws DaoException {
        // Checking to ensure there are no duplicate loans
        Book bk = bookDao.getBookById(book_id);
        int quantityCheck = bk.getQty() - 1;

        if (quantityCheck < 0 || checkingDuplicateLoans(user_id, book_id)) {
            return 0;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // declaring the variable rowsAffected
        int rowsAffected ;

        LocalDate issued_date = LocalDate.now();

        // the due date of each book is
        // 4 weeks
        LocalDate due_date = LocalDate.now().plusWeeks(4);

        try {
            con = getConnection();

            String query = "INSERT INTO borrow(user_id, book_id, issued_date,due_date) VALUES (?, ?, ?, ?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, user_id);
            ps.setInt(2, book_id);
            ps.setDate(3, Date.valueOf(issued_date));
            ps.setDate(4, Date.valueOf(due_date));

            rowsAffected = ps.executeUpdate();
            bookDao.decreaseCopyStock(1, bookDao.getBookById(book_id).getTitle());

        } catch (SQLException e) {
            System.err.println("A problem occurred during the bookBorrow method:");
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
                System.err.println("A problem occurred when closing down the bookBorrow method:\n" + e.getMessage());
            }
        }
        return rowsAffected;
    }

    /**
     * This method allows the user to return the borrowed book,
     * there is a check, whether to impose a fine if late, also
     * the books quantity increase as its returned
     *
     * @param user_id the user ID supplied of the individual returning the book
     * @param book_id the book ID supplied of the book being returned
     * @return the rows affected in the Borrow table, 1 is for if successful and 0 is when
     * there is failure
     */
    @Override
    public int returnBook(int user_id, int book_id) throws DaoException {
        // Checking to ensure there the parameters supplied
        // are in the database
        if (bookDao.getBookById(book_id) == null || userDao.findUserByUserID(user_id) == null) {
            return 0;
        }
        Connection con = null;
        PreparedStatement ps = null;

        // declaring the variable rowsAffected
        int rowsAffected = 0;

        try {
            con = getConnection();

            // This string will invoke an update query which will return the borrowed book
            String query = "UPDATE borrow SET return_date=? where user_id=? AND book_id=? ";

            ps = con.prepareStatement(query);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, user_id);
            ps.setInt(3, book_id);

            rowsAffected = ps.executeUpdate();
            bookDao.increaseCopyStock(1, bookDao.getBookById(book_id).getTitle());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.err.println("A problem occurred during the return of the book:");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when freeing the connection" + e.getMessage());
            }
        }
        return rowsAffected;

    }

    /**
     * This method allows the user to view their current loans of books that they
     * have currently borrowed
     *
     * @param user_id the user ID supplied of the individual who holds the loans
     * @return A List containing the active User Loans, in which they have borrowed a book
     */
    @Override
    public List<Borrow> getAllCurrentLoansByUserID(int user_id) {
        // Checking to ensure there the parameter user_id supplied
        // is present in the database
        if (userDao.findUserByUserID(user_id) == null) {
            return null;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Borrow> currentLoans = new ArrayList<>();

        try {
            String query = "SELECT * FROM borrow WHERE user_id= ? and issued_date<due_date and return_date is null";
            con = this.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int bookID = rs.getInt("book_id");
                LocalDate issued = rs.getDate("issued_date").toLocalDate();
                LocalDate due = rs.getDate("due_date").toLocalDate();
                LocalDate return_date = rs.getDate("return_date").toLocalDate();
                int fine = rs.getInt("fine");
                currentLoans.add(new Borrow(id, user_id, bookID, issued, due, return_date, fine));

            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the getAllLoansByUserID method:");
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
                System.err.println("A problem occurred when closing down the getAllLoansByUserID() method");
                System.out.println(e.getMessage());
            }
        }
        return currentLoans;
    }

    /**
     * This method allows the user to view their history loans of books that they
     * have borrowed past and present
     *
     * @param user_id the user ID supplied of the individual who holds the loans
     * @return A List containing a history of User Loans
     */
    @Override
    public List<Borrow> getAllLoansByUserID(int user_id) {

        // Checking to ensure there the parameter user_id supplied
        // is present in the database
        if (userDao.findUserByUserID(user_id) == null) {
            return null;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Borrow> loansByUserID = new ArrayList<>();

        try {

            con = this.getConnection();

            String query = "SELECT * FROM borrow WHERE user_id= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int bookID = rs.getInt("book_id");
                LocalDate issuedDate = rs.getDate("issued_date").toLocalDate();
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
                int fine = rs.getInt("fine");

                Borrow b = new Borrow(id, user_id, bookID, issuedDate, dueDate, returnDate, fine);
                loansByUserID.add(b);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the getAllLoansByUserID method:");
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
                System.err.println("A problem occurred when closing down the getAllLoansByUserID() method");
                System.out.println(e.getMessage());
            }
        }
        return loansByUserID;
    }

    /**
     * The method will check if the loan the user is taking already exists,
     * this is used in the borrowBook method to verify if there is a loan present
     * ensuring that only one book is borrowed
     *
     * @param user_id the user ID supplied of the individual who is borrowing the book
     * @param book_id the book ID supplied of the book, to check whether it exists or not
     * @return true or false whether a loan exist or not
     */
    @Override
    public boolean checkingDuplicateLoans(int user_id, int book_id) {
        boolean isPresent = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            String query = "SELECT count(*) from borrow where user_id=? and book_id=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setInt(2, book_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int bookCount = rs.getInt(1);
                isPresent = bookCount > 0;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("	A problem occurred during the getAllLoansByUserID method:");
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
                System.err.println("A problem occurred when closing down the checkingDuplicateLoans() method");
                System.out.println(e.getMessage());
            }
        }
        return isPresent;
    }

    /**
     * The method will check if the book that has been returned late or on time,
     * this will decide if a late fee is to be imposed on the user or not
     *
     * @param user_id the user ID supplied of the individual who has borrowed the book
     * @param book_id the book ID supplied of the book which has been borrowed
     * @void, there is no return
     */
    @Override
    public void imposeLateFee(int user_id, int book_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            String query = "SELECT * from borrow where user_id=? and book_id=? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setInt(2, book_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
                if (returnDate.isAfter(dueDate)) {
                    int noOfDays = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
                    System.out.println("There is a late return for " + noOfDays + " fees will be imposed");
                    updateFine(user_id, book_id, imposeFine(noOfDays));
                    System.out.println("Outstanding late fee of + ");
                } else {
                    System.out.println("No outstanding fees in place");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("	A problem occurred during the checkingDuplicateLoans() method:");
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
                System.err.println("A problem occurred when closing down the checkingDuplicateLoans() method");
                System.out.println(e.getMessage());
            }
        }


    }

    /*
    Creating a imposing fine method
     */
    private int imposeFine(int days) {
        int fineDaily = 1;
        return days * fineDaily;
    }

    /*
    Updating the fine in the borrow table to be used in the
    payment table
    */
    private void updateFine(int user_id, int book_id, int fine) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE borrow SET fine=? WHERE user_id= ? and book_id=?";
            con = this.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, fine);
            ps.setInt(2, user_id);
            ps.setInt(3, book_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the updateFine method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the updateFine() method");
                System.out.println(e.getMessage());
            }
        }
    }

}
