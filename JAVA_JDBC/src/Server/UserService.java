package Server;

import c3p0.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private QueryRunner runner = new QueryRunner(DBUtils.getDataSource());

    /**
     * 获得所有的user
     * @return
     */
    public List<User> getUser(){
        String sql = "select * from t_user";
        try {
            return  runner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一个user（登录）
     * @param uname
     * @param psw
     * @return
     */
    public User getOneUser(String uname , String psw){

        QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from t_user where uname = ? and psw = ?";
        try {
            //BeanHandler<>容器装结果集
            return runner.query(sql,new BeanHandler<>(User.class),uname,psw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模糊查询
     * @param str
     * @return
     */
    public List<User> getUserLike(String str){
        String sql = "select * from t_user where uname like ?";
        try {
            return  runner.query(sql,new BeanListHandler<>(User.class),"%"+str+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改学生
     * @param user
     * @return
     */
    public int UpdateUser(User user){
        QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
        String sql = "update t_user set uname = ? , psw = ?,uage=? where uid= ?";
        try {
            return  runner.update(sql,user.getUname(),user.getPsw(),user.getAge(),user.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除
     * @return
     */
   public int deleteUser(int uid){
       QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
       String sql = "delete from t_user where uid=?";
       try {
           //update不需要返回集
          return runner.update(sql,uid);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
   }
     public int addUser(User user){
         QueryRunner runner = new QueryRunner(DBUtils.getDataSource());
         String sql = "Insert into  t_user ( uname , psw ,uage ) values(?,?,?) ";
         try {
             return  runner.update(sql,user.getUname(),user.getPsw(),user.getAge());
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return 0;
     }

    public static void main(String[] args) {
        UserService service = new UserService();
        /*List<User> user = service.getUser();
        for (User u: user) {
            System.out.println(u.toString());
        }*/

        //service.deleteUser(2);

       /* User u = service.getOneUser("坚少", "123456");
       if(u!=null){
           System.out.println("登录成功");
       }else {
           System.out.println("登录失败");
       }*/

      /*  List<User> users = service.getUserLike("少");
        for (User u : users) {
            System.out.println(u);
        }*/

      /*  int i = service.UpdateUser(new User(3, "sui", "dsa", 13));
        System.out.println("i = " + i);*/
        int u = service.addUser(new User("ming", "123456", 12));
        System.out.println("u = " + u);
    }


}
