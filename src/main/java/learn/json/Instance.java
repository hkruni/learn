package learn.json;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class Instance {

    private String name;

//  @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)   //null输出为null
//  @JSONField(serialzeFeatures=SerializerFeature.WriteNullStringAsEmpty) //null输出为""
    private String address;

    //@JSONField(serialzeFeatures=SerializerFeature.WriteNullNumberAsZero)
    //@JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer age;

    @JSONField(serialzeFeatures=SerializerFeature.WriteNullNumberAsZero)
    private Double score;

    @JSONField(format = "yyyy年MM月dd日" )
    private Date birthDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Instance() {
    }

    public Instance(String name, String address, Integer age, Double score, Date birthDay) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.score = score;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", birthDay=" + birthDay +
                '}';
    }
}
