package learn.designPatterns.abstact;

public abstract class Father {

    public void first() {
        System.out.println("first");
        second();
    }

    public abstract void second();

}
