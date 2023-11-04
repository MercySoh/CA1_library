package exceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String m) {
        super(m);

    }
}
