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


	/**
	java PreparedStatement与Statement区别？
	前者预编译，预编译是啥意思？
	都是执行class文件，那么两种Statement执行一次后都在class文件里了，以后执行都是执行class，这样效率不是一样吗？
	请解释具体执行过程，要能区别效率。
	olalaoleilei | 浏览 5372 次
	推荐于2016-08-08 22:13:36 最佳答案
1、 PreparedStatement接口继承Statement， PreparedStatement 实例包含已编译的 SQL 语句，所以其执行速度要快于 Statement 对象。2、作为 Statement 的子类，PreparedStatement 继承了 Statement 的所有功能。三种方法
	execute、 executeQuery 和 executeUpdate 已被更改以使之不再需要参数
3、在JDBC应用中,如果你已经是稍有水平开发者,你就应该始终以PreparedStatement代替
	Statement.也就是说,在任何时候都不要使用Statement.
			基于以下的原因:
	一.代码的可读性和可维护性.
			虽然用PreparedStatement来代替Statement会使代码多出几行,但这样的代码无论从可读性还是可维护性上来说.都比直接用Statement的代码高很多档次:
			stmt.executeUpdate("insert into tb_name (col1,col2,col2,col4) values ('"+var1+"','"+var2+"',"+var3+",'"+var4+"')");//stmt是Statement对象实例
	perstmt = con.prepareStatement("insert into tb_name (col1,col2,col2,col4) values (?,?,?,?)");
perstmt.setString(1,var1);
perstmt.setString(2,var2);
perstmt.setString(3,var3);
perstmt.setString(4,var4);
perstmt.executeUpdate(); //prestmt是 PreparedStatement 对象实例
	不用我多说,对于第一种方法.别说其他人去读你的代码,就是你自己过一段时间再去读,都会觉得伤心.
			二.PreparedStatement尽最大可能提高性能.
			语句在被DB的编译器编译后的执行代码被缓存下来,那么下次调用时只要是相同的预编译语句就不需要编译,只要将参数直接传入编译过的语句执行代码中(相当于一个涵数)就会得到执行.这并不是说只有一个Connection中多次执行的预编译语句被缓存,而是对于整个DB中,只要预编译的语句语法和缓存中匹配.那么在任何时候就可以不需要再次编译而可以直接执行.而statement的语句中,即使是相同一操作,而由于每次操作的数据不同所以使整个语句相匹配的机会极小,几乎不太可能匹配.比如:
	insert into tb_name (col1,col2) values ('11','22');
	insert into tb_name (col1,col2) values ('11','23');
	即使是相同操作但因为数据内容不一样,所以整个个语句本身不能匹配,没有缓存语句的意义.事实是没有数据库会对普通语句编译后的执行代码缓存.
			当然并不是所以预编译语句都一定会被缓存,数据库本身会用一种策略,比如使用频度等因素来决定什么时候不再缓存已有的预编译结果.以保存有更多的空间存储新的预编译语句.
			三.最重要的一点是极大地提高了安全性.
			即使到目前为止,仍有一些人连基本的恶义SQL语法都不知道.
			String sql = "select * from tb_name where name= '"+varname+"' and passwd='"+varpasswd+"'";
	如果我们把[' or '1' = '1]作为varpasswd传入进来.用户名随意,看看会成为什么?
	select * from tb_name = '随意' and passwd = '' or '1' = '1';
	因为'1'='1'肯定成立,所以可以任何通过验证.更有甚者:
	把[';drop table tb_name;]作为varpasswd传入进来,则:
	select * from tb_name = '随意' and passwd = '';drop table tb_name;有些数据库是不会让你成功的,但也有很多数据库就可以使这些语句得到执行.
			而如果你使用预编译语句.你传入的任何内容就不会和原来的语句发生任何匹配的关系.只要全使用预编译语句,你就用不着对传入的数据做任何过虑.而如果使用普通的statement,有可能要对drop,;等做费尽心机的判断和过虑.
	**/
}


