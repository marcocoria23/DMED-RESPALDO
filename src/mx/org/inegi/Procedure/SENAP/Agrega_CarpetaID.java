/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.org.inegi.Procedure.SENAP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mx.org.inegi.conexion.SENAP.ConORACLEFactory;

/**
 *
 * @author ANTONIO.CORIA
 */
public class Agrega_CarpetaID {
    
public void Agrega_CarpetaID(String Entidad,String Periodo) throws SQLException{
    CallableStatement st;
        Connection con = null;
        con = ConORACLEFactory.creaConexion();
        
      try {
     
         st = con.prepareCall("{call AGREGA_RES_CARPETAID(?,?)}");
            // Registra el parámetro de salida
            st.setString(1, Entidad);
            st.setString(2, Periodo);
            st.execute();
        
 } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "Favor de contactar al administrador");
        } finally {
            try {
                if (con != null) {
                    System.out.println("cierraaa");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
      }
      
  }
    
 }
