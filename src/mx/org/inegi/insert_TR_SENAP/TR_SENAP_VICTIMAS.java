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
import mx.org.inegi.bean.SENAP_TR.BeanTR_SENAP_VICTIMAS;
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
public class TR_SENAP_VICTIMAS {

    public void TR_SENAP_VICTIMAS(String Entidad, String Periodo) throws Exception {

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

            ArrayList<BeanTR_SENAP_VICTIMAS> ad = new ArrayList<>();
            QueryTRSENAP DBOData = new QueryTRSENAP();
            fila = DBOData.TMP_SENAP_VICTIMAS(Entidad, Periodo);

            for (int i = 0; i < fila.size(); i++) {
                BeanTR_SENAP_VICTIMAS c = new BeanTR_SENAP_VICTIMAS();
                ArrayList<String> filaActual = fila.get(i);
                    c.SetENTIDADID(filaActual.get(0));
                    c.SetVICTIMAID(filaActual.get(1));
                    c.SetVICTIMAID_FISCALIA(filaActual.get(2));
                    c.SetCATFISCALIAID(filaActual.get(3));
                    c.SetCARPETAID(filaActual.get(4));
                    c.SetNOMBREVICTIMA(filaActual.get(5));
                    c.SetCATTIPOVICTIMAID(filaActual.get(6));
                    c.SetCURP(filaActual.get(7));
                    c.SetFECHANACIMIENTO(filaActual.get(8));
                    c.SetEDADMOMSUCHEC(filaActual.get(9));
                    c.SetCATSEXOID(filaActual.get(10));
                    c.SetCATSITUACIONCONYUGALID(filaActual.get(11));
                    c.SetVESTIMENTARASGOSVISIBLES(filaActual.get(12));
                    c.SetCATNACIONALIDADID(filaActual.get(13));
                    c.SetCATSITUACIONMIGRATORIAID(filaActual.get(14));
                    c.SetCATPAISNACIMIENTOID(filaActual.get(15));
                    c.SetCATENTIDADNACIMIENTOID(filaActual.get(16));
                    c.SetCATMUNICIPIOID(filaActual.get(17));
                    c.SetCATPAISRESHAB(filaActual.get(18));
                    c.SetDIRECCIONUBICACIONID(filaActual.get(19));
                    c.SetNUMEROTELEFONICO(filaActual.get(20));
                    c.SetHABLAESPANIOL(filaActual.get(21));
                    c.SetHABLALENGUAEXTRANJERA(filaActual.get(22));
                    c.SetCATTIPLEGEXT(filaActual.get(23));
                    c.SetHABLALENGUAINDIGENA(filaActual.get(24));
                    c.SetCATTIPOLENGUAINDIGENAID(filaActual.get(25));
                    c.SetUTITRAINT(filaActual.get(26));
                    c.SetPRESENTADISCAPACIDAD(filaActual.get(27));
                    c.SetCATTIPODISCAPACIDADID(filaActual.get(28));
                    c.SetMEDIOTECDISCAPACIDAD(filaActual.get(29));
                    c.SetPERPOPIND(filaActual.get(30));
                    c.SetCATTIPPOPIND(filaActual.get(31));
                    c.SetPERPOBLGBTTTI(filaActual.get(32));
                    c.SetCATTIPOLGBTTTIID(filaActual.get(33));
                    c.SetPERPOBSITCALLE(filaActual.get(34));
                    c.SetSABELEERESCRIBIR(filaActual.get(35));
                    c.SetCATNIVELESCOLARIDADID(filaActual.get(36));
                    c.SetCATOCUPACIONID(filaActual.get(37));
                    c.SetRANINGMENNET(filaActual.get(38));
                    c.SetHECANTVICMANSUFVIOL(filaActual.get(39));
                    c.SetCONTOASESORJURIDICO(filaActual.get(40));
                    c.SetCATTIPOASESORJURIDICOID(filaActual.get(41));
                    c.SetRECIBIOATENCIONMEDICA(filaActual.get(42));
                    c.SetRECATEPSICO(filaActual.get(43));
                    c.SetBORRADO(filaActual.get(44));
                    c.SetFECHAALTA(filaActual.get(45));
                    c.SetUSUARIOALTA(filaActual.get(46));
                    c.SetCARPETAID_FISCALIA(filaActual.get(47));
                    c.SetFECHACORTE(filaActual.get(48));
                    c.SetFECHAACTUALIZACION(filaActual.get(49));
                    c.SetPERIODO(filaActual.get(50));
                ad.add(c);
                CFilas++;
            }

            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TR_SENAP_VICTIMAS", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TR_SENAP_VICTIMAS", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK_TR.RES_TR_SENAP_VICTIMAS(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                //JOptionPane.showMessageDialog(null, "TABLA TMP_SENAP_VICTIMAS sin registros");
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
