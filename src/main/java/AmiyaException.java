public class AmiyaException extends Exception {
    private String message;

    public AmiyaException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "I'm sorry, Doctor, but " + message;
    }
}