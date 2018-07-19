package learn.inner;


public class OuterClass {
    private String name ;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    //内部类对象持有外部类对象的引用，所以必须先创建外部类对象，再创建内部类对象
    public class InnerClass{

        private int x = 15;

        public InnerClass(){
            OuterClass.this.name = "chenssy";
            OuterClass.this.age = 23;
        }

        public void display(){
            System.out.println("name：" + OuterClass.this.getName() +"   ;age：" + OuterClass.this.getAge());
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
        innerClass.setX(10);
        System.out.println(innerClass.x);



    }
}