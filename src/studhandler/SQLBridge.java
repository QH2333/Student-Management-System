package studhandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        System.out.println(getAvgRank("2017003"));
    }
    
    public static int Login(String st_no, String st_pwd) {
    	Connection conn = null;
        Statement stmt = null;
    	int ret_val = -1;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("select * from student where st_no=\"%s\" and st_pwd=\"%s\"", st_no, st_pwd)); // 执行查询    		
    		if (rsResult.next()) {
    			if (st_no.equals("admin")) ret_val = 0; // 管理员
    			else ret_val = 1; // 学生
    		}
    		else ret_val = -1; // 登录错误
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return ret_val;
    }
    
    public static ArrayList<CourseInfo> getAllScore(String st_no) {
    	Connection conn = null;
        Statement stmt = null;
        CourseInfo curCourse = null;
    	ArrayList<CourseInfo> retVal = new ArrayList<CourseInfo>();
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("select * from course")); // 执行查询    		
    		while (rsResult.next()) {
    			curCourse = new CourseInfo(rsResult.getString("c_no"), rsResult.getString("c_name"), rsResult.getInt("c_credit"));
    			retVal.add(curCourse);
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	for (CourseInfo item:retVal) {
    		fillScore(item, st_no);
    	}
    	return retVal;
    }
    
    private static void fillScore(CourseInfo curCourse, String st_no) {
    	Connection conn = null;
        Statement stmt = null;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("select marks from score where c_no = \"%s\" and st_no=\"%s\"",curCourse.courseNo, st_no)); // 执行查询    		
    		if (rsResult.next()) {
    			curCourse.setScore(rsResult.getFloat("marks"));
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public static StudentInfo getStudInfo(String st_no) {
    	Connection conn = null;
        Statement stmt = null;
        StudentInfo retVal = new StudentInfo(st_no);
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("select * from student where st_no=\"%s\"",st_no)); // 执行查询    		
    		if (rsResult.next()) {
    			retVal.gender = rsResult.getInt("st_gender");
    			retVal.studentName = rsResult.getString("st_name");
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
    
    public static int getAvgRank(String st_no) {
    	Connection conn = null;
        Statement stmt = null;
        int retVal = -1;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("SELECT * FROM "
    				+ "(SELECT st_no, @i:=@i+1 AS st_rank FROM (SELECT st_no, avg(marks) AS averageScore FROM score GROUP BY st_no ORDER BY avg(marks) DESC) AS rawData,(SELECT @i:=0) AS colNum) AS rankedData "
    				+ "WHERE st_no=\"%s\"",st_no)); // 执行查询    		
    		if (rsResult.next()) {
    			retVal = rsResult.getInt("st_rank");
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
    
    public static int getStudCount() {
    	Connection conn = null;
        Statement stmt = null;
        int retVal = -1;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery("SELECT count(1) AS st_count FROM student"); // 执行查询    		
    		if (rsResult.next()) {
    			retVal = rsResult.getInt("st_count") - 1; // 去掉admin
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
    
    public static int getCourseRank(String c_no, String st_no) {
    	Connection conn = null;
        Statement stmt = null;
        int retVal = -1;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("SELECT * FROM "
    				+ "(SELECT st_no, @i:=@i+1 AS st_rank FROM (SELECT st_no, marks FROM score WHERE c_no=\"%s\" ORDER BY marks DESC) AS rawData, (SELECT @i:=0) AS colNum) AS rankedData "
    				+ "WHERE st_no=\"%s\";", c_no, st_no)); // 执行查询    		
    		if (rsResult.next()) {
    			retVal = rsResult.getInt("st_rank");
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
    
    public static float getCourseAvg(String c_no) {
    	Connection conn = null;
        Statement stmt = null;
        Float retVal = 0.0f;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		ResultSet rsResult = stmt.executeQuery(String.format("SELECT avg(marks) AS averageScore FROM score WHERE c_no=\"%s\"", c_no)); // 执行查询    		
    		if (rsResult.next()) {
    			retVal = rsResult.getFloat("averageScore");
    		}
    		rsResult.close();
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
    
    public static float chpwd(String st_no, String new_pwd) {
    	Connection conn = null;
        Statement stmt = null;
        Float retVal = 0.0f;
    	try {
    		Class.forName(JDBC_DRIVER); // 注册 JDBC 驱动
            conn = DriverManager.getConnection(DB_URL,USER,PASS); // 打开链接
            stmt = conn.createStatement();
    		stmt.executeUpdate(String.format("update student set st_pwd=\"%s\" where st_no=\"%s\"", new_pwd, st_no)); // 执行查询    		
    		stmt.close();
            conn.close();
    	} catch(SQLException se){ // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e){ // 处理 Class.forName 错误
            e.printStackTrace();
        } finally{ // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            }catch(SQLException se2) {} // 什么都不做
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    	return retVal;
    }
}
