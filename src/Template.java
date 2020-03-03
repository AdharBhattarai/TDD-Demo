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
        List<String> segments = parser.parse(templateText);
        return concatenate(segments);
    }

    private String concatenate(List<String> segments) {
        StringBuilder result = new StringBuilder();
        for (String segment : segments) {
            append(segment, result);
        }
        return result.toString();
    }

    private void append(String segment, StringBuilder result) {
        if (isVariable(segment)) {
            evaluateVariable(segment, result);
        } else {
            result.append(segment);
        }
    }

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }


    private void evaluateVariable(String segment, StringBuilder result) {
        String var = segment.substring(2, segment.length() - 1);
        if (!variables.containsKey(var)) {
            throw new MissingValueException("No value for " + segment);
        }
        result.append(variables.get(var));
    }


    public class MissingValueException extends RuntimeException {
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
