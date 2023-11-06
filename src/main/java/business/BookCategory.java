package business;

import java.util.Objects;

/**
 * @author Mercy
 */

public class BookCategory {
    /*
    CREATE TABLE `bookcategory` (
  `id` int(255) NOT NULL,
  `book_id` int(255) NOT NULL,
  `category_id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
     */

    private int id;
    private int book_id;
    private int category_id;

    public BookCategory() {
        this.id = -1;
    }

    public BookCategory(int book_id, int category_id) {
        this.id = -1;
        this.book_id = book_id;
        this.category_id = category_id;
    }

    public BookCategory(int id, int book_id, int category_id) {
        this.id = id;
        this.book_id = book_id;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategory that = (BookCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", category_id=" + category_id +
                '}';
    }
}
