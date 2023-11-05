package exceptions;

public class DuplicationEmailException extends RuntimeException{
    public DuplicationEmailException(String m){
        super(m);
    }
}
