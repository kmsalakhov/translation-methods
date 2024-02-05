package ParserTest;

import org.junit.Assert;
import org.junit.Test;
import Parser.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KotlinParserTest {
    private final KotlinParser kotlinParser = new KotlinParser();

    @Test
    public void emptyTest() {
        Assert.assertEquals(new Tree("BLOCK"), kotlinParser.parse(fromString("")));
    }

    @Test
    public void fullTest() {
        Assert.assertEquals(
                "BLOCK INIT INIT_WORD VAL NAME COLON INT START_VAL SEMICOLON",
                kotlinParser.parse(fromString("val a: Int;")).toString()
        );
    }

    private InputStreamReader fromString(String s) {
        InputStream is = new ByteArrayInputStream(s.getBytes());
        return new InputStreamReader(is);
    }
}