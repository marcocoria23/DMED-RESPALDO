/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.conexion.SENAP;

import Pantallas_SENAP.IntegraTMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO.CORIA
 */
public class ConDinamicSQLSERVER {
   private Connection conexion;
 
    public Connection getConexion() { 
        return conexion; 
    }    
 
    public void setConexion(Connection conexion) { 
        this.conexion = conexion; 
    }  
 
    // CONEXIÓN A SQL SERVER
    public ConDinamicSQLSERVER Conectar(String DB) { 
        try { 
            // Registrar el controlador JDBC para SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 
            // URL de conexión a SQL Server
           //String baseDeDatos = "jdbc:sqlserver://GSJPCN069519L:1433;databaseName="+DB+";encrypt=true;trustServerCertificate=true;";
          IntegraTMP In=new IntegraTMP();
           String baseDeDatos="";
          
          if (In.selectServidor==true)
          {
          baseDeDatos = "jdbc:sqlserver://"+In.Server+";databaseName="+DB+";encrypt=true;trustServerCertificate=true;";    
          }else{
           baseDeDatos = "jdbc:sqlserver://"+In.Server+":1433;databaseName="+DB+";encrypt=true;trustServerCertificate=true;";    
          }
          
            conexion = DriverManager.getConnection(baseDeDatos, ""+In.Usuario+"", ""+In.Pass+"");  
            if (conexion != null) { 
                System.out.println("Conexión exitosa!"); 
            } else { 
                System.out.println("Conexión fallida!"); 
            } 
        } catch (ClassNotFoundException | SQLException e) { 
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
