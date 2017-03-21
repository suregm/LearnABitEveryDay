package bitBeat.QCC;

//STEP 1. Import required packages
import java.sql.*;

/**
 * 感谢2015.11工作上接到的任务：开发一个工具，以处理数据库里获取的数据与本地Excel表格的数据并进行对比。
 * 遂由此接触了JDBC，POI，XML解析，并且实践了swing界面。
 * Created by sure GM on 2016/8/4 0:44.
 */
public class JDBC {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	//  Database credentials
	static final String USER = "username";
	static final String PASS = "password";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}   //end finally try
		}   //end try
		System.out.println("Goodbye!");
	}



	/** java中预处理PrepareStatement为什么能起到防止SQL注入的作用
	Kyoya12XV3 | 浏览 390 次
	推荐于2016-07-31 21:16:59 最佳答案
	不使用这个，我们一般做查询或更新的条件，是用字符串拼起来的，例如
	String id = (String)request.getAttribute("id");    //假设页面上传了一个id值过来
	String SQL = "SELECT ID,NAME FROM USER WHERE ID='" + id + "'";    //拼接成一个完整的sql语句
	但是这样带来了一个风险，因为id是界面上客户输入的，所以如果没有进入校验，有人输入了一个aa' or '1'='1  把这个值代入到上面的sql语句里面，sql语句就变成了
	SELECT ID,NAME FROM USER WHERE ID='aa' or '1'='1'
	这样就能查到所有的数据了，也就是SQL注入.

	但是，如果用preparedstatement的话，就没有这个问题
	String SQL = "SELECT ID,NAME FROM USER WHERE ID=?"
	然后再将值set进去，如果值里面有引号等字符时，会自动的启用转义，不会破坏这个SQL语句的结果，也就不会造成SQL注入了。
	 **/
}


