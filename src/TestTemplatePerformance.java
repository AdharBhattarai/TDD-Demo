import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplatePerformance {
    private Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}, ${four}, ${five}");
        template.set("one", "askjdsalkhdalsadads");
        template.set("two", "kjndsalkadsasdasdas");
        template.set("three", "dsaljksdlajsdasdasda");
        template.set("four", "asdsalkdjalksjdalksd");
        template.set("five", "abcdefgjijklmnop");

    }

    @Test
    public void templateWith100WordsAnd20Variables() throws Exception {
        long expected = 20L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time + " ms while the target was " + expected + " ms.", time <= expected);

    }

}
