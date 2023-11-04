package dao;

import business.BookCategory;
import exceptions.DaoException;

import java.util.List;

public interface BookCategoryDaoInterface {
    public boolean addBookCatergory(BookCategory newBookCategory) throws DaoException;

    public List<BookCategory> getAllBookCategory() throws DaoException;

    public BookCategory getBookCategoryByBookId(int bookId) throws DaoException;

    public BookCategory getBookCategoryByCategoryId(int categoryId) throws DaoException;

    public boolean deleteBookCategory(int bookId) throws DaoException;
}
