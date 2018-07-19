package learn.classloader;


import java.util.HashMap;
import java.util.Map;

public class ManagerFactory {

    //记录热加载类的加载信息
    private static final Map<String ,LoadInfo> loadTimeMap = new HashMap<String ,LoadInfo>();

    public static final String CLASS_PATH = "";

    public static final String  MY_MANAGER = "com.imooc.classloader.MyManager";
}
