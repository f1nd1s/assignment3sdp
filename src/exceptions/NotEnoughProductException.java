package exceptions;

public class NotEnoughProductException extends Exception {
    public NotEnoughProductException(String message) {
        super(message);
    }
}