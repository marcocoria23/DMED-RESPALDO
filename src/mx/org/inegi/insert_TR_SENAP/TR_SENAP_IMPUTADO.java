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
import mx.org.inegi.bean.SENAP_TR.BeanTR_SENAP_IMPUTADO;
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
public class TR_SENAP_IMPUTADO {

    public void TR_SENAP_IMPUTADO(String Entidad, String Periodo) throws Exception {

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

            ArrayList<BeanTR_SENAP_IMPUTADO> ad = new ArrayList<>();
            QueryTRSENAP DBOData = new QueryTRSENAP();
            fila = DBOData.TMP_SENAP_IMPUTADO(Entidad, Periodo);

            for (int i = 0; i < fila.size(); i++) {
                BeanTR_SENAP_IMPUTADO c = new BeanTR_SENAP_IMPUTADO();
                ArrayList<String> filaActual = fila.get(i);
                    c.SetENTIDADID(filaActual.get(0));
                    c.SetIMPUTADOID(filaActual.get(1));
                    c.SetIMPUTADOID_FISCALIA(filaActual.get(2));
                    c.SetCATFISCALIAID(filaActual.get(3));
                    c.SetCARPETAID(filaActual.get(4));
                    c.SetNOMBREIMPUTADO(filaActual.get(5));
                    c.SetALIASAPODO(filaActual.get(6));
                    c.SetCATTIPOPERSONAMORALID(filaActual.get(7));
                    c.SetCURP(filaActual.get(8));
                    c.SetFECHANACIMIENTO(filaActual.get(9));
                    c.SetEDADMOMENTOSUCEDIERONHECHOS(filaActual.get(10));
                    c.SetCATSEXOID(filaActual.get(11));
                    c.SetCATSITUACIONCONYUGALID(filaActual.get(12));
                    c.SetMEDIAFILIACION(filaActual.get(13));
                    c.SetCATNACIONALIDADID(filaActual.get(14));
                    c.SetCATSITUACIONMIGRATORIAID(filaActual.get(15));
                    c.SetCATPAISNACIMIENTOID(filaActual.get(16));
                    c.SetCATENTIDADNACIMIENTOID(filaActual.get(17));
                    c.SetCATMUNICIPIOID(filaActual.get(18));
                    c.SetCATPAISRESIDENCIAHABITUALID(filaActual.get(19));
                    c.SetUBICACIONID(filaActual.get(20));
                    c.SetNUMEROTELEFONICO(filaActual.get(21));
                    c.SetHABLAESPANIOL(filaActual.get(22));
                    c.SetHABLALENGUAEXTRANJERA(filaActual.get(23));
                    c.SetCATTIPOLENGUAEXTRANJERAID(filaActual.get(24));
                    c.SetHABLALENGUAINDIGENA(filaActual.get(25));
                    c.SetCATTIPOLENGUAINDIGENAID(filaActual.get(26));
                    c.SetUTILIZOTRADUCTORINTERPRETE(filaActual.get(27));
                    c.SetPRESENTADISCAPACIDAD(filaActual.get(28));
                    c.SetCATTIPODISCAPACIDADID(filaActual.get(29));
                    c.SetMEDIOTECDISCAPACIDAD(filaActual.get(30));
                    c.SetPERTENECEPOBLACIONINDIGENA(filaActual.get(31));
                    c.SetCATTIPOPOBLACIONINDIGENAID(filaActual.get(32));
                    c.SetPERTENECEPOBLACIONLGBTTTI(filaActual.get(33));
                    c.SetCATTIPOLGBTTTIID(filaActual.get(34));
                    c.SetPERTENECEPOBLACIONSITUACIONCALLE(filaActual.get(35));
                    c.SetSABELEERESCRIBIR(filaActual.get(36));
                    c.SetCATNIVELESCOLARIDADID(filaActual.get(37));
                    c.SetCATOCUPACIONID(filaActual.get(38));
                    c.SetRANGOINGRESOMENSUALNETO(filaActual.get(39));
                    c.SetIMPUTADOPERTENECEGRUPODELICTIVO(filaActual.get(40));
                    c.SetGRUPODELICTIVO(filaActual.get(41));
                    c.SetCATCOMISIONDELITOID(filaActual.get(42));
                    c.SetANTECEDENTESPENALES(filaActual.get(43));
                    c.SetCATESTADOPSICOFISICOID(filaActual.get(44));
                    c.SetCONTODEFENSOR(filaActual.get(45));
                    c.SetCATTIPODEFENSORID(filaActual.get(46));
                    c.SetCATFORMAPROCESOID(filaActual.get(47));
                    c.SetFECHADETENCION(filaActual.get(48));
                    c.SetHORADETENCION(filaActual.get(49));
                    c.SetCATTIPODETENCIONID(filaActual.get(50));
                    c.SetCATAUTORIDADCARGODETENCIONID(filaActual.get(51));
                    c.SetIMPUTADOFUELIBERADO(filaActual.get(52));
                    c.SetFECHAAUDIENCIACONTROLDETENCION(filaActual.get(53));
                    c.SetCALIFICOLEGALDETENCION(filaActual.get(54));
                    c.SetBORRADO(filaActual.get(55));
                    c.SetFECHAALTA(filaActual.get(56));
                    c.SetUSUARIOALTA(filaActual.get(57));
                    c.SetCARPETAID_FISCALIA(filaActual.get(58));
                    c.SetDIRECCIONUBICACIONID(filaActual.get(59));
                    c.SetFECHACORTE(filaActual.get(60));
                    c.SetCATTIPOPERSONAIMPUTADAID(filaActual.get(61));
                    c.SetFECHAACTUALIZACION(filaActual.get(62));
                    c.SetPERIODO(filaActual.get(63));
                ad.add(c);
                CFilas++;
            }

            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TR_SENAP_IMPUTADO", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TR_SENAP_IMPUTADO", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK_TR.RES_TR_SENAP_IMPUTADO(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                //JOptionPane.showMessageDialog(null, "TABLA TMP_SENAP_IMPUTADO sin registros");
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
