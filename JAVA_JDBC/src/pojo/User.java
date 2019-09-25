package pojo;

public class User {
    private int uid;
    private String uname;
    private String psw;
    private int age;
    private String sex;
    private int role;

    public User(int uid, String uname, String psw, int age, String sex, int role) {
        this.uid = uid;
        this.uname = uname;
        this.psw = psw;
        this.age = age;
        this.sex = sex;
        this.role = role;
    }

    public User() {
    }

    public User(String uname, String psw, int age) {
        this.uname = uname;
        this.psw = psw;
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public int getRole() {
        return role;
    }

    public User(int uid, String uname, String psw, int age) {
        this.uid = uid;

        this.uname = uname;
        this.psw = psw;
        this.age = age;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", psw='" + psw + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", role=" + role +
                '}';
    }
}
