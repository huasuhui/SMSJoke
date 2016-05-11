package jdbcUtils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class ExecSQL {
	public String url = "jdbc:mysql://127.0.0.1/joke";  
    public String driver = "com.mysql.jdbc.Driver";  
    public String username = "root";  
    public String password = "a"; 
	
	private Connection conn = null;
	private Statement stmt = null; 
	private ResultSet ret = null;
	
	public ExecSQL(){
		if(conn == null){
			try {
				getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("get Connection fail!");
				e.printStackTrace();
			}
		}
	}
	//get jdbc connection
	private void getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url, username, password);
	}
	
	public void executeQuery(String sql){
		try {
			stmt = (Statement) conn.createStatement();
			ret = stmt.executeQuery(sql);
			while(ret.next()){
				System.out.println(ret.getMetaData().getColumnCount()); //can get column count
				System.out.println(ret.getString(1));
				System.out.println(ret.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}


	}
	
	public boolean executeInsert(String sql){
		int res = 0;
		try {
			stmt = (Statement) conn.createStatement();
			res = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res==1;
		
	}
	
	//close source
	public void closeSource(Connection conn,PreparedStatement pst,ResultSet ret){
		
			try {
				if(ret!=null){
					ret.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
	}

//	public static void main(String[] args) {
//		
//
//	}

}
