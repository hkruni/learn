package learn.reflection;

/**
 * Created by hukai on 2018/7/26.
 */
public class Member {


    private String  username;

    private String  password;

    private String  truename;

    private String  nickname;

    private String  gender;

    private int     age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return (username==null?"":"username:" + username) +

                (password==null?"":" password:" + password) +

                (truename==null?"":" truename:" + truename) +

                (nickname==null?"":" nickname:" + nickname) +

                (gender==null?"":" gender:" + gender) +

                " age:" + age;
    }

    public Integer getLength(String username,String password) {
        return username.length() + password.length() + this.username.length();
    }
}

