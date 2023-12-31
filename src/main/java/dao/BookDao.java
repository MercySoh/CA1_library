package dao;

import business.Book;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mercy
 */
public class BookDao extends Dao implements BookDaoInterface  {

    public BookDao(String databaseName) {super(databaseName);}

    /**
     * addBook(with 7 args) method allows admin/staff to add a new book,
     *
     * @param title a string of book's title
     * @param author a string of book's author
     * @param ISBN an int(10) of book's ISBN
     * @param publication_date a date of book's publication date
     * @param qty an int of book's stock
     * @param description a string of book's description
     * @param copy_qty an int of copy of book's stock
     * @return new book id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addBook(String title, String author, int ISBN, Date publication_date, int qty, String description, int copy_qty) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newId = -1;
        try {
            con = this.getConnection();

            String query = "INSERT INTO book(title,author,ISBN,publication_date,qty,description,copy_qty) VALUES (?, ?, ?, ?, ?, ?,?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, ISBN);
            ps.setDate(4, (java.sql.Date) publication_date);
            ps.setInt(5,qty);
            ps.setString(6,description);
            ps.setInt(7,copy_qty);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next())
            {
                newId = generatedKeys.getInt(1);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("\tA problem occurred during the addBook() method:" + "\t"+e.getMessage());
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
                throw new DaoException("A problem occurred when closing down the addBook() method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    /**
     * addBook(Book newbook) method allows admin/staff to add a new book,
     *
     * @param newbook the new <code>Book</code> to be added
     * @return new book id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addBook(Book newbook) throws DaoException {
        return addBook(newbook.getTitle(),newbook.getAuthor(),newbook.getISBN(),newbook.getPublication_date(),newbook.getQty(),newbook.getDescription(),newbook.getCopy_qty());
    }

    /**
     * getAllBook method return a list of all book in library,
     *
     * @return a list of all book in library
     * @throws DaoException if failure
     */
    @Override
    public List<Book> getAllBook() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<Book>();

        try{
            con = getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Book b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("ISBN"), rs.getDate("publication_date"), rs.getInt("qty"), rs.getString("description"), rs.getInt("copy_qty"));
                books.add(b);
            }
        }catch (SQLException e) {
            throw new DaoException("Exception occured in the getAllBook() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getAllBook() method: " + e.getMessage());
            }
        }

        return books;
    }

    /**
     * getBookByTitle method return the book that match the title,
     *
     * @param title a string of book's title to find
     * @return Book that match the title else return null if not found
     * @throws DaoException if failure
     */
    @Override
    public Book getBookByTitle(String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book b = null;

        try{
            con = getConnection();

            String query = "Select * from book where title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            //verify(ps).setString(1,"Look Inside Food");
            rs = ps.executeQuery();

            if(rs.next())
            {
                b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("ISBN"), rs.getDate("publication_date"), rs.getInt("qty"), rs.getString("description"), rs.getInt("copy_qty"));
            }
        }catch (SQLException e) {
            throw new DaoException("Exception occured in the getBookByTitle() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getBookByTitle() method: " + e.getMessage());
            }
        }
        return b;
    }

    /**
     * increaseCopyStock method will increase the copy_qty of the book's title when title match,
     *
     * @param increaseAmount an int of number to increase the stock of copy of book
     * @param title  a string of book's title to find
     * @return an int if increase success
     * @throws DaoException if failure
     */
    @Override
    public int increaseCopyStock(int increaseAmount, String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try{
            con = getConnection();

            String query = "UPDATE Book SET copy_qty = copy_qty + ? WHERE title = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, increaseAmount);
            ps.setString(2, title);

            rowsAffected = ps.executeUpdate();

        }catch (SQLException e) {
            throw new DaoException("Exception occured in the increaseCopyStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("Exception occured in the finally section of the increaseCopyStock() method" + e.getMessage());
            }
        }

        return rowsAffected;
    }

    /**
     * decreaseCopyStock method will decrease the copy_qty of the book's title when title match,
     *
     * @param decreaseAmount an int of number to decrease the stock of copy of book
     * @param title  a string of book's title to find
     * @return an int if decrease success
     * @throws DaoException if failure
     */
    @Override
    public int decreaseCopyStock(int decreaseAmount, String title) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try{
            con = getConnection();

            String query = "UPDATE Book SET copy_qty = copy_qty - ? WHERE title = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, decreaseAmount);
            ps.setString(2, title);

            rowsAffected = ps.executeUpdate();

        }catch (SQLException e) {
            throw new DaoException("Exception occured in the decreaseCopyStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("Exception occured in the finally section of the decreaseCopyStock() method" + e.getMessage());
            }
        }

        return rowsAffected;
    }

    /**
     * deleteBook method delete a book by book's id from the library.
     * @param bookId an int of book's id to be deleted.
     * @return 1 if deleted else return 0.
     */
    @Override
    public int deleteBook(int bookId) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();

            String query = "DELETE FROM book WHERE id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);

            rowsAffected = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("\tA problem occurred during the deleteBook() method:" + "\t" + e.getMessage());
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
                throw new DaoException("A problem occured when closing down the deleteBook() method:\n" + e.getMessage());
            }
        }
        return rowsAffected;
    }

    /**
     * getBookById method return the book that match the book id,
     *
     * @param bookId an int of book's id to find
     * @return Book that match the book's id else return null if not found
     * @throws DaoException if failure
     */
    @Override
    public Book getBookById(int bookId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book b = null;

        try{
            con = getConnection();

            String query = "Select * from book where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            //verify(ps).setString(1,"6");
            rs = ps.executeQuery();

            if(rs.next())
            {
                b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("ISBN"), rs.getDate("publication_date"), rs.getInt("qty"), rs.getString("description"), rs.getInt("copy_qty"));
            }
        }catch (SQLException e) {
            throw new DaoException("Exception occured in the getBookById() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getBookById() method: " + e.getMessage());
            }
        }
        return b;
    }

}
