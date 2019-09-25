package Server;

import c3p0.OracleDBUtils;
import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import pojo.User;
import test.MyJdbc;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleService {
    /**
     * 删除用户
     * @param i
     * @return
     */
    public String  deleteUser(int i){
        Connection connection = OracleDBUtils.getConnection();
        CallableStatement cst = null;
        String msg = null;
        try {
            cst = connection.prepareCall("call pkg_user.user_delete(?,?)");
           cst.setInt(1,i);
            cst.registerOutParameter(2, OracleTypes.VARCHAR);
            cst.execute();
            msg = cst.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,cst,connection);
        }
        return  msg;
    }

    /**
     * 修改用户
     * @param
     * @return
     */
    public String  updateUser(User user){
        Connection connection = OracleDBUtils.getConnection();
        CallableStatement cst = null;
        String msg = null;
        try {
            cst = connection.prepareCall("call pkg_user.user_update(?,?,?,?,?,?,?)");
            cst.setInt(1,user.getUid());
            cst.setString(2,user.getUname());
            cst.setString(3,user.getPsw());
            cst.setInt(4,user.getAge());
            cst.setString(5,user.getSex());
            cst.setInt(6,user.getRole());
            cst.registerOutParameter(7, OracleTypes.VARCHAR);
            cst.execute();
            msg = cst.getString(7);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,cst,connection);
        }
        return  msg;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public String  addUser(User user){
        Connection connection = OracleDBUtils.getConnection();
        CallableStatement cst = null;
        String msg = null;
        try {
            cst = connection.prepareCall("call  pkg_user.user_add(?,?,?,?,?,?,?)");
            cst.setInt(1,user.getUid());
            cst.setString(2,user.getUname());
            cst.setString(3,user.getPsw());
            cst.setInt(4,user.getAge());
            cst.setString(5,user.getSex());
            cst.setInt(6,user.getRole());
            cst.registerOutParameter(7, OracleTypes.VARCHAR);
            cst.execute();
            msg = cst.getString(7);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(null,cst,connection);
        }
        return  msg;
    }

    /**
     * 查询
     * @param name
     * @return
     */
    public List<User>  selectUser(String name){
        Connection connection = OracleDBUtils.getConnection();
        CallableStatement cst = null;
        List<User> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            cst = connection.prepareCall("call pkg_user.user_select(?,?)");
            cst.setString(1,name);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
             cst.execute();
            resultSet = (ResultSet) cst.getObject(2);
            while (resultSet.next()){
                User user = new User(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4), 
               resultSet.getString(5),
                resultSet.getInt(6)
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyJdbc.closeAll(resultSet,cst,connection);
        }
        return  list;
    }

    public static void main(String[] args) {
        OracleService os = new OracleService();
       /* String s = os.deleteUser(10);
        System.out.println(s);*/
      /*  String s1 = os.updateUser(new User(28, "谢霆锋", "hdaiu", 13, "男", 1));
        System.out.println("s1 = " + s1);*/
        /*String s1 = os.addUser(new User(27, "谢霆锋", "hdaiu", 13, "男", 1));
        System.out.println("s1 = " + s1);*/
        List<User> list = os.selectUser("谢");
        for (User u:list
             ) {
            System.out.println("u = " + u);
            
        }
    }
}
