//package learn.String;
//
//import java.lang.reflect.Field;
//
//public class InnerFinalTest {
//
//    private static Test test0 = null;
//
//    public static void main(String[] args) {
//        new InnerFinalTest().method1();
//        System.out.println("-------");
//        test0.test();
//    }
//
//    public void method1(){
//        final Test  test = new Test();
//        test0 = new Test(){
//            @Override
//            public void test(){
//                System.out.println("匿名内部类：" + test);
//                Field[] field = this.getClass().getDeclaredFields();
//                for (int i = 0; i < field.length; i++) {
//                    System.out.println(field[i].getName());
//                }
//            }
//        };
//
//        InnerFinalTest ift = new InnerFinalTest();
//        ift.innerFinalTest(test0);
//        System.out.println("外部直接访问变量："+ test);
//    }
//
//    public void innerFinalTest(Test test){
//        test.test();
//    }
//}
