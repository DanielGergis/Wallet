public class InsufficientFundException extends RuntimeException {

    private String message;
 
    public InsufficientFundException(String message) {
        this.message = message;
    }
 
    public InsufficientFundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
    public InsufficientFundException(Throwable cause, double balance, double withdrawalAmount) {
        super(cause);
        this.message =  "Current balance " + balance+" is less than requested amount " + withdrawalAmount;
    }
 
    public String getMessage() {
        return message;
    }
 
}