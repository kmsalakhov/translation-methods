package ParserTest;

import org.junit.Assert;
import org.junit.Test;
import Parser.*;

import java.io.*;

public class KotlinParserTest {
    private final KotlinParser kotlinParser = new KotlinParser();

    @Test
    public void emptyTest() throws ParseException {
        Assert.assertEquals(new Tree("BLOCK"), parse(""));
    }

    @Test
    public void failTestNoVar() {
        Assert.assertThrows(ParseException.class, () -> parse("No Var"));
    }

    @Test
    public void failTestNoSemicolon() {
        Assert.assertThrows(ParseException.class, () -> parse("val a; Int"));
    }

    @Test
    public void failTestNoValue() {
        Assert.assertThrows(ParseException.class, () -> parse("val a; Int = "));
    }

    @Test
    public void failTestNoName() {
        Assert.assertThrows(ParseException.class, () -> parse("val; Int"));
    }

    @Test
    public void fullTest() throws ParseException {
        Assert.assertEquals(
                "BLOCK INIT INIT_WORD VAL NAME COLON INT START_VAL SEMICOLON",
                kotlinParser.parse(fromString("val a: Int;")).toString()
        );
    }

    private Tree parse(String s) throws ParseException {
        return kotlinParser.parse(fromString(s));
    }

    private Reader fromString(String s) {
        return new StringReader(s);
    }
}
