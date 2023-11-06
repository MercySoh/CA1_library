package dao;

import business.Category;
import exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mercy
 */

public class CategoryDaoTest {

    /**
     * Test of addCatergory() method, of class CategoryDao.
     */
    @Test
    public void addCatergory() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("addCategory with 1 arguments");
        String catName = "TestCategoryName";

        int result = categoryDao.addCatergory(catName);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = categoryDao.deleteCategory(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    /**
     * Test of addCategory_AddFail() method, of class BookDao.
     */
    @Test
    public void addCategory_AddFail() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("addCategory with 1 arguments failed");
        String catName = "TestCategoryName";

        int expResult = -1;
        int result = categoryDao.addCatergory(catName);
        assertEquals(expResult, result);

        if (result == -1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "has NOT changed by trying to remove what was added and failing");
            int rowsDeleted = categoryDao.deleteCategory(result);
            assertEquals(rowsDeleted, 0);
        }
    }

    /**
     * Test of getAllCategory() method, of class CategoryDao.
     */
    @Test
    public void getAllCategory() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("get All Category");
        ArrayList<Category> result = (ArrayList<Category>) categoryDao.getAllCategory();
        assertEquals(9, result.size());
    }

    /**
     * Test of getCategoryById() method, of class CategoryDao.
     */
    @Test
    public void getCategoryById() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("get Category By Id");
        int id = 3;

        Category expResult = new Category(3,"Fashion");
        Category result = categoryDao.getCategoryById(id);
        assertEquals(expResult, result);
    }


    /**
     * Test of getCategoryById_NotFound() method, of class CategoryDao.
     */
    @Test
    public void getCategoryById_NotFound() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("get Category By Id Not Found");
        int id = 15;

        Category expResult = null;
        Category result = categoryDao.getCategoryById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCategory() method, of class CategoryDao.
     */
    @Test
    public void deleteCategory() throws DaoException {
        CategoryDao categoryDao = new CategoryDao("testlibrary");
        System.out.println("delete Category By Id");
        Category c = new Category(6,"Food");

        int id = c.getId();
        int expResult = 1;

        int result = categoryDao.deleteCategory(id);
        assertEquals(expResult, result);

        if (result == 1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "changed by trying to select what was deleted");
            Category selectedCategory = categoryDao.getCategoryById(c.getId());
            assertEquals(null, selectedCategory);

            if (selectedCategory == null) {
                categoryDao.addCatergory(c);
            }
        }
    }
}