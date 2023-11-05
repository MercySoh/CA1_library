package business;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Conor
 */
public class Payment {
    private int id;
    private int user_id;
    private int book_id;
    private int fine;
    private String card_number;
    private LocalDate expiry_date;


    public Payment(int id,int user_id, int book_id, int fine, String card_number, LocalDate expiry_date) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.fine = fine;
        this.card_number = card_number;
        this.expiry_date = expiry_date;
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

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
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
     * equals, used to compare Payment Object values as a way to make valid comparisons
     * among the Payment id
     *
     * @param obj, the Object used to compare Payment id
     * instance variable
     *
     * @return the Payment details with the instance variables above in a formatted
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
        final Payment other = (Payment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", fine=" + fine +
                ", card_number='" + card_number + '\'' +
                ", expiry_date=" + expiry_date +
                '}';
    }
}
