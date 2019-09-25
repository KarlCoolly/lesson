package Server;

import c3p0.DBUtils;
import pojo.User;
import test.MyJdbc;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyUserService {
    /**
     * 查询所有学生（存储过程）
     * @return
     */
    public List<User> getAllList(){
        List<User> list = new ArrayList<>();
        //1.得到数据库的连接
        Connection connection = DBUtils.getConnection();
        //2.定义一个callableStatement对象（存储过程容器）
        CallableStatement cst = null;
        //3.定义一个sql（存储过程语句）
        String sql = "CALL select_user()";
        //返回集
        ResultSet resultSet = null;
        try {
            cst =connection.prepareCall(sql); //预编译sql
            resultSet  = cst.executeQuery(); //得到返回集
            while (resultSet.next()){
                User user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(resultSet,cst,connection);
        }
        return list;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public String addUser(User user){
        //建立连接
        Connection connection = MyJdbc.getConnection();
        CallableStatement cst = null;
        //存储过程的sql语句
        String sql = "CALL add_user(?,?,?,?)";
        //输出字符串
        String msg = null;
        try {
            //为statement对象载入sql
            cst = connection.prepareCall(sql);
            //传入参数
            cst.setString(1,user.getUname());
            cst.setString(2,user.getPsw());
            cst.setInt(3,user.getAge());
            //注册输出参数
            cst.registerOutParameter(4, Types.VARCHAR);
            //执行存储过程
            cst.executeUpdate();
            msg = cst.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,cst,connection);
        }

        return msg;
    }
   public String deleteUser(int uid){
       //建立连接
       Connection connection = MyJdbc.getConnection();
       CallableStatement cst = null;
       //存储过程的sql语句
       String sql = "CALL delete_user(?,?);";
       //输出字符串
       String msg = null;
       try {
           //为statement对象载入sql
           cst = connection.prepareCall(sql);
           //传入参数
           cst.setInt(1,uid);
           //注册输出参数
           cst.registerOutParameter(2, Types.INTEGER);
           //执行存储过程
           cst.executeUpdate();
           msg = cst.getString(2);
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           MyJdbc.closeAll(null,cst,connection);
       }

       return msg;
   }
   public String updateUser(User user){
       //建立连接
       Connection connection = MyJdbc.getConnection();
       CallableStatement cst = null;
       //存储过程的sql语句
       String sql = "CALL update_user(?,?,?,?,?)";
       //输出字符串
       String msg = null;
       try {
           //为statement对象载入sql
           cst = connection.prepareCall(sql);
           //传入参数
           cst.setInt(1,user.getUid());
           cst.setString(2,user.getUname());
           cst.setString(3,user.getPsw());
           cst.setInt(4,user.getAge());
           //注册输出参数
           cst.registerOutParameter(5, Types.INTEGER);
           //执行存储过程
           cst.executeUpdate();
           msg = cst.getString(5);
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           MyJdbc.closeAll(null,cst,connection);
       }

       return msg;
   }
    /**
     * 模糊查询
     * @return
     */
    public List<User> getAllListLike(String str){
        List<User> list = new ArrayList<>();
        //1.得到数据库的连接
        Connection connection = DBUtils.getConnection();
        //2.定义一个callableStatement对象（存储过程容器）
        CallableStatement cst = null;
        //3.定义一个sql（存储过程语句）
        String sql = "CALL selectLike(?)";
        //返回集
        ResultSet resultSet = null;
        try {
            cst =connection.prepareCall(sql); //预编译sql
            cst.setString(1,str);
            resultSet  = cst.executeQuery(); //得到返回集
            while (resultSet.next()){
                User user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(resultSet,cst,connection);
        }
        return list;
    }

    public static void main(String[] args) {
     MyUserService myUserService = new MyUserService();
       /*    List<User> allList = myUserService.getAllList();
        for (User user: allList) {
            System.out.println(user.toString());
        }*/
    /*    List<User> s = myUserService.getAllListLike("s");
        for (User user: s) {
            System.out.println(user.toString());
        }*/
        String dq354 = myUserService.addUser(new User("dq354", "123456", 16));
        System.out.println( dq354);
        /*String s = myUserService.deleteUser(7);
        System.out.println( s);*/
    /*    String ysaho = myUserService.updateUser(new User(8, "ysaho", "13da", 16));
        System.out.println(ysaho);*/
    }

}
