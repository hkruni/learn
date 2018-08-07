package learn.reflection.classLoader;


public class MyClassloader extends ClassLoader {


    //要加载的java类的classpath路径
    private String classpath;

    protected MyClassloader(String  classpath) {
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }


}
