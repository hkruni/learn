package learn.json.jackson;

import java.util.Date;

/**
 * Created by geely
 */
public class TestPojo {
    private String name;
    private Integer id;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestPojo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", date=" + date +
                '}';
    }
}
