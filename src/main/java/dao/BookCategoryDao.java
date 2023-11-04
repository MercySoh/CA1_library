package dao;

import business.BookCategory;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class BookCategoryDao extends Dao implements BookCategoryDaoInterface{

    public BookCategoryDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean addBookCatergory(BookCategory newBookCategory) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean added = false;

        try {
            con = getConnection();

            String query = "INSERT INTO bookcategory VALUES (?, ?, ?)";

            ps = con.prepareStatement(query);

            //ps.setInt(1, newBookCategory.getId());
            ps.setInt(2, newBookCategory.getBook_id());
            ps.setInt(3, newBookCategory.getCategory_id());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                added = true;
            }
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Integrity constraint failed in the addBookCategory() method: " + e.getMessage());
            added = false;
        } catch (SQLException e) {
            System.out.println("Exception occurred in the addBookCategory() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the addBookCategory() method: \n\t" +  e.getMessage());
            }
        }
        return added;
    }

    @Override
    public List<BookCategory> getAllBookCategory() throws DaoException {
        return null;
    }

    @Override
    public BookCategory getBookCategoryByBookId(int bookId) throws DaoException {
        return null;
    }

    @Override
    public BookCategory getBookCategoryByCategoryId(int categoryId) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteBookCategory(int bookId) throws DaoException {
        return false;
    }
}
