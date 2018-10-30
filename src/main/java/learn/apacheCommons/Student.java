package learn.apacheCommons;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Student {

    private String name;

    private Integer age;

    private Integer result;

    private Date createTime;

    private List<Integer> types;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getResult() {
        return result;
    }


    public void setResult(Integer result) {
        this.result = result;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public Student() {

        super();
        // TODO Auto-generated constructor stub
    }


    public Student(String name, Integer age, Integer result, Date createTime) {
        this.name = name;
        this.age = age;
        this.result = result;
        this.createTime = createTime;
        this.types = types;
    }

    private Integer getAgeOne() {
        return  this.age + 1;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", result=" + result +
                ", createTime=" + createTime +
                ", types=" + types +
                '}';
    }



//    @Override
//    public int hashCode() {
//        return 1;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return true;
//    }

    public static void main(String[] args) {


    }

}
