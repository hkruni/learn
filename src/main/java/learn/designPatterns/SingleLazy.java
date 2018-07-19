package learn.designPatterns;

public class SingleLazy {

    private static volatile SingleLazy singleLazy = null;

    private SingleLazy(){}

    public static SingleLazy getInstance() {

        if(singleLazy == null) {
            synchronized (SingleLazy.class) {
                if (singleLazy == null) {
                    singleLazy = new SingleLazy();
                }
            }
        }
        return singleLazy;
    }

}
