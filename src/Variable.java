public class Variable implements Segment {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public boolean equals(Object other) {
        return name.equals(((Variable) other).name);
    }
}