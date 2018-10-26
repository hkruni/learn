package learn.exception;

public class Outer {

    public static void main(String[] args) {
        try {
            InnerThrow i1 = new InnerThrow();
            InnerThrow2 i2 = new InnerThrow2();
            i1.tetst();
            i2.tetst();
        }catch (Exception e) {
            System.out.println("抛出异常了");
        }

    }
}
