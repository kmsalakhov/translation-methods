package Parser;

import java.io.Reader;

public interface Parser<T> {
    T parse(Reader reader) throws ParseException;
}
