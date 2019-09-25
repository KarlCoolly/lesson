package pojo;

public class Student {
    private String  StuId;
    private String  StuName;
    private int StuAge;
    private String StuSex;

    public Student() {
    }

    public Student(String stuId, String stuName, int stuAge, String stuSex) {
        StuId = stuId;
        StuName = stuName;
        StuAge = stuAge;
        StuSex = stuSex;
    }



    public String getStuId() {
        return StuId;
    }

    public void setStuId(String stuId) {
        StuId = stuId;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public int getStuAge() {
        return StuAge;
    }

    public void setStuAge(int stuAge) {
        StuAge = stuAge;
    }

    public String getStuSex() {
        return StuSex;
    }

    public void setStuSex(String stuSex) {
        StuSex = stuSex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StuId='" + StuId + '\'' +
                ", StuName='" + StuName + '\'' +
                ", StuAge=" + StuAge +
                ", StuSex='" + StuSex + '\'' +
                '}';
    }
}
