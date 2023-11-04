package dao;

import business.Category;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        return null;
    }

    @Override
    public Category getCategoryById(int categoryId) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteCategory(int bookId) throws DaoException {
        return false;
    }
}
