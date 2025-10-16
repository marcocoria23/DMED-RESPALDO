/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.insert_TMP_SENAP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mx.org.inegi.LeeSQLSERVER_SENAP.QueryTMPSENAP;
import mx.org.inegi.bean.SENAP_TMP.BeanTMP_SENAP_ACTOSINVESTIGACION;
import mx.org.inegi.conexion.SENAP.ConORACLEFactory;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author ANTONIO.CORIA
 */
public class TMP_SENAP_ACTOSINVESTIGACION {

    public void TMP_SENAP_ACTOSINVESTIGACION(String Entidad, String Periodo) throws Exception {

        ARRAY array_to_pass;
        ArrayList<ArrayList<String>> fila;
        CallableStatement st;
        Connection con = null;
        STRUCT[] structs;
        StructDescriptor sd;
        ArrayDescriptor descriptor;
        con = ConORACLEFactory.creaConexion();
        int CFilas = 0;

        try {

            ArrayList<BeanTMP_SENAP_ACTOSINVESTIGACION> ad = new ArrayList<>();
            QueryTMPSENAP DBOData = new QueryTMPSENAP();
            fila = DBOData.DBO_Actosinvestigacion();
            if (fila != null){
            for (int i = 0; i < fila.size(); i++) {
                BeanTMP_SENAP_ACTOSINVESTIGACION c = new BeanTMP_SENAP_ACTOSINVESTIGACION();
                ArrayList<String> filaActual = fila.get(i);
                for (int j = 0; j < 12; j++) {
                    c.SetENTIDADID(Entidad);
                    c.SetACTOSID(filaActual.get(0));
                    c.SetACTOSID_FISCALIA(filaActual.get(1));
                    c.SetCATFISCALIAID(filaActual.get(2));
                    c.SetCARPETAID(filaActual.get(3));
                    c.SetCARPETAID_FISCALIA(filaActual.get(4));
                    c.SetCATACTOSINVESTJUDICIAL(filaActual.get(5));
                    c.SetCATCLASIFICACIONACTOSID(filaActual.get(6));
                    c.SetBORRADO(filaActual.get(7));
                    c.SetFECHAALTA(filaActual.get(8));
                    c.SetUSUARIOALTA(filaActual.get(9));
                    c.SetFECHACORTE(filaActual.get(10));
                    c.SetFECHAACTUALIZACION(filaActual.get(11));
                    c.SetPERIODO(Periodo);  // Asignar el periodo directamente
                }
                ad.add(c);
                CFilas++;
            }

           

            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TMP_SENAP_ACTOSINVESTIGACION", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TMP_SENAP_ACTOSINVESTIGACION", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK.RES_TMP_SENAP_ACTOSINVESTIGACION(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                JOptionPane.showMessageDialog(null, "TABLA DBO.SENAP_ACTOSINVESTIGACION sin registros");
            }
            }
        } finally {
            try {
                array_to_pass = null;
                structs = null;
                descriptor = null;
                if (con != null) {
                    System.out.println("cierraaa");
                    // JOptionPane.showMessageDialog(null, "CONEXION CERRADA!!-TMP_PLE_MEDS1_1");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }
    }

}
