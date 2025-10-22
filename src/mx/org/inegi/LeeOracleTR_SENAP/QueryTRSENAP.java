/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.org.inegi.LeeOracleTR_SENAP;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.conexion.SENAP.ConOracle;

/**
 *
 * @author ANTONIO.CORIA
 */
public class QueryTRSENAP {

    ResultSet resul;
    String sql = "", sql2 = "";
    ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
    ConOracle conexion = new ConOracle();

    public ArrayList<ArrayList<String>> TMP_SENAP_DIRECCIONUBICACION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT  \n"
                + "ENTIDADID,\n"
                + "UBICACIONID,\n"
                + "CONVER_TIPOVIALIDAD (CATTIPOVIALIDADID),\n"
                + "CALLEVIALIDAD,\n"
                + "CONVER_COLONIA(CATCOLONIAID),\n"
                + "NUMEROEXTERIOR,\n"
                + "NUMEROINTERIOR,\n"
                + "CODIGOPOSTAL,\n"
                + "CONVER_LOCALIDADES(LOCALIDAD),\n"
                + "CATDEMARCACIONID,\n"
                + "CONVER_ENTIDADFEDERATIVA(CATENTFEDID),\n"
                + "LATITUD,\n"
                + "LONGITUD,\n"
                + "CONVER_SUBPROCESOREFERENCIA(CATSUBPROREFID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "COLONIA,\n"
                + "UBICACION,\n"
                + "CATFISCALIAID,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO FROM RES_TMP_SENAP_DIRECCIONUBICACION WHERE (ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "') and "
                + " (ENTIDADID||PERIODO||UBICACIONID  NOT IN (SELECT ENTIDADID||PERIODO||UBICACIONID FROM TR_SENAP_DIRECCIONUBICACION))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23)); // Añadir la veintitresava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_NOTICIACRIMINAL(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "  SELECT \n"
                + "ENTIDADID,\n"
                + "NOTICIACRIMINALID,\n"
                + "NOTICIACRIMINALID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "NOMBREFISREGIOESPECIALIZADA,\n"
                + "CONVER_TIPOFISCALIAREGESPECIAL(CATFISREGIOESPECIALIZADAID),\n"
                + "NOMBREAGEMINISTERIOPUBLICO,\n"
                + "CONVER_TIPOAGENCIA(CATTIPOAGENCIAID),\n"
                + "NOMAGEMINISTERIOPUBLICORESP,\n"
                + "IDAGEMINISTERIOPUBLICORESP,\n"
                + "CONVER_MEDIOCONOCIMIENTOHECHOS(CATMEDCONOCHECHOSID),\n"
                + "FECHAPRESDENUNQUERELLA,\n"
                + "HORAREPREDENUNQUERELLA,\n"
                + "conver_AUTORIDADRECIBIODENUNCIAQUER(CATAUTORDENUNQUERELLAID),\n"
                + "CONVER_RESPUESTASGENERICAS(INICIOCARPETAINVESTIGACION),\n"
                + "CARPETAID,\n"
                + "CONVER_RESPUESTASGENERICAS(BRINDOALGUNTIPOATENCION),\n"
                + "FOLIONOTICIA,\n"
                + "ANIOFOLIONOTICIA,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "DIRECCIONUBICACIONID,\n"
                + "CARPETAID_FISCALIA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_NOTICIACRIMINAL WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'   and "
                + " (ENTIDADID||PERIODO||NOTICIACRIMINALID  NOT IN (SELECT ENTIDADID||PERIODO||NOTICIACRIMINALID FROM TR_SENAP_NOTICIACRIMINAL))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23));
                fila.add(resul.getString(24));
                fila.add(resul.getString(25));
                fila.add(resul.getString(26));
                fila.add(resul.getString(27));// Añadir la veintitresava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_ATENCIONES(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "ATENCIONID,\n"
                + "CONVER_TIPOATENCION(CATTIPOATENCIONID),\n"
                + "NUMEROATENCION,\n"
                + "FECHAINICIOATENCION,\n"
                + "FECHACONCLUSIONATENCION,\n"
                + "CONVER_SENTIDOCONCLUSIONATENCION(CATSENTIDOCONCLUSIONATENCIONID),\n"
                + "NOTICIACRIMINALID,\n"
                + "NOTICIACRIMINALID_FISCALIA,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "CATFISCALIAID,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_ATENCIONES WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||ATENCIONID  NOT IN (SELECT ENTIDADID||PERIODO||ATENCIONID FROM TR_SENAP_ATENCIONES))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_CARPETAINVESTIGACION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID	,\n"
                + "CARPETAID	,\n"
                + "CARPETAID_FISCALIA	,\n"
                + "CATFISCALIAID	,\n"
                + "NOTICIACRIMINALID_FISCALIA	,\n"
                + "NOTICIACRIMINALID	,\n"
                + "NOMBREFISREGIOESPECIALIZADA	,\n"
                + "CONVER_MEDIOCONOCIMIENTOHECHOS(CATFISREGIOESPECIALIZADAID)	,\n"
                + "NOMBREAGEMINISTERIOPUBLICO	,\n"
                + "CONVER_TIPOAGENCIA(CATTIPOAGENCIAID)	,\n"
                + "NOMAGEMINISTERIOPUBLICORESP	,\n"
                + "IDAGEMINISTERIOPUBLICORESP	,\n"
                + "CONVER_MEDIOCONOCIMIENTOHECHOS(CATMEDCONOCHECHOSID)	,\n"
                + "NUMCARPINVE	,\n"
                + "NUMERICOANIOCARPETA	,\n"
                + "FECAPECARINVE	,\n"
                + "HORAPECARINVE	,\n"
                + "CONVER_FORMAINICIOCARPETAINVESTIGACION(CATFORMINICARINVEID)	,\n"
                + "CONVER_RESPUESTASGENERICAS(REALACTINVE)	,\n"
                + "CANTIDADDELITOS	,\n"
                + "CANTIDADVICTIMAS	,\n"
                + "CANTIDADIMPUTADOS	,\n"
                + "CONVER_RESPUESTASGENERICAS(REALIZARONASEGURAMIENTOS)	,\n"
                + "FOLIOCARPETA	,\n"
                + "ANIOFOLIOCARPETA,\n"
                + "DETERMINACIONID_ULTIMO,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "DIRECCIONUBICACIONID,\n"
                + "FECHACORTE,\n"
                + "CATHECHOCIID,\n"
                + "CONVER_ETAPAPROCESAL(CATETAPAPROCESALID),\n"
                + "DESCRIPCIONHECHOS,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO "
                + "  FROM RES_TMP_SENAP_CARPETAINVESTIGACION WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||CARPETAID  NOT IN (SELECT ENTIDADID||PERIODO||CARPETAID FROM TR_SENAP_CARPETAINVESTIGACION))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23)); // Añadir la veintitresava columna
                fila.add(resul.getString(24)); // Añadir la veinticuatroava columna
                fila.add(resul.getString(25)); // Añadir la veinticincoava columna
                fila.add(resul.getString(26)); // Añadir la veinticincoava columna
                fila.add(resul.getString(27)); // Añadir la veinticincoava columna
                fila.add(resul.getString(28)); // Añadir la veinticincoava columna
                fila.add(resul.getString(29)); // Añadir la veinticincoava columna
                fila.add(resul.getString(30)); // Añadir la veinticincoava columna
                fila.add(resul.getString(31)); // Añadir la veinticincoava columna
                fila.add(resul.getString(32)); // Añadir la veinticincoava columna
                fila.add(resul.getString(33)); // Añadir la veinticincoava columna
                fila.add(resul.getString(34)); // Añadir la veinticincoava columna
                fila.add(resul.getString(35)); // Añadir la veinticincoava columna
                fila.add(resul.getString(36)); // Añadir la veinticincoava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_ASEGURAMIENTOS(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT  ENTIDADID,\n"
                + "ENTFISASEGID,\n"
                + "ENTFISASEGID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "CARPETAID,\n"
                + "CARPETAID_FISCALIA,\n"
                + "CONVER_CLASIFICACIONBIENASEGURADO(CATCLASBIENSEG),\n"
                + "CONVER_TIPOBIENASEGURADO(CATTIPBIENSEG),\n"
                + "CANTBIESEG,\n"
                + "CONVER_UNIDADMEDIDA(CATUNIDADMEDIDAID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_ASEGURAMIENTOS WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||ENTFISASEGID  NOT IN (SELECT ENTIDADID||PERIODO||ENTFISASEGID FROM TR_SENAP_ASEGURAMIENTOS))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_ACTOSINVESTIGACION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "  SELECT \n"
                + "  ENTIDADID,\n"
                + "ACTOSID,\n"
                + "ACTOSID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "CARPETAID,\n"
                + "CARPETAID_FISCALIA,\n"
                + "CATACTOSINVESTJUDICIAL,\n"
                + "CONVER_CLASIFICACIONACTOS(CATCLASIFICACIONACTOSID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_ACTOSINVESTIGACION WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||ACTOSID  NOT IN (SELECT ENTIDADID||PERIODO||ACTOSID FROM TR_SENAP_ACTOSINVESTIGACION))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_DELITOS(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = " SELECT \n"
                + "  ENTIDADID	,\n"
                + "DELITOID,\n"
                + "DELITOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "CARPETAID,\n"
                + "DELITOLEYPENAL,\n"
                + "SENALAMIENTONORMATIVO,\n"
                + "CONVER_FUERO(CATFUEROID),\n"
                + "CATDELITONORMATECNICAID,\n"
                + "FECHAOCURRENCIA,\n"
                + "HORAOCURRENCIA,\n"
                + "DIRECCIONUBICACIONID,\n"
                + "CONVER_TIPOSITIOOCURRENCIA(CATTIPOSITIOOCURRENCIAID),\n"
                + "CONVER_GRADOCONSUMACION(CATGRADOCONSUMACIONID),\n"
                + "CONVER_CALIFICACIONDELITO(CATCALIFICACIONDELITOID),\n"
                + "CONVER_FORMACOMISION(CATFORMACOMISIONID),\n"
                + "CONVER_FORMAACCION(CATFORMAACCIONID),\n"
                + "CONVER_MODALIDAD(CATMODALIDADID),\n"
                + "CONVER_INSTRUMENTOSCOMISION(CATINSTRUMENTOSCOMISIONID),\n"
                + "CONVER_CONTEXTOSITUACIONAL(CATCONTEXTOSITUACIONALID),\n"
                + "CONVER_MOMENTORECLASIFICACION(CATMOMENTORECLASIFICACIONID),\n"
                + "FECHARECLASIFICACION,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "CARPETAID_FISCALIA,\n"
                + "DELITOID_RECLASIFICADO,\n"
                + "RECLASIFICACIONACTUAL,\n"
                + "FECHACORTE,\n"
                + "CONVER_RESPUESTASGENERICAS(HUBORECLASIFICACION),\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_DELITOS  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||DELITOID  NOT IN (SELECT ENTIDADID||PERIODO||DELITOID FROM TR_SENAP_DELITOS))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23)); // Añadir la veintitresava columna
                fila.add(resul.getString(24)); // Añadir la veinticuatroava columna
                fila.add(resul.getString(25)); // Añadir la veinticincoava columna
                fila.add(resul.getString(26)); // Añadir la veinticincoava columna
                fila.add(resul.getString(27)); // Añadir la veinticincoava columna
                fila.add(resul.getString(28)); // Añadir la veinticincoava columna
                fila.add(resul.getString(29)); // Añadir la veinticincoava columna
                fila.add(resul.getString(30)); // Añadir la veinticincoava columna
                fila.add(resul.getString(31)); // Añadir la veinticincoava columna
                fila.add(resul.getString(32)); // Añadir la veinticincoava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_VICTIMAS(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID	,\n"
                + "VICTIMAID	,\n"
                + "VICTIMAID_FISCALIA	,\n"
                + "CATFISCALIAID	,\n"
                + "CARPETAID	,\n"
                + "NOMBREVICTIMA	,\n"
                + "CONVER_TIPOVICTIMA(CATTIPOVICTIMAID),\n"
                + "CURP	,\n"
                + "FECHANACIMIENTO	,\n"
                + "EDADMOMSUCHEC	,\n"
                + "CONVER_SEXO(CATSEXOID),\n"
                + "CONVER_SITUACIONCONYUGAL(CATSITUACIONCONYUGALID)	,\n"
                + "VESTIMENTARASGOSVISIBLES	,\n"
                + "CONVER_NACIONALIDAD(CATNACIONALIDADID),\n"
                + "CONVER_SITUACIONMIGRATORIA(CATSITUACIONMIGRATORIAID)	,\n"
                + "CONVER_PAIS(CATPAISNACIMIENTOID)	,\n"
                + "CONVER_ENTIDADFEDERATIVA(CATENTIDADNACIMIENTOID)	,\n"
                + "CATMUNICIPIOID	,\n"
                + "CONVER_PAIS(CATPAISRESHAB),\n"
                + "DIRECCIONUBICACIONID	,\n"
                + "NUMEROTELEFONICO	,\n"
                + "CONVER_RESPUESTASGENERICAS(HABLAESPANIOL)	,\n"
                + "CONVER_RESPUESTASGENERICAS(HABLALENGUAEXTRANJERA)	,\n"
                + "CONVER_TIPOLENGUAEXTRANJERA(CATTIPLEGEXT)	,\n"
                + "CONVER_RESPUESTASGENERICAS(HABLALENGUAINDIGENA),\n"
                + "CONVER_TIPOLENGUAINDIGENA(CATTIPOLENGUAINDIGENAID)	,\n"
                + "CONVER_RESPUESTASGENERICAS(UTITRAINT)	,\n"
                + "CONVER_RESPUESTASGENERICAS(PRESENTADISCAPACIDAD)	,\n"
                + "CONVER_TIPODISCAPACIDAD(CATTIPODISCAPACIDADID),\n"
                + "CONVER_RESPUESTASGENERICAS(MEDIOTECDISCAPACIDAD)	,\n"
                + "CONVER_RESPUESTASGENERICAS(PERPOPIND)	,\n"
                + "CONVER_POBLACIONINDIGENAPERTENECE(CATTIPPOPIND)	,\n"
                + "CONVER_RESPUESTASGENERICAS(PERPOBLGBTTTI)	,\n"
                + "CONVER_POBLACIONLGBTTTIPERTENECE(CATTIPOLGBTTTIID)	,\n"
                + "CONVER_RESPUESTASGENERICAS(PERPOBSITCALLE)	,\n"
                + "CONVER_RESPUESTASGENERICAS(SABELEERESCRIBIR)	,\n"
                + "CONVER_NIVELESCOLARIDAD(CATNIVELESCOLARIDADID)	,\n"
                + "CONVER_OCUPACION(CATOCUPACIONID)	,\n"
                + "RANINGMENNET	,\n"
                + "CONVER_RESPUESTASGENERICAS(HECANTVICMANSUFVIOL)	,\n"
                + "CONVER_RESPUESTASGENERICAS(CONTOASESORJURIDICO)	,\n"
                + "CONVER_TIPOASESORJURIDICO(CATTIPOASESORJURIDICOID),\n"
                + "CONVER_RESPUESTASGENERICAS(RECIBIOATENCIONMEDICA)	,\n"
                + "CONVER_RESPUESTASGENERICAS(RECATEPSICO)	,\n"
                + "BORRADO	,\n"
                + "FECHAALTA	,\n"
                + "USUARIOALTA	,\n"
                + "CARPETAID_FISCALIA	,\n"
                + "FECHACORTE	,\n"
                + "FECHAACTUALIZACION	,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_VICTIMAS  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||VICTIMAID  NOT IN (SELECT ENTIDADID||PERIODO||VICTIMAID FROM TR_SENAP_VICTIMAS))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23)); // Añadir la veintitresava columna
                fila.add(resul.getString(24)); // Añadir la veinticuatroava columna
                fila.add(resul.getString(25)); // Añadir la veinticincoava columna
                fila.add(resul.getString(26)); // Añadir la veinticincoava columna
                fila.add(resul.getString(27)); // Añadir la veinticincoava columna
                fila.add(resul.getString(28)); // Añadir la veinticincoava columna
                fila.add(resul.getString(29)); // Añadir la veinticincoava columna
                fila.add(resul.getString(30)); // Añadir la veinticincoava columna
                fila.add(resul.getString(31)); // Añadir la veinticincoava columna
                fila.add(resul.getString(32)); // Añadir la veinticincoava columna
                fila.add(resul.getString(33)); // Añadir la veinticincoava columna
                fila.add(resul.getString(34)); // Añadir la veinticincoava columna
                fila.add(resul.getString(35)); // Añadir la veinticincoava columna
                fila.add(resul.getString(36)); // Añadir la veinticincoava columna
                fila.add(resul.getString(37)); // Añadir la veinticincoava columna
                fila.add(resul.getString(38)); // Añadir la veinticincoava columna
                fila.add(resul.getString(39)); // Añadir la veinticincoava columna
                fila.add(resul.getString(40)); // Añadir la veinticincoava columna
                fila.add(resul.getString(41)); // Añadir la veinticincoava columna
                fila.add(resul.getString(42)); // Añadir la veinticincoava columna
                fila.add(resul.getString(43)); // Añadir la veinticincoava columna
                fila.add(resul.getString(44)); // Añadir la veinticincoava columna
                fila.add(resul.getString(45)); // Añadir la veinticincoava columna
                fila.add(resul.getString(46)); // Añadir la veinticincoava columna
                fila.add(resul.getString(47)); // Añadir la veinticincoava columna
                fila.add(resul.getString(48)); // Añadir la veinticincoava columna
                fila.add(resul.getString(49)); // Añadir la veinticincoava columna
                fila.add(resul.getString(50)); // Añadir la veinticincoava columna
                fila.add(resul.getString(51)); // Añadir la veinticincoava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_VICTIMASDELITO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "VICTIMASDELITOID,\n"
                + "VICTIMASDELITOID_FISCALIA,\n"
                + "DELITOID,\n"
                + "VICTIMAID,\n"
                + "CATFISCALIAID,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "DELITOID_FISCALIA,\n"
                + "VICTIMAID_FISCALIA,\n"
                + "CARPETAID,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_VICTIMASDELITO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||VICTIMASDELITOID  NOT IN (SELECT ENTIDADID||PERIODO||VICTIMASDELITOID FROM TR_SENAP_VICTIMASDELITO))     ";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_MEDIDAS_PROTECCION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID	,\n"
                + "MEDIDASPROTECCIONID	,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "VICTIMAID	,\n"
                + "CONVER_MEDIDAPROTECCION(CATMEDIDAPROTECCION)	,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_MEDIDAS_PROTECCION  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||MEDIDASPROTECCIONID  NOT IN (SELECT ENTIDADID||PERIODO||MEDIDASPROTECCIONID FROM TR_SENAP_MEDIDAS_PROTECCION))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_IMPUTADO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "IMPUTADOID,\n"
                + "IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "CARPETAID,\n"
                + "NOMBREIMPUTADO,\n"
                + "ALIASAPODO,\n"
                + "CONVER_TIPOPERSONAMORAL(CATTIPOPERSONAMORALID),\n"
                + "CURP,\n"
                + "FECHANACIMIENTO,\n"
                + "EDADMOMENTOSUCEDIERONHECHOS,\n"
                + "CONVER_SEXO(CATSEXOID),\n"
                + "CONVER_SITUACIONCONYUGAL(CATSITUACIONCONYUGALID),\n"
                + "MEDIAFILIACION,\n"
                + "CONVER_NACIONALIDAD(CATNACIONALIDADID),\n"
                + "CONVER_SITUACIONMIGRATORIA(CATSITUACIONMIGRATORIAID),\n"
                + "CONVER_PAIS(CATPAISNACIMIENTOID),\n"
                + "CONVER_ENTIDADFEDERATIVA(CATENTIDADNACIMIENTOID),\n"
                + "CATMUNICIPIOID,\n"
                + "CONVER_PAIS(CATPAISRESIDENCIAHABITUALID),\n"
                + "UBICACIONID,\n"
                + "NUMEROTELEFONICO,\n"
                + "CONVER_RESPUESTASGENERICAS(HABLAESPANIOL),\n"
                + "CONVER_RESPUESTASGENERICAS(HABLALENGUAEXTRANJERA),\n"
                + "CONVER_TIPOLENGUAEXTRANJERA(CATTIPOLENGUAEXTRANJERAID),\n"
                + "CONVER_RESPUESTASGENERICAS(HABLALENGUAINDIGENA),\n"
                + "CONVER_TIPOLENGUAINDIGENA(CATTIPOLENGUAINDIGENAID),\n"
                + "CONVER_RESPUESTASGENERICAS(UTILIZOTRADUCTORINTERPRETE),\n"
                + "CONVER_RESPUESTASGENERICAS(PRESENTADISCAPACIDAD),\n"
                + "CONVER_TIPODISCAPACIDAD(CATTIPODISCAPACIDADID),\n"
                + "CONVER_RESPUESTASGENERICAS(MEDIOTECDISCAPACIDAD),\n"
                + "CONVER_RESPUESTASGENERICAS(PERTENECEPOBLACIONINDIGENA),\n"
                + "CONVER_POBLACIONINDIGENAPERTENECE(CATTIPOPOBLACIONINDIGENAID),\n"
                + "CONVER_RESPUESTASGENERICAS(PERTENECEPOBLACIONLGBTTTI),\n"
                + "CONVER_POBLACIONLGBTTTIPERTENECE(CATTIPOLGBTTTIID),\n"
                + "CONVER_RESPUESTASGENERICAS(PERTENECEPOBLACIONSITUACIONCALLE),\n"
                + "CONVER_RESPUESTASGENERICAS(SABELEERESCRIBIR),\n"
                + "CONVER_NIVELESCOLARIDAD(CATNIVELESCOLARIDADID),\n"
                + "CONVER_OCUPACION(CATOCUPACIONID),\n"
                + "RANGOINGRESOMENSUALNETO,\n"
                + "CONVER_RESPUESTASGENERICAS(IMPUTADOPERTENECEGRUPODELICTIVO),\n"
                + "GRUPODELICTIVO,\n"
                + "CONVER_GRADOAUTORIAPARTICIPACIONCOMISIONDELITO(CATCOMISIONDELITOID),\n"
                + "CONVER_RESPUESTASGENERICAS(ANTECEDENTESPENALES),\n"
                + "CONVER_ESTADOPSICOFISICO(CATESTADOPSICOFISICOID),\n"
                + "CONVER_RESPUESTASGENERICAS(CONTODEFENSOR),\n"
                + "CONVER_TIPODEFENSOR(CATTIPODEFENSORID),\n"
                + "CONVER_FORMAPROCESO(CATFORMAPROCESOID),\n"
                + "FECHADETENCION,\n"
                + "HORADETENCION,\n"
                + "CONVER_TIPODETENCION(CATTIPODETENCIONID),\n"
                + "CONVER_AUTORIDADCARGODETENCION(CATAUTORIDADCARGODETENCIONID),\n"
                + "CONVER_RESPUESTASGENERICAS(IMPUTADOFUELIBERADO),\n"
                + "FECHAAUDIENCIACONTROLDETENCION,\n"
                + "conver_respuestasgenericas(CALIFICOLEGALDETENCION),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "CARPETAID_FISCALIA,\n"
                + "DIRECCIONUBICACIONID,\n"
                + "FECHACORTE,\n"
                + "CONVER_TIPOPERSONAIMPUTADA(CATTIPOPERSONAIMPUTADAID),\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_IMPUTADO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||IMPUTADOID  NOT IN (SELECT ENTIDADID||PERIODO||IMPUTADOID FROM TR_SENAP_IMPUTADO))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la diecisieteava columna
                fila.add(resul.getString(18)); // Añadir la dieciochoava columna
                fila.add(resul.getString(19)); // Añadir la diecinueveava columna
                fila.add(resul.getString(20)); // Añadir la veinteava columna
                fila.add(resul.getString(21)); // Añadir la veintiunava columna
                fila.add(resul.getString(22)); // Añadir la veintidosava columna
                fila.add(resul.getString(23)); // Añadir la veintitresava columna
                fila.add(resul.getString(24)); // Añadir la veinticuatroava columna
                fila.add(resul.getString(25)); // Añadir la veinticincoava columna
                fila.add(resul.getString(26)); // Añadir la veinticincoava columna
                fila.add(resul.getString(27)); // Añadir la veinticincoava columna
                fila.add(resul.getString(28)); // Añadir la veinticincoava columna
                fila.add(resul.getString(29)); // Añadir la veinticincoava columna
                fila.add(resul.getString(30)); // Añadir la veinticincoava columna
                fila.add(resul.getString(31)); // Añadir la veinticincoava columna
                fila.add(resul.getString(32)); // Añadir la veinticincoava columna
                fila.add(resul.getString(33)); // Añadir la veinticincoava columna
                fila.add(resul.getString(34)); // Añadir la veinticincoava columna
                fila.add(resul.getString(35)); // Añadir la veinticincoava columna
                fila.add(resul.getString(36)); // Añadir la veinticincoava columna
                fila.add(resul.getString(37)); // Añadir la veinticincoava columna
                fila.add(resul.getString(38)); // Añadir la veinticincoava columna
                fila.add(resul.getString(39)); // Añadir la veinticincoava columna
                fila.add(resul.getString(40)); // Añadir la veinticincoava columna
                fila.add(resul.getString(41)); // Añadir la veinticincoava columna
                fila.add(resul.getString(42)); // Añadir la veinticincoava columna
                fila.add(resul.getString(43)); // Añadir la veinticincoava columna
                fila.add(resul.getString(44)); // Añadir la veinticincoava columna
                fila.add(resul.getString(45)); // Añadir la veinticincoava columna
                fila.add(resul.getString(46)); // Añadir la veinticincoava columna
                fila.add(resul.getString(47)); // Añadir la veinticincoava columna
                fila.add(resul.getString(48)); // Añadir la veinticincoava columna
                fila.add(resul.getString(49)); // Añadir la veinticincoava columna
                fila.add(resul.getString(50)); // Añadir la veinticincoava columna
                fila.add(resul.getString(51)); // Añadir la veinticincoava columna
                fila.add(resul.getString(52)); // Añadir la veinticincoava columna
                fila.add(resul.getString(53)); // Añadir la veinticincoava columna
                fila.add(resul.getString(54)); // Añadir la veinticincoava columna
                fila.add(resul.getString(55)); // Añadir la veinticincoava columna
                fila.add(resul.getString(56)); // Añadir la veinticincoava columna
                fila.add(resul.getString(57)); // Añadir la veinticincoava columna
                fila.add(resul.getString(58)); // Añadir la veinticincoava columna
                fila.add(resul.getString(59)); // Añadir la veinticincoava columna
                fila.add(resul.getString(60)); // Añadir la veinticincoava columna
                fila.add(resul.getString(61)); // Añadir la veinticincoava columna
                fila.add(resul.getString(62)); // Añadir la veinticincoava columna
                fila.add(resul.getString(63)); // Añadir la veinticincoava columna
                fila.add(resul.getString(64)); // Añadir la veinticincoava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_IMPUTADODELITO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "IMPUTADODELITOID,\n"
                + "IMPUTADODELITOID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA\n"
                + ",\n"
                + "DELITOSDELITOID,\n"
                + "IMPUTADOID,\n"
                + "DELITOID_FISCALIA,\n"
                + "IMPUTADOID_FISCALIA,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "CATFISCALIAID,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_IMPUTADODELITO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||IMPUTADODELITOID  NOT IN (SELECT ENTIDADID||PERIODO||IMPUTADODELITOID FROM TR_SENAP_IMPUTADODELITO))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);
        int i = 0;
        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_VICTIMAIMPUTADO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "VICTIMAIMPUTADOID,\n"
                + "VICTIMAIMPUTADOID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "VICTIMAID,\n"
                + "IMPUTADOID,\n"
                + "VICTIMAID_FISCALIA,\n"
                + "IMPUTADOID_FISCALIA,\n"
                + "CONVER_TIPORELACION(CATTIPORELACIONID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_VICTIMAIMPUTADO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||VICTIMAIMPUTADOID  NOT IN (SELECT ENTIDADID||PERIODO||VICTIMAIMPUTADOID FROM TR_SENAP_VICTIMAIMPUTADO))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_DETERMINACION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "DETERMINACIONID,\n"
                + "DETERMINACIONID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "CARPETAID,\n"
                + "CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "NULL IMPUTADODELITOID,\n"
                + "NULL IMPUTADODELITOID_FISCALIA,\n"
                + "CONVER_ESTATUSCARPETAINVESTIGAC(CATESTATUSCARPETAID),\n"
                + "CONVER_SENTIDODETERMINACION(CATSENDETID),\n"
                + "CONVER_TIPOACUERDOREPARATORIO(CATACURECID),\n"
                + "CONVER_RESPUESTASGENERICAS(HUBOREACTCARPETAINVEST),\n"
                + "FECHADETERMINACION,\n"
                + "NULL CATETAPAPROCESAL,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_DETERMINACION  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||DETERMINACIONID  NOT IN (SELECT ENTIDADID||PERIODO||DETERMINACIONID FROM TR_SENAP_DETERMINACION))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_PROCESO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL CATETAPAPROCESALID,\n"
                + "CATFISCALIAID,\n"
                + "IMPUTADOID,\n"
                + "NULL IMPUTADODELITOID,\n"
                + "NULL IMPUTADODELITOID_FISCALIA,\n"
                + "PROCESOCAUSA,\n"
                + "FECHAINICIO,\n"
                + "ANIOPROCESO,\n"
                + "conver_respuestasgenericas(HUBOCELEBRACIONAUDIENCIAINICIAL),\n"
                + "conver_MOTIVOAUDIENCIAINICIAL(CATMOTIVOAUDIENCIAINICIALID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "IMPUTADOID_FISCALIA,\n"
                + "FECHAAUDIENCIAINICIAL,\n"
                + "OTROMOTIVO,\n"
                + "conver_ESTATUSPROCESO(ULTIMOESTATUSPROCESOID),\n"
                + "FECHAULTIMOESTATUSPROCESO,\n"
                + "NULL CATESTATUSCARPETAID,\n"
                + "FECHACORTE,\n"
                + "conver_respuestasgenericas(TIENESUSPENSIONCONDICIONAL),\n"
                + "conver_respuestasgenericas(DERIVOMASC),\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_PROCESO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||PROCESOID  NOT IN (SELECT ENTIDADID||PERIODO||PROCESOID FROM TR_SENAP_PROCESO))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                fila.add(resul.getString(23)); // Añadir la dieciseisava columna
                fila.add(resul.getString(24)); // Añadir la dieciseisava columna
                fila.add(resul.getString(25)); // Añadir la dieciseisava columna
                fila.add(resul.getString(26)); // Añadir la dieciseisava columna
                fila.add(resul.getString(27)); // Añadir la dieciseisava columna
                fila.add(resul.getString(28)); // Añadir la dieciseisava columna
                fila.add(resul.getString(29)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_MASC(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "MASCID,\n"
                + "MASCID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL PROCESOID,\n"
                + "CATFISCALIAID,\n"
                + "IMPUTADOID,\n"
                + "IMPUTADOID_FISCALIA,\n"
                + "CONVER_AUTORIDADDERIVAMASC(CATAUTORIDADDERIVAMASCID),\n"
                + "FECHADERIVAMASC,\n"
                + "CONVER_TIPOMASC(CATTIPOMASCID),\n"
                + "CONVER_TIPOCUMPLIMIENTO(CATTIPOCUMPLIMIENTOID),\n"
                + "FECHACUMPLIMENTOMASC,\n"
                + "MONTOREPARACIONDANIO,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_MASC  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||MASCID  NOT IN (SELECT ENTIDADID||PERIODO||MASCID FROM TR_SENAP_MASC))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

 /*   public ArrayList<ArrayList<String>> TMP_SENAP_ETAPAINVESTIGACION(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "ETAPAINVESTIGACIONID,\n"
                + "CARPETAID,\n"
                + "CARPETAID_FISCALIA,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "NULL IMPUTADODELITOID,\n"
                + "NULL IMPUTADODELITOID_FISCALIA,\n"
                + "CONVER_ETAPAPROCESAL(CATETAPAPROCESALID),\n"
                + "CATFISCALIAID,\n"
                + "NULL ESTATUSPROCESOID,\n"
                + "NULL FECHAESTATUSPROCESO,\n"
                + "REGISTROID,\n"
                + "FECHAETAPA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_ETAPAINVESTIGACION  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "'  and "
                + " (ENTIDADID||PERIODO||ETAPAINVESTIGACIONID  NOT IN (SELECT ENTIDADID||PERIODO||ETAPAINVESTIGACIONID FROM TR_SENAP_ETAPAINVESTIGACION))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }*/

    public ArrayList<ArrayList<String>> TMP_SENAP_MANDAMIENTOSJUDICIALES(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "MANDAMIENTOJUDICIALID,\n"
                + "MANDAMIENTOJUDICIALID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "FECHSOLMANDJUD,\n"
                + "CONVER_TIPOMANDAMIENTO(CATTIPOMANDAMIENTOID),\n"
                + "FECHLIBMAND,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "CONVER_ESTATUSMANDAMIENTOJUDICIAL(ULTESTMAND),\n"
                + "FECHULTESTMAND,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_MANDAMIENTOSJUDICIALES  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||MANDAMIENTOJUDICIALID  NOT IN (SELECT ENTIDADID||PERIODO||MANDAMIENTOJUDICIALID FROM TR_SENAP_MANDAMIENTOSJUDICIALES))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_INVESTIGACIONCOMPLEMENTARIA(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "INVESTIGACIONCOMPLEMENTARIAID,\n"
                + "INVESTIGACIONCOMPLEMENTARIAID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "CONVER_RESPUESTASGENERICAS(FORMULACIONIMPUTACION),\n"
                + "FECHFORMIMP,\n"
                + "CONVER_RESOLUCIONAUTOVINCULACIONPRO(CATVINCPROSID),\n"
                + "FECHVINCPROS,\n"
                + "CONVER_RESPUESTASGENERICAS(MEDIDACAUTELAR),\n"
                + "FECHCIERREINV,\n"
                + "CONVER_RESPUESTASGENERICAS(ACUSACION),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "CATFISCALIAID,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_INVESTIGACIONCOMPLEMENTARIA  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||INVESTIGACIONCOMPLEMENTARIAID  NOT IN (SELECT ENTIDADID||PERIODO||INVESTIGACIONCOMPLEMENTARIAID FROM TR_SENAP_INVESTIGACIONCOMPLEMENTARIA))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                fila.add(resul.getString(23)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_MEDIDASCAUTELARES(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "MEDIDACAUTELARID,\n"
                + "MEDIDACAUTELARID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "CONVER_MEDIDA(CATMEDIDAID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_MEDIDASCAUTELARES  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||MEDIDACAUTELARID  NOT IN (SELECT ENTIDADID||PERIODO||MEDIDACAUTELARID FROM TR_SENAP_MEDIDASCAUTELARES))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_ETAPAINTERMEDIA(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "ETAPAINTERMEDIAID,\n"
                + "ETAPAINTERMEDIAID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "FECHAESCRITOACUSACION,\n"
                + "CONVER_RESPUESTASGENERICAS(HUBOCELAUDINTER),\n"
                + "FECHACELAUDINTER,\n"
                + "CONVER_RESPUESTASGENERICAS(PRESMEDPRUEBA),\n"
                + "MEDIOSPRUEBA,\n"
                + "CONVER_RESPUESTASGENERICAS(CONTACUPROB),\n"
                + "CONVER_RESPUESTASGENERICAS(DICTAUTAPERJUICIO),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_ETAPAINTERMEDIA  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||ETAPAINTERMEDIAID  NOT IN (SELECT ENTIDADID||PERIODO||ETAPAINTERMEDIAID FROM TR_SENAP_ETAPAINTERMEDIA))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                fila.add(resul.getString(23)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_SOBRESEIMIENTO(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "SOBRESEIMIENTOID,\n"
                + "SOBRESEIMIENTOID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "CONVER_ETAPASOBRESEIMIENTO(CATETAPASOBRESEIMIENTOID),\n"
                + "CONVER_CAUSASSOBRESEIMIENTO(CATCAUSASSOBRESEIMIENTOID),\n"
                + "FECHADICTOSOBRESEIMIENTO,\n"
                + "CONVER_TIPOSOBRESEIMIENTO(CATTIPOSOBRESEIMIENTOID),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_SOBRESEIMIENTO  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||SOBRESEIMIENTOID  NOT IN (SELECT ENTIDADID||PERIODO||SOBRESEIMIENTOID FROM TR_SENAP_SOBRESEIMIENTO))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_SUSPENSIONCONDICIONAL(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "SUSPENSIONCONDICIONALID,\n"
                + "SUSPENSIONCONDICIONALID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "FECHADICSUSCONPRO,\n"
                + "CONVER_TIPOSOBRESEIMIENTO(CATSUSCONID),\n"
                + "CONVER_TIPOCONDICIONESIMPUESTASSUSP(CATCONDIMPUSUSPENCONDIPROCESOID),\n"
                + "CONVER_RESPUESTASGENERICAS(HUBOREAPERTURAPROCESO),\n"
                + "FECHAREAPERTURAPROCESO,\n"
                + "FECHACUMPSUSPENCONDIPROCESO,\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_SUSPENSIONCONDICIONAL  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||SUSPENSIONCONDICIONALID  NOT IN (SELECT ENTIDADID||PERIODO||SUSPENSIONCONDICIONALID FROM TR_SENAP_SUSPENSIONCONDICIONAL))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> TMP_SENAP_SENTENCIA(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        String sql = "SELECT \n"
                + "ENTIDADID,\n"
                + "SENTENCIAID,\n"
                + "SENTENCIAID_FISCALIA,\n"
                + "NULL CARPETAID,\n"
                + "NULL CARPETAID_FISCALIA,\n"
                + "NULL IMPUTADOID,\n"
                + "NULL IMPUTADOID_FISCALIA,\n"
                + "CATFISCALIAID,\n"
                + "PROCESOID,\n"
                + "PROCESOID_FISCALIA,\n"
                + "FECHACELEBAUDIJUICIO,\n"
                + "CONVER_TIPOPRUEBASDESAHOGADAS(CATPRUEBADESAHOGAID),\n"
                + "CONVER_RESPUESTASGENERICAS(SENTENCIADERPROCABREV),\n"
                + "FECHADICTPROCABREV,\n"
                + "FECHADICTOSENTENCIA,\n"
                + "CONVER_TIPOSENTENCIA(CATTIPOSENTENCIAID),\n"
                + "TIEMPOPRISION,\n"
                + "MONTOREPARACIONDANIOIMPUESTA,\n"
                + "CONVER_RESPUESTASGENERICAS(SENTENCIAENCUENTRAFIRME),\n"
                + "BORRADO,\n"
                + "FECHAALTA,\n"
                + "USUARIOALTA,\n"
                + "FECHACORTE,\n"
                + "FECHAACTUALIZACION,\n"
                + "PERIODO\n"
                + "  FROM RES_TMP_SENAP_SENTENCIA  WHERE ENTIDADID='" + Entidad + "' and PERIODO='" + Periodo + "' and "
                + " (ENTIDADID||PERIODO||SENTENCIAID  NOT IN (SELECT ENTIDADID||PERIODO||SENTENCIAID FROM TR_SENAP_SENTENCIA))";
        System.out.println(sql);
        ResultSet resul = conexion.consultar(sql);

        try {
            while (resul.next()) {
                ArrayList<String> fila = new ArrayList<>();
                fila.add(resul.getString(1)); // Añadir la primera columna
                fila.add(resul.getString(2)); // Añadir la segunda columna
                fila.add(resul.getString(3)); // Añadir la tercera columna
                fila.add(resul.getString(4)); // Añadir la cuarta columna
                fila.add(resul.getString(5)); // Añadir la quinta columna
                fila.add(resul.getString(6)); // Añadir la sexta columna
                fila.add(resul.getString(7)); // Añadir la séptima columna
                fila.add(resul.getString(8)); // Añadir la octava columna
                fila.add(resul.getString(9)); // Añadir la novena columna
                fila.add(resul.getString(10)); // Añadir la décima columna
                fila.add(resul.getString(11)); // Añadir la onceava columna
                fila.add(resul.getString(12)); // Añadir la doceava columna
                fila.add(resul.getString(13)); // Añadir la treceava columna
                fila.add(resul.getString(14)); // Añadir la catorceava columna
                fila.add(resul.getString(15)); // Añadir la quinceava columna
                fila.add(resul.getString(16)); // Añadir la dieciseisava columna
                fila.add(resul.getString(17)); // Añadir la dieciseisava columna
                fila.add(resul.getString(18)); // Añadir la dieciseisava columna
                fila.add(resul.getString(19)); // Añadir la dieciseisava columna
                fila.add(resul.getString(20)); // Añadir la dieciseisava columna
                fila.add(resul.getString(21)); // Añadir la dieciseisava columna
                fila.add(resul.getString(22)); // Añadir la dieciseisava columna
                fila.add(resul.getString(23)); // Añadir la dieciseisava columna
                fila.add(resul.getString(24)); // Añadir la dieciseisava columna
                fila.add(resul.getString(25)); // Añadir la dieciseisava columna
                arrayList.add(fila); // Agregar la fila a la lista principal
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryTRSENAP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resul != null) {
                    resul.close();
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

}
