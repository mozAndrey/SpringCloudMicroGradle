package example.exception;

public class BillNoFoundException extends RuntimeException {
    public BillNoFoundException(String message) {
        super(message);
    }
}
