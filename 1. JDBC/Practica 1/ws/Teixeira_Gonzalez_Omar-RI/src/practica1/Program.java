package practica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

import Student_Package.Student;

public class Program {
	/**
	 * Constante USERNAME
	 */
	public static final String USERNAME = Student.USERNAME;
	/**
	 * Constante PASSWORD
	 */
	public static final String PASSWORD = Student.PASSWORD;	
	/**
	 * Constante URL
	 */
	public static final String URL = "jdbc:oracle:thin:@156.35.94.98:1521:DESA19";
	
	/**
	 * Atributo con
	 */
	private static Connection con;
	/**
	 * Atributo st
	 */
	private static Statement st;
	/**
	 * Atributo pst
	 */
	private static PreparedStatement pst;
	/**
	 * Atributo rs
	 */
	private static ResultSet rs;
	/**
	 * Atributo ds
	 */
	private static DataSource ds;
	
	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		ejercicio1();
		ejercicio2();
		ejercicio3();
	}

	/**
	 * Método ejercicio1
	 */
	public static void ejercicio1() {
		String querySt = "SELECT COUNT(*) FROM TVEHICLES WHERE PLATENUMBER LIKE ";
		String queryPst = "SELECT COUNT(*) FROM TVEHICLES WHERE PLATENUMBER LIKE ?";
		long before = 0, after = 0;
		
		before = System.currentTimeMillis();		
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
			st = con.createStatement();			
			for (int i=0; i<=100; i++) {
				st.execute(querySt + "'%" + i + "%'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		after = System.currentTimeMillis();
		System.out.println(String.format("Statement = " + Long.toString(after - before)));
		
		before = System.currentTimeMillis();		
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
		    pst = con.prepareStatement(queryPst);			
			for (int i=0; i<=100; i++) {
				pst.setInt(1, i);
				pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		after = System.currentTimeMillis();
		System.out.println(String.format("PreparedStatement = " + Long.toString(after - before)));
	}
	
	/**
	 * Método ejercicio2
	 */
	public static void ejercicio2() {
		String query = "SELECT COUNT(*) FROM TVEHICLES";
		long before = 0, after = 0;	
		
		before = System.currentTimeMillis();			
		try {					
			for (int i=0; i<=100; i++) {
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
				st = con.createStatement();		
				st.execute(query);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		after = System.currentTimeMillis();
		System.out.println(String.format("Dentro del bucle = " + Long.toString(after - before)));
		
		before = System.currentTimeMillis();		
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
			st = con.createStatement();			
			for (int i=0; i<=100; i++) {
				st.execute(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		after = System.currentTimeMillis();
		System.out.println(String.format("Fuera del bucle = " + Long.toString(after - before)));		
	}
	
	/**
	 * Método ejercicio3
	 */
	public static void ejercicio3() {
		String query = "SELECT COUNT(*) FROM TVEHICLES";
		long before = 0, after = 0;	
		
		before = System.currentTimeMillis();			
		try {					
			for (int i=0; i<=100; i++) {
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
				st = con.createStatement();		
				st.execute(query);
				releaseResources();
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}
		after = System.currentTimeMillis();
		System.out.println(String.format("Sin pool = " + Long.toString(after - before)));
		
		before = System.currentTimeMillis();	
		try {
			createPoolConnections();
			for (int i=0; i<100; i++) {
				con = ds.getConnection();
				st = con.createStatement();
				st.execute(query);
				releaseResources();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResources();
		}		
		after = System.currentTimeMillis();
		System.out.println(String.format("Con pool = " + Long.toString(after - before)));		
	}
	
	/**
	 * Método createPoolConnections
	 * @throws SQLException
	 */
	private static void createPoolConnections() throws SQLException {
		DataSource ds_unpooled = DataSources.unpooledDataSource(URL, USERNAME, PASSWORD);
		Map<String, Integer> overrides = new HashMap<String, Integer>();
		overrides.put("minPoolSize", 3);
		overrides.put("maxPoolSize", 50);
		overrides.put("initialPoolSize", 3);
		ds = DataSources.pooledDataSource(ds_unpooled, overrides );
		//ya está inicializado
		//Para obtener conexión
		con = ds.getConnection();
	}

	/**
	 * Método releaseResources
	 */
	private static void releaseResources() {
		if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
		if (st != null) { try { st.close(); } catch (SQLException e) {} }
		if (pst != null) { try { pst.close(); } catch (SQLException e) {} }
		if (con != null) { try { con.close(); } catch (SQLException e) {} }	
	}
}
