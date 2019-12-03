package studhandler;

import java.sql.*;
//import org.json.*;

public class SQLBridge {
	// 注册JDBC驱动（MySQL 8.0+）
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    // 定义数据库URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/stud?useSSL=false&serverTimezone=UTC";
 
    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "QH1999727";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
            ResultSet rsResult = stmt.executeQuery("select st_no from student"); // 执行查询
            while (rsResult.next())
            {
            	System.out.println(rsResult.getString("st_no"));
            }
            // 完成后关闭
            rsResult.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{ // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){} // 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
