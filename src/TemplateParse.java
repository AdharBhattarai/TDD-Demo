import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {

    public List<String> parse(String template) {
        List<String> segments = new ArrayList<String>();
        int index = collectSegments(segments, template, 0);
        addTail(segments, template, index);
//        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }

    public List<Segment> parseSegments(String template) {
        List<Segment> segments = new ArrayList<Segment>();
        List<String> strings = parse(template);
        for (String s : strings) {
            if (isVariable(s)) {
                String name = s.substring(2, s.length() - 1);
                segments.add(new Variable(name));
            } else {
                segments.add(new PlainText(s));
            }
        }
        return segments;
    }

    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }

    private int collectSegments(List<String> segs, String src, int index) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");        // Truncates \$ \{ neglects } * gets everything upto } ((**if not it takes the whole line. Right now it is taking only one variable at a time.
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            addPrecedingPlainText(segs, src, matcher, index);
            addVariable(segs, src, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addTail(List<String> segs, String template, int index) {
        if (index < template.length()) {
            segs.add(template.substring(index));
        }
    }

    private void addVariable(List<String> segs, String src, Matcher m) {
        segs.add(src.substring(m.start(), m.end()));
    }

    private void addPrecedingPlainText(List<String> segs, String src, Matcher m, int index) {
        if (index != m.start()) {
            segs.add(src.substring(index, m.start()));
        }
    }

//    private void addEmptyStringIfTemplateWasEmpty(List<String> segs) {
//        if (segs.isEmpty()) {
//            segs.add("");
//        }
//    }


    public static void main(String[] args) {
        TemplateParse templateParse = new TemplateParse();
        templateParse.parse("${a} : ${b}");
    }

}
