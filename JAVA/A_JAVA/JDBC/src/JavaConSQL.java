import java.net.URL;
import java.sql.*;

public class JavaConSQL {
	private String user="sa";
	private String password="123";
	private Connection conn;
	private String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
	private String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_test"; //数据源
	public JavaConSQL() {
		try{
			Class.forName(JDriver);//加载数据库引擎，返回给定字符串名的类 
			}catch(ClassNotFoundException e){
			System.out.println("加载数据库引擎失败");
			System.exit(0);
			}
		System.out.println("数据库驱动成功");
		conn=getConnection(connectDB);
	}
	
	public Connection getConnection(String connectDB){
		try {
			conn=DriverManager.getConnection(connectDB, user, password);
			if(conn!=null)
			{
				System.out.println("数据库连接成功！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[]args){
		new JavaConSQL();
		}
}