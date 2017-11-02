package pl.sszwaczyk.carmanagement.application.domain.exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(String s) {
        super(s);
    }
}
