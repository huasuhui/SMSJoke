package everydayjoke;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	private ArrayList<ArrayList<String>> resultList = null;
	
	public ExecSQL(){
		if(conn == null){
			try {
				getConnection();
			} catch (Exception e) {
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
	
	public ArrayList<ArrayList<String>> executeQuery(String sql){
		resultList = new ArrayList<ArrayList<String>>();
		try {
			stmt = (Statement) conn.createStatement();
			ret = stmt.executeQuery(sql);
			while(ret.next()){
//				System.out.println(ret.getMetaData().getColumnCount()); //can get column count
//				System.out.println(ret.getString(1));
//				System.out.println(ret.getString(2));
				ArrayList<String> tmpArrayList = new ArrayList<String>();
				for(int i=0;i<=ret.getMetaData().getColumnCount();i++){
					tmpArrayList.add(ret.getString(i));
				}
				resultList.add(tmpArrayList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return resultList;
	}
	
	public String getOneValue(String sql){
		ArrayList<ArrayList<String>> list = executeQuery(sql);
		String result = list.get(0).get(0);
		if(result == null || "".equals(result)){
			result = "";
		}
		return result;
	}
	
	public boolean executeInsert(String sql){
		int resultt = 0;
		try {
			stmt = (Statement) conn.createStatement();
			resultt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			closeSource(conn,stmt,ret);
		}
		
		return resultt == 1;
	}
	
	//close source
	private void closeSource(Connection conn,Statement pst,ResultSet ret){
		
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
