package Server;

import pojo.Student;
import test.MyJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

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
        Connection connection = MyJdbc.getConnection();
        String sql = "Insert into tblstudent (StuId, StuName,StuAge,StuSex) values (?,?,?,?)";
        PreparedStatement p = null;
        int i=0;
        try {
           p = connection.prepareStatement(sql);
           p.setString(1,student.getStuId());
           p.setString(2,student.getStuName());
           p.setInt(3,student.getStuAge());
           p.setString(4,student.getStuSex());
           i = p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,p,connection);
        }
        return i;
    }
    //删除学生
    public  int deleteStudent(String Stuid){
        int i =0;
        Connection connection = MyJdbc.getConnection();
        String sql = "delete from tblstudent where stuid=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Stuid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    /**
     * 修改学生
     * @param
     */
    public int modifyStudent(Student student){
        int i =0;
        Connection connection = MyJdbc.getConnection();
        String sql = "update  tblstudent set stuname=?,stuage = ? ,stusex=? where stuid=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getStuName());
            preparedStatement.setInt(2,student.getStuAge());
            preparedStatement.setString(3,student.getStuSex());
            preparedStatement.setString(4,student.getStuId());

            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    public static void main(String[] args) {
    /*    StudentService studentService = new StudentService();
        List<Student>  list = studentService.getAllStudent();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
 /*   Student student = studentService.getStudent("1003");
        System.out.println("student = " + student);
    */
       StudentService studentService = new StudentService();
      /*   List<Student>  list = studentService.getStudentLike("郭");
        for (Student student: list ){
            System.out.println(student.toString());
        }*/
        /*int i = studentService.deleteStudent("100");
        System.out.println("i = " + i);*/
  /*      int i = studentService.addStudent(new Student("133", "打多少", 12, "女"));
        System.out.println(i);*/
       int i  = studentService.modifyStudent(new Student("100","张浩",22,"男"));
        System.out.println("i = " + i);
    }
}