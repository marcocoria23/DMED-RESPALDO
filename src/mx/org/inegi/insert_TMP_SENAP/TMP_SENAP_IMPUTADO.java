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
import mx.org.inegi.bean.SENAP_TMP.BeanTMP_SENAP_IMPUTADO;
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
public class TMP_SENAP_IMPUTADO {

    public void TMP_SENAP_IMPUTADO(String Entidad, String Periodo) throws Exception {

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

            ArrayList<BeanTMP_SENAP_IMPUTADO> ad = new ArrayList<>();
            QueryTMPSENAP DBOData = new QueryTMPSENAP();
            fila = DBOData.DBO_Imputado();
            if (fila != null){
            for (int i = 0; i < fila.size(); i++) {
                BeanTMP_SENAP_IMPUTADO c = new BeanTMP_SENAP_IMPUTADO();
                ArrayList<String> filaActual = fila.get(i);
                for (int j = 0; j < 62; j++) {
                    c.SetENTIDADID(Entidad);
                    c.SetIMPUTADOID(filaActual.get(0));
                    c.SetIMPUTADOID_FISCALIA(filaActual.get(1));
                    c.SetCATFISCALIAID(filaActual.get(2));
                    c.SetCARPETAID(filaActual.get(3));
                    c.SetNOMBREIMPUTADO(filaActual.get(4));
                    c.SetALIASAPODO(filaActual.get(5));
                    c.SetCATTIPOPERSONAMORALID(filaActual.get(6));
                    c.SetCURP(filaActual.get(7));
                    c.SetFECHANACIMIENTO(filaActual.get(8));
                    c.SetEDADMOMENTOSUCEDIERONHECHOS(filaActual.get(9));
                    c.SetCATSEXOID(filaActual.get(10));
                    c.SetCATSITUACIONCONYUGALID(filaActual.get(11));
                    c.SetMEDIAFILIACION(filaActual.get(12));
                    c.SetCATNACIONALIDADID(filaActual.get(13));
                    c.SetCATSITUACIONMIGRATORIAID(filaActual.get(14));
                    c.SetCATPAISNACIMIENTOID(filaActual.get(15));
                    c.SetCATENTIDADNACIMIENTOID(filaActual.get(16));
                    c.SetCATMUNICIPIOID(filaActual.get(17));
                    c.SetCATPAISRESIDENCIAHABITUALID(filaActual.get(18));
                    c.SetUBICACIONID(filaActual.get(19));
                    c.SetNUMEROTELEFONICO(filaActual.get(20));
                    c.SetHABLAESPANIOL(filaActual.get(21));
                    c.SetHABLALENGUAEXTRANJERA(filaActual.get(22));
                    c.SetCATTIPOLENGUAEXTRANJERAID(filaActual.get(23));
                    c.SetHABLALENGUAINDIGENA(filaActual.get(24));
                    c.SetCATTIPOLENGUAINDIGENAID(filaActual.get(25));
                    c.SetUTILIZOTRADUCTORINTERPRETE(filaActual.get(26));
                    c.SetPRESENTADISCAPACIDAD(filaActual.get(27));
                    c.SetCATTIPODISCAPACIDADID(filaActual.get(28));
                    c.SetMEDIOTECDISCAPACIDAD(filaActual.get(29));
                    c.SetPERTENECEPOBLACIONINDIGENA(filaActual.get(30));
                    c.SetCATTIPOPOBLACIONINDIGENAID(filaActual.get(31));
                    c.SetPERTENECEPOBLACIONLGBTTTI(filaActual.get(32));
                    c.SetCATTIPOLGBTTTIID(filaActual.get(33));
                    c.SetPERTENECEPOBLACIONSITUACIONCALLE(filaActual.get(34));
                    c.SetSABELEERESCRIBIR(filaActual.get(35));
                    c.SetCATNIVELESCOLARIDADID(filaActual.get(36));
                    c.SetCATOCUPACIONID(filaActual.get(37));
                    c.SetRANGOINGRESOMENSUALNETO(filaActual.get(38));
                    c.SetIMPUTADOPERTENECEGRUPODELICTIVO(filaActual.get(39));
                    c.SetGRUPODELICTIVO(filaActual.get(40));
                    c.SetCATCOMISIONDELITOID(filaActual.get(41));
                    c.SetANTECEDENTESPENALES(filaActual.get(42));
                    c.SetCATESTADOPSICOFISICOID(filaActual.get(43));
                    c.SetCONTODEFENSOR(filaActual.get(44));
                    c.SetCATTIPODEFENSORID(filaActual.get(45));
                    c.SetCATFORMAPROCESOID(filaActual.get(46));
                    c.SetFECHADETENCION(filaActual.get(47));
                    c.SetHORADETENCION(filaActual.get(48));
                    c.SetCATTIPODETENCIONID(filaActual.get(49));
                    c.SetCATAUTORIDADCARGODETENCIONID(filaActual.get(50));
                    c.SetIMPUTADOFUELIBERADO(filaActual.get(51));
                    c.SetFECHAAUDIENCIACONTROLDETENCION(filaActual.get(52));
                    c.SetCALIFICOLEGALDETENCION(filaActual.get(53));
                    c.SetBORRADO(filaActual.get(54));
                    c.SetFECHAALTA(filaActual.get(55));
                    c.SetUSUARIOALTA(filaActual.get(56));
                    c.SetCARPETAID_FISCALIA(filaActual.get(57));
                    c.SetDIRECCIONUBICACIONID(filaActual.get(58));
                    c.SetFECHACORTE(filaActual.get(59));
                    c.SetCATTIPOPERSONAIMPUTADAID(filaActual.get(60));
                    c.SetFECHAACTUALIZACION(filaActual.get(61));
                    c.SetPERIODO(Periodo);  // Asignar el periodo directamente
                }
                ad.add(c);
                CFilas++;
            }

          
            if (CFilas > 0) {
                sd = StructDescriptor.createDescriptor("RES_OBJ_TMP_SENAP_IMPUTADO", con);
                structs = new STRUCT[ad.size()];
                System.out.println("entro 1");
                System.out.println("tamaño " + ad.size());
                for (int i = 0; i < ad.size(); i++) {
                    structs[i] = new STRUCT(sd, con, ad.get(i).toArray());
                }
                System.out.println("entro 2");
                descriptor = ArrayDescriptor.createDescriptor("RES_ARR_OBJ_TMP_SENAP_IMPUTADO", con);
                System.out.println("entro 3");
                array_to_pass = new ARRAY(descriptor, con, structs);
                System.out.println("entro 4");
                st = con.prepareCall("{? = call(RES_PKG_INTEGRADOR_SENAP_BAK.RES_TMP_SENAP_IMPUTADO(?))}");
                System.out.println("entro 5");
                st.registerOutParameter(1, OracleTypes.INTEGER);
                System.out.println("entro 6");
                st.setArray(2, array_to_pass);
                System.out.println("entro 7");
                st.execute();
                System.out.println("entro 8");
            } else {
                JOptionPane.showMessageDialog(null, "TABLA DBO.SENAP_IMPUTADO sin registros");
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
