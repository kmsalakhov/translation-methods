package Parser;

import LexicalAnalyzer.KotlinTokens;

public class KotlinParseException extends ParseException {
    public KotlinParseException(KotlinTokens expected, KotlinTokens actual) {
        super("expected: %s, actual: %s".formatted(expected, actual));
    }
}
