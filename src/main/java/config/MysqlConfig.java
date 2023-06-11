package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
    public static final String username = "root";
    public static final String password = "minho333";
    public static Connection getConnection(){
        Connection connection = null;
        try{
            //chir định driver sử dụng
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username,password);
        }catch (Exception e){
            System.out.println("Error Connection "+ e.getMessage());
        }

        return connection;
    }
}
