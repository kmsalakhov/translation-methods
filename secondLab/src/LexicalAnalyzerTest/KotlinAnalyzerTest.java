package LexicalAnalyzerTest;

import LexicalAnalyzer.KotlinAnalyzer;
import LexicalAnalyzer.KotlinTokens;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;

public class KotlinAnalyzerTest {
    @Test
    public void emptyTest() {
        spaceTest("", KotlinTokens.END);
    }

    @Test
    public void varTest() {
        spaceTest("var", KotlinTokens.VAR);
    }

    @Test
    public void valTest() {
        spaceTest("val", KotlinTokens.VAL);
    }

    @Test
    public void intTest() {
        spaceTest("Int", KotlinTokens.INT);
    }

    @Test
    public void fullTest() {
        KotlinAnalyzer kotlinAnalyzer = fromString("  val abracadabra: Int;   var kiddle: Int = 10 ; ");

        expected(kotlinAnalyzer, KotlinTokens.VAL);
        expected(kotlinAnalyzer, KotlinTokens.NAME);
        expected(kotlinAnalyzer, KotlinTokens.COLON);
        expected(kotlinAnalyzer, KotlinTokens.INT);
        expected(kotlinAnalyzer, KotlinTokens.SEMICOLON);
        expected(kotlinAnalyzer, KotlinTokens.VAR);
        expected(kotlinAnalyzer, KotlinTokens.NAME);
        expected(kotlinAnalyzer, KotlinTokens.COLON);
        expected(kotlinAnalyzer, KotlinTokens.INT);
        expected(kotlinAnalyzer, KotlinTokens.EQ);
        expected(kotlinAnalyzer, KotlinTokens.NUMBER);
        expected(kotlinAnalyzer, KotlinTokens.SEMICOLON);
        expected(kotlinAnalyzer, KotlinTokens.END);
    }

    private void expected(KotlinAnalyzer kotlinAnalyzer, KotlinTokens token) {
        Assert.assertEquals(token, kotlinAnalyzer.getToken());
        kotlinAnalyzer.nextToken();
    }

    private KotlinAnalyzer fromString(String s) {
        return new KotlinAnalyzer(new StringReader(s));
    }

    private void spaceTest(String str, KotlinTokens token) {
        Assert.assertEquals(token, fromString(str).getToken());
        Assert.assertEquals(token, fromString("     " + str).getToken());
        Assert.assertEquals(token, fromString(str + "    ").getToken());
        Assert.assertEquals(token, fromString("    " + str + "    ").getToken());
    }
}
