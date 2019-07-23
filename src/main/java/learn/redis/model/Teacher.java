package learn.redis.model;

public class Teacher {

    private String tName;

    private String course;

    private Integer sex;

    public Teacher(String tName, String course, Integer sex) {
        this.tName = tName;
        this.course = course;
        this.sex = sex;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tName='" + tName + '\'' +
                ", course='" + course + '\'' +
                ", sex=" + sex +
                '}';
    }
}
