package LexicalAnalyzer;

import java.io.IOException;
import java.io.Reader;

public class KotlinAnalyzer implements LexicalAnalyzer<KotlinTokens> {
    private final Reader reader;

    private int charNow;
    private KotlinTokens token;

    public KotlinAnalyzer(Reader reader) {
        this.reader = reader;
        try {
            charNow = reader.read();
        } catch (IOException e) {
            charNow = -1;
        }
        nextToken();
    }

    @Override
    public void nextToken() {
        String tokenStr = parseString();

        if (tokenStr.isEmpty()) {
            token = KotlinTokens.END;
            return;
        }

        try {
            Integer.parseInt(tokenStr);
            token = KotlinTokens.NUMBER;
        } catch (NumberFormatException ignored) {
            token = switch (tokenStr) {
                case "val" -> KotlinTokens.VAL;
                case "var" -> KotlinTokens.VAR;
                case "Int" -> KotlinTokens.INT;
                case ";" -> KotlinTokens.SEMICOLON;
                case ":" -> KotlinTokens.COLON;
                case "=" -> KotlinTokens.EQ;
                default -> KotlinTokens.NAME;
            };
        }
    }

    @Override
    public KotlinTokens getToken() {
        return token;
    }

    private String parseString() {
        skipWhitespaces();
        StringBuilder sb = new StringBuilder();
        if (charNow == ';' || charNow == ':' || charNow == '=') {
            return String.valueOf((char) getChar());
        } else
            while (charNow != -1 && !Character.isWhitespace(charNow) && charNow != ';' && charNow != ':' && charNow != '=') {
                sb.append((char) charNow);
                getChar();
            }
        return sb.toString();
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(charNow)) {
            getChar();
        }
    }

    private int getChar() {
        int result = charNow;
        if (result != -1) {
            try {
                charNow = reader.read();
            } catch (IOException e) {
                charNow = -1;
            }
        }
        return result;
    }
}
