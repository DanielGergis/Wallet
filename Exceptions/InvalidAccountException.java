public class InvalidAccountException extends RuntimeException{

    private String message;
    
    public InvalidAccountException(String message) {
        this.message = message;
    }
 
    public InvalidAccountException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
    public InvalidAccountException(Throwable cause, int accountID) {
        super(cause);
        this.message =  "Invalid Account ID: " +accountID;
    }
 
    public String getMessage() {
        return message;
    }
}
 