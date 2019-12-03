package studhandler;

import java.sql.*;
//import org.json.*;

public class SQLBridge {
	// ע��JDBC������MySQL 8.0+��
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    // �������ݿ�URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/stud?useSSL=false&serverTimezone=UTC";
 
    // ���ݿ���û���������
    static final String USER = "root";
    static final String PASS = "QH1999727";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER); // ע�� JDBC ����
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // ������
            stmt = conn.createStatement();
            ResultSet rsResult = stmt.executeQuery("select st_no from student"); // ִ�в�ѯ
            while (rsResult.next())
            {
            	System.out.println(rsResult.getString("st_no"));
            }
            // ��ɺ�ر�
            rsResult.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){ // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){ // ���� Class.forName ����
            e.printStackTrace();
        }finally{ // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){} // ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
