package learn.designPatterns.abstact;

public class Child extends Father {


    @Override
    public void second() {
        System.out.println("second");
    }

    public static void main(String[] args) {
        Father f = new Child();
        f.first();
    }
}
