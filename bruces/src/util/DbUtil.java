package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import test.DbTest;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DbUtil {
    public static Connection getConnection() throws Exception {
        //创建一个Properties类，用来读取配置文件
        Properties per = new Properties();
        //得到配置文件的输入流
        InputStream is = DbTest.class.getClassLoader().getResourceAsStream("druid.properties");
        //将流装载到配置文件类中
        per.load(is);
        //获取DataSource数据资源对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(per);
        //获得数据库连接
        Connection connection = dataSource.getConnection();
        //输出结果
        System.out.println(connection);

        return connection;
    }
}
