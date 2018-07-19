package learn.designPatterns;

public class Apple implements Fruit {



    @Override
    public void clean() {
        System.out.println("洗苹果");
    }

    @Override
    public void eat(String name) {
        System.out.println(name + "在吃苹果");
    }
}
