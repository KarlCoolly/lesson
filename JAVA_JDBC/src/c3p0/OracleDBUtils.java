package c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleDBUtils {
    //实例化一个数据源
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("oracle");
    //得到一个数据源
    public static DataSource getDataSource(){
        return dataSource;
    }
    //得到一个连接池的连接
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
