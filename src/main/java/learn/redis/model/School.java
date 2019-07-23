package learn.redis.model;

import java.util.List;

public class School {

    private String name;

    private Integer number;

    private Boolean isHigh;

    private List<Student> studentList;

    private List<Teacher> teacherList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getHigh() {
        return isHigh;
    }

    public void setHigh(Boolean high) {
        isHigh = high;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", isHigh=" + isHigh +
                ", studentList=" + studentList +
                ", teacherList=" + teacherList +
                '}';
    }
}
