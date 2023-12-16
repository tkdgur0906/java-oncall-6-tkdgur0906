package oncall.domain;

public class Worker {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Worker of(String name) {
        return new Worker(name);
    }
}
