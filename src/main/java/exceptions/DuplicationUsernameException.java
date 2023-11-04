package exceptions;

public class DuplicationUsernameException extends RuntimeException {
    public DuplicationUsernameException(String m){
        super(m);
    }
}
