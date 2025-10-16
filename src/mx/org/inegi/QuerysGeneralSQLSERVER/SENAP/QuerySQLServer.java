/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.QuerysGeneralSQLSERVER.SENAP;

import Pantallas_SENAP.IntegraTMP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.conexion.SENAP.ConDinamicSQLSERVER;
import mx.org.inegi.conexion.SENAP.ConSQLServer;

/**
 *
 * @author ANTONIO.CORIA
 */
public class QuerySQLServer {
    
    
    ConDinamicSQLSERVER conexion=new ConDinamicSQLSERVER();
    ConSQLServer Con=new ConSQLServer();
    ResultSet resul; 
    String sql="",sql2="";
    ArrayList<String[]> Array; 
    IntegraTMP tmp= new IntegraTMP();
    
    
     
    public String Total_Reg_TMP_SqlServer(String Tabla) throws SQLException{
        System.out.println("bdddddd"+tmp.BDSQL);
 conexion.Conectar(tmp.BDSQL);
        
 String TotalReg="";
      sql="SELECT COUNT(*) Total_RegTMP FROM  "+Tabla+" ";
      System.out.println(sql);
     resul=conexion.consultar(sql);
      try {
       if (resul.next()) {
              TotalReg=resul.getString("Total_RegTMP");
       }
      conexion.close();
     } catch (SQLException ex) {
            Logger.getLogger(QuerySQLServer.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:V3_ERRORES_INSERT_RALAB");
        }
      return TotalReg;   
} 
    
    
    public ArrayList Nombre_Base() throws SQLException{
        System.out.println("bdddddd"+tmp.BDSQL);
  Con.Conectar();
  Array = new ArrayList();
      sql="SELECT NAME FROM sys.databases where upper(NAME) LIKE 'SENAP%'";
      System.out.println(sql);
     resul=Con.consultar(sql);
      try {
       while (resul.next()) {
              Array.add(new String[]{
                  resul.getString("name")
                });
       }
      Con.close();
     } catch (SQLException ex) {
            Logger.getLogger(QuerySQLServer.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showInputDialog(null, "Se han eliminado los registros de tabla:V3_ERRORES_INSERT_RALAB");
        }
      return Array;   
} 
    
    
    
    
}
