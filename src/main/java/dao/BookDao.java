package dao;

import business.Book;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> products = new ArrayList<Book>();

        try{
            con = getConnection();

            String query = "Select * from book";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Book b = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("ISBN"), rs.getDate("publication_date"), rs.getInt("qty"), rs.getString("description"), rs.getInt("copy_qty"));
                products.add(b);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllBook() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllBook() method: " + e.getMessage());
            }
        }

        return products;
    }

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
            System.out.println("Exception occured in the getBookByTitle() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookByTitle() method: " + e.getMessage());
            }
        }
        return b;
    }

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
            System.out.println("Exception occured in the increaseCopyStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the increaseCopyStock() method");
                e.getMessage();
            }
        }

        return rowsAffected;
    }

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
            System.out.println("Exception occured in the decreaseCopyStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the decreaseCopyStock() method");
                e.getMessage();
            }
        }

        return rowsAffected;
    }

    @Override
    public boolean deleteBook(int bookId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean deleted = false;
        try {
            con = getConnection();

            String command = "DELETE FROM book WHERE ID=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, bookId);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected < 0){
                deleted = true;
            }

        } catch (SQLException e) {
            throw new DaoException("deleteBook: " + e.getMessage());
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
                throw new DaoException("deleteBook(): " + e.getMessage());
            }
        }
        return deleted;
    }

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
            System.out.println("Exception occured in the getBookById() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getBookById() method: " + e.getMessage());
            }
        }
        return b;
    }


}
