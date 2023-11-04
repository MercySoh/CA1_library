package dao;

import business.Book;
import business.Category;
import exceptions.DaoException;

import java.util.List;

public interface CategoryDaoInterface {

    public boolean addCatergory(Category newCategory) throws DaoException;

    public List<Category> getAllCategory() throws DaoException;

    public Category getCategoryById(int categoryId) throws DaoException;

    public boolean deleteCategory(int bookId) throws DaoException;
}
