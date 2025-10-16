package mx.org.inegi.conexion.SENAP;
 
import Pantallas_SENAP.IntegraTMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
 
public class ConSQLServer {
    private Connection conexion;
 
    public Connection getConexion() { 
        return conexion; 
    }    
 
    public void setConexion(Connection conexion) { 
        this.conexion = conexion; 
    }  
 
    // CONEXIÓN A SQL SERVER
    public ConSQLServer Conectar() { 
      try { 
            // Registrar el controlador JDBC para SQL Server
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 
            // URL de conexión a SQL Server
           //String baseDeDatos = "jdbc:sqlserver://GSJPCN069519L:1433;databaseName="+DB+";encrypt=true;trustServerCertificate=true;";
          IntegraTMP In=new IntegraTMP();
          String baseDeDatos="";
          
          
          if (In.selectServidor==true)
          {
          baseDeDatos = "jdbc:sqlserver://"+In.Server+";databaseName=master;encrypt=true;trustServerCertificate=true;";    
          }else{
           baseDeDatos = "jdbc:sqlserver://"+In.Server+":1433;databaseName=master;encrypt=true;trustServerCertificate=true;";    
          }
          
           
           System.out.println("++++bd"+baseDeDatos+"user:"+In.Usuario+"Pass:"+In.Pass);
           //  String baseDeDatos = "jdbc:sqlserver://MARCOCORIA\\SQLEXPRESS;databaseName=master;encrypt=true;trustServerCertificate=true;";
            conexion = DriverManager.getConnection(baseDeDatos,In.Usuario,In.Pass);  
            if (conexion != null) { 
                System.out.println("Conexión exitosa!"); 
                JOptionPane.showMessageDialog(null, "Conexión exitosa!");
            } else { 
                System.out.println("Conexión fallida!"); 
                JOptionPane.showMessageDialog(null, "Conexión fallida!");
            } 
        } catch (ClassNotFoundException | SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error!"+e);
            System.out.println("Error: " + e);
        }        
        return this; 
    } 
    public boolean escribir(String sql) { 
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql); 
            sentencia.close();
        } catch (SQLException e) { 
            System.out.print("ERROR SQL: " + e); 
            return false; 
        }         
        return true; 
    }
    public ResultSet consultar(String sql) {
        ResultSet rst = null;
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rst = sentencia.executeQuery(sql);
        } catch (SQLException e) { 
            System.out.println("Error SQL: " + e);
            return null; 
        }
        return rst; 
    }
    public void close() throws SQLException {
        if (conexion != null) {
            System.out.println("Conexión cerrada");
            conexion.close();
        }
    }
}