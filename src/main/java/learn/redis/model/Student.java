package learn.redis.model;

public class Student {

    private String sName;

    private Integer age;

    private String address;

    public Student() {
    }

    public Student(String sName, Integer age, String address) {
        this.sName = sName;
        this.age = age;
        this.address = address;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sName='" + sName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
