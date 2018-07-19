package learn.classloader;


/**
 * 封装加载类的信息
 */
public class LoadInfo {

    private MyClassLoader myClassLoader;

    //记录要加载类的时间戳
    private long loadTime;

    private BaseManager baseManager;

    public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public BaseManager getBaseManager() {
        return baseManager;
    }

    public void setBaseManager(BaseManager baseManager) {
        this.baseManager = baseManager;
    }
}
