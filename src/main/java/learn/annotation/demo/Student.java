package learn.annotation.demo;


import learn.annotation.face.MethonAnnoatation;

public class Student {


    private int id;
    private String name;
    private int  result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @MethonAnnoatation(name="name1")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Student(int id, String name, int result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", result=" + result +
                '}';
    }
}
