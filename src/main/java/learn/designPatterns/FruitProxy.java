package learn.designPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FruitProxy implements InvocationHandler {

    //目标对象
    private Object subject;

    public Object bind(Object subject) {
        this.subject = subject;

        //通过反射机制，创建一个代理类对象实例并返回。用户进行方法调用时使用
        //创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
        return Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("先准备一下");

        method.invoke(subject,args);

        System.out.println("处理完毕");
        return null;
    }

    public static void main(String[] args) {

        Fruit fruit = new Apple();
        FruitProxy proxy = new FruitProxy();
        Fruit f = (Fruit) proxy.bind(fruit);
        f.eat("小明");
    }
}
