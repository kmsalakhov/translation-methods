package LexicalAnalyzer;

public interface LexicalAnalyzer<T> {
    void nextToken();

    T getToken();
}
