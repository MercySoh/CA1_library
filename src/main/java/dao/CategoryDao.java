package dao;

import business.Book;
import business.Category;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends Dao implements CategoryDaoInterface{
    public CategoryDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean addCatergory(Category newCategory) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean added = false;

        try {
            con = getConnection();

            String query = "INSERT INTO category VALUES (?, ?)";

            ps = con.prepareStatement(query);

            //ps.setInt(1, newbook.getId());
            ps.setString(2, newCategory.getCategory_name());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                added = true;
            }
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println("Integrity constraint failed in the addCategory() method: " + e.getMessage());
            added = false;
        } catch (SQLException e) {
            System.out.println("Exception occurred in the addCategory() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the finally section of the addCategory() method: \n\t" +  e.getMessage());
            }
        }
        return added;
    }

    @Override
    public List<Category> getAllCategory() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> categories = new ArrayList<Category>();

        try{
            con = getConnection();

            String query = "Select * from category";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Category c = new Category(rs.getInt("id"), rs.getString("Category name"));
                categories.add(c);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllCategory() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllCategory() method: " + e.getMessage());
            }
        }

        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category c = null;

        try{
            con = getConnection();

            String query = "Select * from category where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);
            //verify(ps).setString(1,"2");
            rs = ps.executeQuery();

            if(rs.next())
            {
                c = new Category(rs.getInt("id"), rs.getString("category_name"));
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getCategoryById() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getCategoryById() method: " + e.getMessage());
            }
        }
        return c;
    }

    @Override
    public boolean deleteCategory(int bookId) throws DaoException {
        return false;
    }
}
