import javafx.css.Match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.util.Map.Entry;

public class Template {

    private Map<String, String> variables;
    private String templateText;


    public Template(String templateText) {   // Hello name
        this.variables = new HashMap<String, String>();
        this.templateText = templateText;
    }

    public void set(String name, String value) {        //  variable = name, value = Reader
        this.variables.put(name, value);                  // Reader
    }

    public String evaluate() {
        TemplateParse parser = new TemplateParse();
        List<Segment> segments = parser.parseSegments(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<Segment> segments) {
        StringBuilder result = new StringBuilder();
        for (Segment segment : segments) {
            result.append(segment.evaluate(variables));
        }
        return result.toString();
    }

    public static class MissingValueException extends RuntimeException {
        public MissingValueException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        Template temp = new Template("hi, ${adhar}");
        temp.set("adhar", "${one}");
        temp.evaluate();
    }
}
