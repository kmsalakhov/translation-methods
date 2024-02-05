package Dot;

import java.io.IOException;

public interface DotGenerator<T> {
    void generateDot(T graph) throws IOException;
}
