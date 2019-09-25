package Server;

import pojo.Student;
import test.MyJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService2 {

    /**
     * 查询所有的学生
     *
     * @return
     */
    public static List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();
        //获得连接
        Connection connection = MyJdbc.getConnection();
        //sql语句
        String sql = "SELECT * FROM tblstudent ";
        //预处理对象
        PreparedStatement pst = null;
        //result结果集
        ResultSet rest = null;
        try {
            pst = connection.prepareStatement(sql);
            rest = pst.executeQuery();
            while (rest.next()) {
                //将读入的数据放入一个学生类实例对象中
                Student student = new Student(
                        rest.getString(1),
                        rest.getString(2),
                        rest.getInt(3),
                        rest.getString(4)
                );
                //将学生类放入集合
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJdbc.closeAll(rest, pst, connection);
        }
        return list;
    }

    /**
     * 查询一个学生
     *
     * @return
     */
    public static Student getStudent(String Stuid) {
        Student student = null;
        //获得连接
        Connection connection = MyJdbc.getConnection();
        //sql语句
        String sql = " SELECT * FROM tblstudent s WHERE s.`StuId` = ?";
        //预处理对象
        PreparedStatement pst = null;
        //result结果集
        ResultSet rest = null;

        try {
            //执行sql语句的对象statement
            pst = connection.prepareStatement(sql);
            //设置id
            pst.setString(1, Stuid);
            //得到sql语句的返回值
            rest = pst.executeQuery();
            if (rest.next()) {
                //将读入的数据放入一个学生类实例对象中
                student = new Student(
                        rest.getString(1),
                        rest.getString(2),
                        rest.getInt(3),
                        rest.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJdbc.closeAll(rest, pst, connection);
        }
        return student;
    }

    /**
     * 模糊查询学生
     *
     * @return
     */
    public  List<Student> getStudentLike(String str) {
        List<Student> list = new ArrayList<>();
        //获得连接
        Connection connection = MyJdbc.getConnection();
        //sql语句
        String sql = "select * from tblstudent where stuName like ?";
        //预处理对象
        PreparedStatement pst = null;
        //result结果集
        ResultSet rest = null;

        try {
            //执行sql语句的对象statement
            pst = connection.prepareStatement(sql);
            //设置
            pst.setObject(1, "%"+str+"%");
            //得到sql语句的返回值
            rest = pst.executeQuery();
            while(rest.next()){
                //将读入的数据放入一个学生类实例对象中
             Student student = new Student(
                        rest.getString(1),
                        rest.getString(2),
                        rest.getInt(3),
                        rest.getString(4)
                );
             list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJdbc.closeAll(rest, pst, connection);
        }
        return list;
    }

    //添加学生
    public  int addStudent(Student student){
        String sql = "Insert into tblstudent (StuId, StuName,StuAge,StuSex) values (?,?,?,?)";
        int i = MyJdbc.addUpdateOrDelete(sql, student.getStuId(), student.getStuName(), student.getStuAge(), student.getStuSex());
        return i;
    }
    //删除学生
    public  int deleteStudent(String Stuid){
        String sql = "delete from tblstudent where stuid=?";
        int i = MyJdbc.addUpdateOrDelete(sql, Stuid);
        return  i;

    }

    /**
     * 修改学生
     * @param
     */
    public int modifyStudent(Student student){

     return MyJdbc.addUpdateOrDelete("update tblstudent set stuname=?,stuage=?,stusex=? where stuid = ?" ,student.getStuName(),student.getStuAge(),student.getStuSex(),student.getStuId());

    }

    public static void main(String[] args) {
       StudentService2 studentService2 = new StudentService2();
     /*   int i = studentService.modifyStudent(new Student("100","陈千里",16,"男"));
        System.out.println("i = " + i);*/
   /*     int i = studentService2.addStudent(new Student("10", "黄飞鸿", 16, "男"));
        System.out.println("i = " + i);*/
        int i = studentService2.deleteStudent("100");
        System.out.println("i = " + i);
    }
}