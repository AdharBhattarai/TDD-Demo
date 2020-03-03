import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTemplateParse {

    private List<String> parse(String template) {
        return new TemplateParse().parse(template);
    }

    private void assertSegments(List<? extends Object> actual, Object... expected) {
        assertEquals("Number of segments doesn't match.", expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void emptyTemplateRendersAsEmptyString() throws Exception {
        List<String> segments = parse("");
        assertSegments(segments, "");
    }

    @Test
    public void templateWithOnlyPlainTest() throws Exception {
        List<String> segments = parse("plain text only");
        assertSegments(segments, "plain text only");
    }

    @Test
    public void parsingMultipleVariables() throws Exception {
        List<String> segments = parse("${a}:${b}:${c}");
        assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
    }
    @Test

    public void parsingTemplateIntoSegmentO
}
