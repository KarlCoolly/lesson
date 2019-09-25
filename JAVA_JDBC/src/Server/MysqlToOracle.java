package Server;

import c3p0.DBUtils;
import c3p0.MysqlDBUtils;
import c3p0.OracleDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class MysqlToOracle {
    private QueryRunner MysqlRunner = new QueryRunner(MysqlDBUtils.getDataSource());
    private QueryRunner OracleRunner = new QueryRunner(OracleDBUtils.getDataSource());

    public List<User> getAllUsers(){
        try {
            return  MysqlRunner.query("select * from t_user",new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public int InsertUsers(User user){
        try {
            return OracleRunner.update("Insert into t_user (userid , uname , upwd , uage ) values(?,?,?,?)",user.getUid(),user.getUname(),user.getPsw(),user.getAge());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        MysqlToOracle mysqlToOracle = new MysqlToOracle();
        List<User> allUsers = mysqlToOracle.getAllUsers();
        for (User u : allUsers) {
            mysqlToOracle.InsertUsers(u);
        }
    }

}
