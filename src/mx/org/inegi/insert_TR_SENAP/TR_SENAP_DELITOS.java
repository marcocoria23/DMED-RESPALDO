/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.insert_TR_SENAP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mx.org.inegi.LeeOracleTR_SENAP.QueryTRSENAP;
import mx.org.inegi.bean.SENAP_TR.BeanTR_SENAP_DELITOS;
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
public class TR_SENAP_DELITOS {

    public void TR_SENAP_DELITOS(String Entidad, String Periodo) throws Exception {

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

            ArrayList<BeanTR_SENAP_DELITOS> ad = new ArrayList<>();
            QueryTRSENAP DBOData = new QueryTRSENAP();
            fila = DBOData.TMP_SENAP_DELITOS(Entidad, Periodo);

            for (int i = 0; i < fila.size(); i++) {
                BeanTR_SENAP_DELITOS c = new BeanTR_SENAP_DELITOS();
                ArrayList<String> filaActual = fila.get(i);
                    c.SetENTIDADID(filaActual.get(0));
                    c.SetDELITOID(filaActual.get(1));
                    c.SetDELITOID_FISCALIA(filaActual.get(2));
                    c.SetCATFISCALIAID(filaActual.get(3));
                    c.SetCARPETAID(filaActual.get(4));
                    c.SetDELITOLEYPENAL(filaActual.get(5));
                    c.SetSENALAMIENTONORMATIVO(filaActual.get(6));
                    c.SetCATFUEROID(filaActual.get(7));
                    c.SetCATDELITONORMATECNICAID(filaActual.get(8));
                    c.SetFECHAOCURRENCIA(filaActual.get(9));
                    c.SetHORAOCURRENCIA(filaActual.get(10));
                    c.SetDIRECCIONUBICACIONID(filaActual.get(11));
                    c.SetCATTIPOSITIOOCURRENCIAID(filaActual.get(12));
                    c.SetCATGRADOCONSUMACIONID(filaActual.get(13));
                    c.SetCATCALIFICACIONDELITOID(filaActual.get(14));
                    c.SetCATFORMACOMISIONID(filaActual.get(15));
                    c.SetCATFORMAACCIONID(filaActual.get(16));
                    c.SetCATMODALIDADID(filaActual.get(17));
                    c.SetCATINSTRUMENTOSCOMISIONID(filaActual.get(18));
                    c.SetCATCONTEXTOSITUACIONALID(filaActual.get(19));
                    c.SetCATMOMENTORECLASIFICACIONID(filaActual.get(20));
                    c.SetFECHARECLASIFICACION(filaActual.get(21));
                    c.SetBORRADO(filaActual.get(22));
                    c.SetFECHAALTA(filaActual.get(23));
                    c.SetUSUARIOALTA(filaActual.get(24));
                    c.SetCARPETAID_FISCALIA(filaActual.get(25));
                    c.SetDELITOID_RECLASIFICADO(filaActual.get(26));
                    c.SetRECLASIFICACIONACTUAL(filaActual.get(27));
                    c.SetFECHACORTE(filaActual.get(28));
                    c.SetHUBORECLASIFICACION(filaActual.get(29));
                    c.SetFECHAACTUALIZACION(filaActual.get(30));
                    c.SetPERIODO(filaActual.get(31));
                ad.add(c);
                CFilas++;
            }

            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TR_SENAP_DELITOS", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TR_SENAP_DELITOS", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK_TR.RES_TR_SENAP_DELITOS(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                //JOptionPane.showMessageDialog(null, "TABLA TMP_SENAP_DELITOS sin registros");
            }

        } finally {
            try {
                array_to_pass = null;
                structs = null;
                descriptor = null;
                if (con != null) {
                    System.out.println("cierraaa");
                    // JOptionPane.showMessageDialog(null, "CONEXION CERRADA!!-TR_PLE_MEDS1_1");
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                throw new SQLException("[actualiza]: " + ex.getLocalizedMessage());
            }
        }
    }

}
