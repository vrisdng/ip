package amiya.exception;

/**
 * Custom exception class for handling exceptions specific to the Amiya application.
 */
public class AmiyaException extends Exception {
    private String message;

    /**
     * Constructs a new AmiyaException with the specified detail message.
     *
     * @param message The detail message.
     */
    public AmiyaException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return The detail message string of this exception.
     */
    @Override
    public String getMessage() {
        return "I'm sorry, Doctor, but " + message;
    }
}