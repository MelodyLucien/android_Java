package MSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//֮ǰҪ���   ѧ�ῴ�ĵ�
public class TestCon {
	
	public static void main(String[] args) {
		Connection conn=null;
		Statement state=null;
		ResultSet res=null;
      try{
            Class.forName("com.mysql.jdbc.Driver");
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs?" +
                                           "user=root&password=root");
               state=conn.createStatement();
               res = state.executeQuery("select * from article");
           while(res.next())
             System.out.println(res.getString("cont"));
        
        } catch(ClassNotFoundException e){
        	e.printStackTrace();
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally{
			try {
				if(res!=null)
				{
					res.close();
					res=null;
				}
				if(state!=null)
				{
					state.close();
					state=null;
				}
				if(conn!=null)
				{
					conn.close();
					conn=null;
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    }
}
