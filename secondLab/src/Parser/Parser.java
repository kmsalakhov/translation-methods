package Parser;

import LexicalAnalyzer.KotlinAnalyzer;
import LexicalAnalyzer.KotlinTokens;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private KotlinAnalyzer analyzer;
    public Tree parse(InputStreamReader input) {
        analyzer = new KotlinAnalyzer(input);
        return parseBlock();
    }

    private Tree parseBlock() {
        List<Tree> children = new ArrayList<>();
        while (analyzer.getToken() == KotlinTokens.VAL || analyzer.getToken() == KotlinTokens.VAR) {
            children.add(parseInit());
        }
        return new Tree("BLOCK", children);
    }

    private Tree parseInit() {
        List<Tree> children = new ArrayList<>();
        children.add(parseInitWord());
        children.add(skipToken(KotlinTokens.NAME));
        children.add(skipToken(KotlinTokens.COLON));
        children.add(skipToken(KotlinTokens.INT));
        children.add(parseStartVal());
        children.add(skipToken(KotlinTokens.SEMICOLON));
        return new Tree("INIT", children);
    }

    private Tree parseInitWord() {
        Tree child = switch (analyzer.getToken()) {
            case VAL -> new Tree("VAL");
            case VAR -> new Tree("VAR");
            default -> throw new RuntimeException();
        };
        analyzer.nextToken();
        return new Tree("INIT_WORD", child);
    }

    private Tree parseStartVal() {
        List<Tree> children = new ArrayList<>();
        if (analyzer.getToken() == KotlinTokens.EQ) {
            children.add(skipToken(KotlinTokens.EQ));
            children.add(skipToken(KotlinTokens.NUMBER));
            return new Tree("START_VAL", children);
        } else {
            assertToken(KotlinTokens.SEMICOLON);
            return new Tree("START_VAL");
        }
    }

    private void assertToken(KotlinTokens token) {
        assert(analyzer.getToken() == token);
    }

    private Tree skipToken(KotlinTokens token) {
        assertToken(token);
        Tree result = new Tree(token.name());
        analyzer.nextToken();
        return result;
    }
}
