package connection;
import java.sql.*;

public class ConnectionDB {
	
	
	private Connection con = null;
	
	public Connection conecta(){
		String url = "jdbc:redshift://examit.cllxqcdznlhy.us-east-2.rds.amazonaws.com:5432/examit";
		String usuario = "examit";
		String senha = "examit10";
		String driver = "com.amazon.redshift.jdbc42.Driver";
		
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			con = (Connection) DriverManager.getConnection(url, usuario, senha);
		}catch (SQLException se){
			se.printStackTrace();
		}
		System.out.println("Conectou");
		return con;
	}
	
	public void desconecta(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
