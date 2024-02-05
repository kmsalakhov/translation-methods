package Parser;

public class ParseException extends Exception {
    private final String message;

    public ParseException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "%s: %s%n".formatted(super.toString(), message);
    }
}
