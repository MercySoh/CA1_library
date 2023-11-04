package dao;

import business.Book;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class BookDao extends Dao implements BookDaoInterface  {

    public BookDao(String databaseName) {super(databaseName);}


    @Override
    public boolean addBook(Book newbook) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        boolean added = false;

        try {
            con = getConnection();

            String query = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?,?,?)";

            ps = con.prepareStatement(query);

            //ps.setInt(1, newbook.getId());
            ps.setString(2, newbook.getTitle());
            ps.setString(3, newbook.getAuthor());
            ps.setInt(4, newbook.getISBN());
            ps.setDate(5, newbook.getPublication_date());
            ps.setInt(6, newbook.getQty());
            ps.setString(7, newbook.getDescription());
            ps.setInt(8,newbook.getCopy_qty());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                added = true;
            }
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Integrity constraint failed in the addBook() method: " + e.getMessage());
            added = false;
        } catch (SQLException e) {
            System.out.println("Exception occurred in the addBook() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the addOrder() method: \n\t" +  e.getMessage());
            }
        }
        return added;

    }

    @Override
    public List<Book> getAllBook() throws DaoException {
        return null;
    }

    @Override
    public Book getBookByTitle(String title) throws DaoException {
        return null;
    }

    @Override
    public int increaseCopyStock(int increaseAmount) throws DaoException {
        return 0;
    }

    @Override
    public int decreaseCopyStock(int decreaseAmount) throws DaoException {
        return 0;
    }
}
