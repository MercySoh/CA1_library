package dao;

import business.Book;
import business.BookCategory;
import business.Category;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BookCategory> bookCategories = new ArrayList<BookCategory>();

        try{
            con = getConnection();

            String query = "Select * from bookcategory";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                BookCategory bc = new BookCategory(rs.getInt("id"), rs.getInt("book_id"), rs.getInt("category_id"));
                bookCategories.add(bc);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllBookCategory() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBookCategory() method: " + e.getMessage());
            }
        }

        return bookCategories;
    }

    @Override
    public BookCategory getBookCategoryByBookId(int bookId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookCategory bc = null;

        try{
            con = getConnection();

            String query = "Select * from bookcategory where book_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            //verify(ps).setString(1,"6");
            rs = ps.executeQuery();

            if(rs.next())
            {
                bc = new BookCategory(rs.getInt("id"), rs.getInt("book_id"), rs.getInt("category_id"));
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getBookCategoryByBookId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookCategoryByBookId() method: " + e.getMessage());
            }
        }
        return bc;
    }

    @Override
    public BookCategory getBookCategoryByCategoryId(int categoryId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BookCategory bc = null;

        try{
            con = getConnection();

            String query = "Select * from bookcategory where category_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);
            //verify(ps).setString(1,"1");
            rs = ps.executeQuery();

            if(rs.next())
            {
                bc = new BookCategory(rs.getInt("id"), rs.getInt("book_id"), rs.getInt("category_id"));
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getBookCategoryByCategoryId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookCategoryByCategoryId() method: " + e.getMessage());
            }
        }
        return bc;
    }

    @Override
    public boolean deleteBookCategory(int bookId) throws DaoException {
        return false;
    }
}
