package business;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Conor
 */
public class Borrow {
    private int id;
    private int user_id;
    private int book_id;
    private LocalDate issued_date;
    private LocalDate due_date;
    private LocalDate return_date;
    private int fine;

    public Borrow(int id, int user_id, int book_id, LocalDate issued_date, LocalDate due_date, LocalDate return_date, int fine) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.issued_date = issued_date;
        this.due_date = due_date;
        this.return_date = return_date;
        this.fine = fine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getBookId() {
        return book_id;
    }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public LocalDate getIssuedDate() {
        return issued_date;
    }

    public void setIssuedDate(LocalDate issued_date) {
        this.issued_date = issued_date;
    }

    public LocalDate getDueDate() {
        return due_date;
    }

    public void setDueDate(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getReturnDate() {
        return return_date;
    }

    public void setReturnDate(LocalDate return_date) {
        this.return_date = return_date;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    /**
     * HashCode for Payment id
     *
     * @return hash which the id variable was used to generate a
     * HashCode
     */
    @Override
    public int hashCode() {
        int hash = 8;
        hash = 90 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * equals, used to compare Borrow Object values as a way to make valid comparisons
     * among the Borrow id
     *
     * @param obj, the Object used to compare Borrow id
     * instance variable
     *
     * @return the Borrow details with the instance variables above in a formatted
     *         manner
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Borrow other = (Borrow) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", issued_date=" + issued_date +
                ", due_date=" + due_date +
                ", return_date=" + return_date +
                ", fine=" + fine +
                '}';
    }
}
