package dao;

import business.Category;
import exceptions.DaoException;

import java.util.List;

public interface CategoryDaoInterface {

    /**
     * addCatergory(with 1args) method allows admin/staff to add a new catergory,
     *
     * @param catergory_name a string of new category's name
     * @return new category id if added else return -1
     * @throws DaoException if failure
     */
    public int addCatergory(String catergory_name) throws DaoException;

    /**
     * addCatergory(Category newCategory) method allows admin/staff to add a new catergory,
     *
     * @param newCategory the new <code>Category</code> to be added
     * @return new category id if added else return -1
     * @throws DaoException if failure
     */
    public int addCatergory(Category newCategory) throws DaoException;

    /**
     * getAllCategory method return a list of all category in library,
     *
     * @return a list of all category in library
     * @throws DaoException if failure
     */
    public List<Category> getAllCategory() throws DaoException;

    /**
     * getCategoryById method return the category that match the category id,
     *
     * @param categoryId an int of category's id to find
     * @return Category that match the category's id else return null if not found
     * @throws DaoException if failure
     */
    public Category getCategoryById(int categoryId) throws DaoException;

    /**
     * deleteCategory method delete a category by category's id from the library.
     * @param categoryId an int of category's id to be deleted.
     * @return 1 if deleted else return 0.
     * @throws DaoException if failure
     */
    public int deleteCategory(int categoryId) throws DaoException;
}
