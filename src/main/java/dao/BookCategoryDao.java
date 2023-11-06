package dao;

import business.BookCategory;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDao extends Dao implements BookCategoryDaoInterface{

    public BookCategoryDao(String databaseName) {
        super(databaseName);
    }


    /**
     * addBookCatergory(with 2args) method allows admin/staff to add book to a new catergory,
     *
     * @param bookId an int of book's id to add to category
     * @param catId an int of category's id to be added in book
     * @return new bookCategory's id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addBookCatergory(int bookId, int catId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newId = -1;
        try {
            con = this.getConnection();

            String query = "INSERT INTO bookcategory(book_id,category_id) VALUES (?,?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, bookId);
            ps.setInt(2,catId);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next())
            {
                newId = generatedKeys.getInt(1);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("\tA problem occurred during the addBookCategory() method:" +"\t" +e.getMessage());
        }
        finally
        {
            try
            {
                if(generatedKeys != null){
                    generatedKeys.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("A problem occurred when closing down the addBookCategory()" + " method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    /**
     * addBookCatergory(BookCategory newBookCategory) method allows admin/staff to add a book to category,
     *
     * @param newBookCategory the new <code>BookCategory</code> to be added
     * @return new bookCategory's id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addBookCatergory(BookCategory newBookCategory) throws DaoException {
        return addBookCatergory(newBookCategory.getBook_id(),newBookCategory.getCategory_id());
    }

    /**
     * getAllBookCategory method return a list of all book in all category in library,
     *
     * @return a list of all book in all category in library
     * @throws DaoException if failure
     */
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
            throw new DaoException("Exception occured in the getAllBookCategory() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getAllBookCategory() method: " + e.getMessage());
            }
        }

        return bookCategories;
    }

    /**
     * getBookCategoryByBookId method return the bookCategory that match the book id,
     *
     * @param bookId an int of book's id to find
     * @return bookCategory that match the book's id else return null if not found
     * @throws DaoException if failure
     */

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
            throw new DaoException("Exception occured in the getBookCategoryByBookId() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getBookCategoryByBookId() method: " + e.getMessage());
            }
        }
        return bc;
    }

    /**
     * getBookCategoryByCategoryId method return the bookCategory that match the category id,
     *
     * @param categoryId an int of book's id to find
     * @return bookCategory that match the category's id else return null if not found
     * @throws DaoException if failure
     */
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
            throw new DaoException("Exception occured in the getBookCategoryByCategoryId() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getBookCategoryByCategoryId() method: " + e.getMessage());
            }
        }
        return bc;
    }

    /**
     * deleteBookCategory method delete a bookCategory by book's id from the library.
     * @param bookId an int of book's id to be deleted.
     * @return 1 if deleted else return 0.
     * @throws DaoException if failure
     */
    @Override
    public int deleteBookCategory(int bookId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();

            String query = "DELETE FROM bookcategory WHERE book_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);

            rowsAffected = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("A problem occurred during the deleteBookCategory() method:" + e.getMessage());
        }
        finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                throw new DaoException("A problem occured when closing down the deleteBookCategory() method:\n" + e.getMessage());
            }
        }
        return rowsAffected;
    }

}
