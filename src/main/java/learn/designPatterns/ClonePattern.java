package learn.designPatterns;

public class ClonePattern {

    public static void main(String[] args) {
        Student s = new Student();
        s.setAge(20);
        s.setUsername("lilei");

        Student s1 = s.clone();
        System.out.println(s1.getUsername());
    }
}
