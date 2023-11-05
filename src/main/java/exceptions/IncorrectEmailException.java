package exceptions;

public class IncorrectEmailException extends RuntimeException{
    public IncorrectEmailException(String m) {
        super(m);

    }
}