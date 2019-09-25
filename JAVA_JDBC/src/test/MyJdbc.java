package test;

import java.sql.*;

public class MyJdbc {
    //mysql驱动
    public static final String MYSQL = "com.mysql.jdbc.Driver";
    //URL: 本机地址 + 端口号 + 数据库名
    public static final String URL = "jdbc:mysql://localhost:3306/java";
    //用户名
    public static final String USER = "root";
    //密码
    public static final String PSW= "root";

    public static Connection getConnection() {

        try {
            //1.注册驱动
            Class.forName(MYSQL);
            //2.获取连接
            Connection connection = DriverManager.getConnection(URL,USER,PSW);
           return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static int addUpdateOrDelete(String sql,Object...obj){ //对象数组，动态的，根据传入的参数个数确定
        int num = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i <obj.length ; i++) {
                preparedStatement.setObject(i+1,obj[i]);//循环修改sql语句中的参数
            }
             num = preparedStatement.executeUpdate(); //返回影响行数
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(null,preparedStatement,connection);
        }
        return num;
    }


    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println("connection = " + connection);
    }

    public static void closeAll(ResultSet rest, PreparedStatement pst,Connection connection) {
        try {
            if(rest!=null) {
                rest.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(pst!=null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
