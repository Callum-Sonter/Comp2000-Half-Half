import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class School extends Building implements PopulationObserver {

    // ---------- Observer pattern: subject side ----------
    private final List<PopulationObserver> observers = new ArrayList<>();

    // ---------- Immutable fields (safe for parallel streams) ----------
    private final int students;
    private final int teachers;
    private final String name;

    // ---------- Default constructor used by Main ----------
    // Main does:  School test4 = new School();
    //             w.place(6, 8, test4);
    public School() {
        this(new Builder());
    }

    // ---------- Builder pattern: private constructor ----------
    private School(Builder b) {
        this.name     = b.name;
        this.students = b.students;
        this.teachers = b.teachers;
        setX(b.x);
        setY(b.y);
    }

    // ---------- Builder pattern: nested Builder ----------
    public static class Builder {
        private String name   = "School";
        private int students  = 600;
        private int teachers  = 100;
        private int x = 0, y = 0;

        public Builder name(String n)   { this.name = n;     return this; }
        public Builder students(int s)  { this.students = s; return this; }
        public Builder teachers(int t)  { this.teachers = t; return this; }
        public Builder at(int x, int y) { this.x = x; this.y = y; return this; }

        public School build() {
            if (students < 0 || teachers < 0)
                throw new IllegalStateException("Negative population");
            return new School(this);
        }
    }

    // ---------- Convenience factory (optional) ----------
    public static School create(int x, int y, int students, int teachers) {
        return new Builder()
                .name("School #" + x + "-" + y)
                .students(students)
                .teachers(teachers)
                .at(x, y)
                .build();
    }

    // ---------- Observer pattern ----------
    public void addObserver(PopulationObserver o)    { observers.add(o); }
    public void removeObserver(PopulationObserver o) { observers.remove(o); }

    private void notifyObservers() {
        int pop = getPopulation();
        for (PopulationObserver o : observers) o.onPopulationChanged(this, pop);
    }

    @Override
    public void onPopulationChanged(Building source, int newPopulation) {
        if (newPopulation > getPopulation()) {
            System.out.println(name + ": city grew to " + newPopulation
                    + ", considering expansion.");
        }
    }

    // ---------- Getters ----------
    public int getStudents() { return students; }
    public int getTeachers() { return teachers; }
    public String getName()  { return name; }

    @Override
    public int getPopulation() { return students + teachers; }

    // ---------- Rendering (Template Method override) ----------
    @Override
    public void build(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(128, 0, 128));          // purple = school
        g2d.fillRect((x * 50) + 7, (y * 50) + 7, 35, 35);
    }
}