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
import mx.org.inegi.bean.SENAP_TMP.BeanTMP_SENAP_VICTIMAS;
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
public class TMP_SENAP_VICTIMAS {

    public void TMP_SENAP_VICTIMAS(String Entidad, String Periodo) throws Exception {

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

            ArrayList<BeanTMP_SENAP_VICTIMAS> ad = new ArrayList<>();
            QueryTMPSENAP DBOData = new QueryTMPSENAP();
            fila = DBOData.DBO_Victimas();
            if (fila != null){
            for (int i = 0; i < fila.size(); i++) {
                BeanTMP_SENAP_VICTIMAS c = new BeanTMP_SENAP_VICTIMAS();
                ArrayList<String> filaActual = fila.get(i);
                for (int j = 0; j < 49; j++) {
                    c.SetENTIDADID(Entidad);
                    c.SetVICTIMAID(filaActual.get(0));
                    c.SetVICTIMAID_FISCALIA(filaActual.get(1));
                    c.SetCATFISCALIAID(filaActual.get(2));
                    c.SetCARPETAID(filaActual.get(3));
                    c.SetNOMBREVICTIMA(filaActual.get(4));
                    c.SetCATTIPOVICTIMAID(filaActual.get(5));
                    c.SetCURP(filaActual.get(6));
                    c.SetFECHANACIMIENTO(filaActual.get(7));
                    c.SetEDADMOMSUCHEC(filaActual.get(8));
                    c.SetCATSEXOID(filaActual.get(9));
                    c.SetCATSITUACIONCONYUGALID(filaActual.get(10));
                    c.SetVESTIMENTARASGOSVISIBLES(filaActual.get(11));
                    c.SetCATNACIONALIDADID(filaActual.get(12));
                    c.SetCATSITUACIONMIGRATORIAID(filaActual.get(13));
                    c.SetCATPAISNACIMIENTOID(filaActual.get(14));
                    c.SetCATENTIDADNACIMIENTOID(filaActual.get(15));
                    c.SetCATMUNICIPIOID(filaActual.get(16));
                    c.SetCATPAISRESHAB(filaActual.get(17));
                    c.SetDIRECCIONUBICACIONID(filaActual.get(18));
                    c.SetNUMEROTELEFONICO(filaActual.get(19));
                    c.SetHABLAESPANIOL(filaActual.get(20));
                    c.SetHABLALENGUAEXTRANJERA(filaActual.get(21));
                    c.SetCATTIPLEGEXT(filaActual.get(22));
                    c.SetHABLALENGUAINDIGENA(filaActual.get(23));
                    c.SetCATTIPOLENGUAINDIGENAID(filaActual.get(24));
                    c.SetUTITRAINT(filaActual.get(25));
                    c.SetPRESENTADISCAPACIDAD(filaActual.get(26));
                    c.SetCATTIPODISCAPACIDADID(filaActual.get(27));
                    c.SetMEDIOTECDISCAPACIDAD(filaActual.get(28));
                    c.SetPERPOPIND(filaActual.get(29));
                    c.SetCATTIPPOPIND(filaActual.get(30));
                    c.SetPERPOBLGBTTTI(filaActual.get(31));
                    c.SetCATTIPOLGBTTTIID(filaActual.get(32));
                    c.SetPERPOBSITCALLE(filaActual.get(33));
                    c.SetSABELEERESCRIBIR(filaActual.get(34));
                    c.SetCATNIVELESCOLARIDADID(filaActual.get(35));
                    c.SetCATOCUPACIONID(filaActual.get(36));
                    c.SetRANINGMENNET(filaActual.get(37));
                    c.SetHECANTVICMANSUFVIOL(filaActual.get(38));
                    c.SetCONTOASESORJURIDICO(filaActual.get(39));
                    c.SetCATTIPOASESORJURIDICOID(filaActual.get(40));
                    c.SetRECIBIOATENCIONMEDICA(filaActual.get(41));
                    c.SetRECATEPSICO(filaActual.get(42));
                    c.SetBORRADO(filaActual.get(43));
                    c.SetFECHAALTA(filaActual.get(44));
                    c.SetUSUARIOALTA(filaActual.get(45));
                    c.SetCARPETAID_FISCALIA(filaActual.get(46));
                    c.SetFECHACORTE(filaActual.get(47));
                    c.SetFECHAACTUALIZACION(filaActual.get(48));
                    c.SetPERIODO(Periodo);  // Asignar el periodo directamente
                }
                ad.add(c);
                CFilas++;
            }

          
            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TMP_SENAP_VICTIMAS", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TMP_SENAP_VICTIMAS", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK.RES_TMP_SENAP_VICTIMAS(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                JOptionPane.showMessageDialog(null, "TABLA DBO.SENAP_VICTIMAS sin registros");
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
