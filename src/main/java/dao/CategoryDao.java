package dao;

import business.Category;
import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mercy
 */

public class CategoryDao extends Dao implements CategoryDaoInterface{
    public CategoryDao(String databaseName) {
        super(databaseName);
    }

    /**
     * addCatergory(with 1args) method allows admin/staff to add a new catergory,
     *
     * @param catergory_name a string of new category's name
     * @return new category id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addCatergory(String catergory_name) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newId = -1;
        try {
            con = this.getConnection();

            String query = "INSERT INTO category(category_name) VALUES (?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, catergory_name);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next())
            {
                newId = generatedKeys.getInt(1);
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("\tA problem occurred during the addCategory() method:" +"\t" +e.getMessage());
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
                throw new DaoException("A problem occurred when closing down the addCategory()" + " method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    /**
     * addCatergory(Category newCategory) method allows admin/staff to add a new catergory,
     *
     * @param newCategory the new <code>Category</code> to be added
     * @return new category id if added else return -1
     * @throws DaoException if failure
     */
    @Override
    public int addCatergory(Category newCategory) throws DaoException {
        return addCatergory(newCategory.getCategory_name());
    }

    /**
     * getAllCategory method return a list of all category in library,
     *
     * @return a list of all category in library
     * @throws DaoException if failure
     */
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
                Category c = new Category(rs.getInt("id"), rs.getString("category_name"));
                categories.add(c);
            }
        }catch (SQLException e) {
            throw new DaoException("Exception occured in the getAllCategory() method: " + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getAllCategory() method: " + e.getMessage());
            }
        }

        return categories;
    }

    /**
     * getCategoryById method return the category that match the category id,
     *
     * @param categoryId an int of category's id to find
     * @return Category that match the category's id else return null if not found
     * @throws DaoException if failure
     */
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
            throw new DaoException("Exception occured in the getCategoryById() method: " + e.getMessage() + e.getMessage());
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
                throw new DaoException("Exception occured in the finally section of the getCategoryById() method: " + e.getMessage());
            }
        }
        return c;
    }

    /**
     * deleteCategory method delete a category by category's id from the library.
     * @param categoryId an int of category's id to be deleted.
     * @return 1 if deleted else return 0.
     */
    @Override
    public int deleteCategory(int categoryId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();

            String query = "DELETE FROM category WHERE id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, categoryId);

            rowsAffected = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DaoException("A problem occurred during the deleteCategory() method:" + e.getMessage());
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
                throw new DaoException("A problem occured when closing down the  deleteCategory() method:\n" + e.getMessage());
            }
        }
        return rowsAffected;
    }


}
