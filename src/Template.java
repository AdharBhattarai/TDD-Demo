import javafx.css.Match;

import java.util.HashMap;
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
        String result = replaceVariables();
        checkForMissingValues(result);
        return result;
    }

    private String replaceVariables() {
        String result = templateText;
        for (Entry<String, String> entry : variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";

            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    private void checkForMissingValues(String result) {
        Matcher m = Pattern.compile("\\$\\{.+\\}").matcher(result);
        if (m.find()) {
            throw new MissingValueException("No value for " + m.group());
        }
    }

    public class MissingValueException extends RuntimeException {
        public MissingValueException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        Template temp = new Template("hi");
        temp.set("one", "${one}");
        temp.set("two", "${three}");
        temp.set("three", "${two}");
        temp.evaluate();
    }
}
