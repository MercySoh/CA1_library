package business;

import java.util.Date;
import java.util.Objects;

/**
 * @author Mercy
 */

public class Book {
    /*
   CREATE TABLE `book` (
  `id` int(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `ISBN` int(255) NOT NULL,
  `publication_date` date NOT NULL,
  `qty` int(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `copy_qty` int(255) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

     */
 private int id;
 private String title;
 private String author;
 private int ISBN;
 private Date publication_date;
 private int qty;
 private String description;
 private int copy_qty;

    public Book() {
        this.id = -1;
    }

    public Book(String title, String author, int ISBN, Date publication_date, int qty, String description, int copy_qty) {

        this.id = -1;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publication_date = publication_date;
        this.qty = qty;
        this.description = description;
        this.copy_qty = copy_qty;
    }

    public Book(int id, String title, String author, int ISBN, Date publication_date, int qty, String description, int copy_qty) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publication_date = publication_date;
        this.qty = qty;
        this.description = description;
        this.copy_qty = copy_qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public java.sql.Date getPublication_date() {
        return (java.sql.Date) publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCopy_qty() {
        return copy_qty;
    }

    public void setCopy_qty(int copy_qty) {
        this.copy_qty = copy_qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", publication_date=" + publication_date +
                ", qty=" + qty +
                ", description='" + description + '\'' +
                ", copy_qty=" + copy_qty +
                '}';
    }
}
