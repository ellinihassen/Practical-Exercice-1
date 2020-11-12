package exception;

public class OrderException extends Exception {
    public OrderException(String errorMessage){
        super(errorMessage);
    }
}
