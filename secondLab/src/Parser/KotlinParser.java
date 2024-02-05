package Parser;

import LexicalAnalyzer.KotlinAnalyzer;
import LexicalAnalyzer.KotlinTokens;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class KotlinParser implements Parser<Tree> {
    private KotlinAnalyzer analyzer;

    public Tree parse(Reader reader) throws ParseException {
        analyzer = new KotlinAnalyzer(reader);
        return parseBlock();
    }

    private Tree parseBlock() throws ParseException {
        List<Tree> children = new ArrayList<>();
        while (analyzer.getToken() == KotlinTokens.VAL || analyzer.getToken() == KotlinTokens.VAR) {
            children.add(parseInit());
        }
        assertToken(KotlinTokens.END);
        return new Tree("BLOCK", children);
    }

    private Tree parseInit() throws ParseException {
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

    private Tree parseStartVal() throws ParseException {
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

    private void assertToken(KotlinTokens token) throws ParseException {
        if (analyzer.getToken() != token) {
            throw new KotlinParseException(token, analyzer.getToken());
        }
    }

    private Tree skipToken(KotlinTokens token) throws ParseException {
        assertToken(token);
        Tree result = new Tree(token.name());
        analyzer.nextToken();
        return result;
    }
}
