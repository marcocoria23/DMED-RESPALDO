/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.org.inegi.QuerysGeneralOracle.SENAP;

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
public class QueryConteosSenap {

    ConOracle conexion = new ConOracle();
    ResultSet resul;
    String sql = "", sql2 = "";
    ArrayList<String[]> Array;

// ASPECTOS GENERALES
    public ArrayList ConteosEntidadAnioT(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT (CARPETAID) AS CARPETAID \n"
                + "FROM (\n"
                + "  SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CARPETAID, CI.PERIODO\n"
                + "  FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "  INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "    ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "  WHERE CI.ENTIDADID = '" + entidad + "' AND CI.PERIODO = '" + Periodo + "'\n"
                + ") \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2\n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ANIO"),
                    resul.getString("NOMBREENTIDADFEDERATIVA"),
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosImputadoPlantillaCarpeta(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CANTIDADIMPUTADOS IS NULL THEN 0 ELSE CANTIDADIMPUTADOS END CANTIDADIMPUTADOS\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, SUM(CANTIDADIMPUTADOS) AS CANTIDADIMPUTADOS \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CANTIDADIMPUTADOS\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'  ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CANTIDADIMPUTADOS")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosImputadoPlantillaImputado(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN IMPUTADOID IS NULL THEN 0 ELSE IMPUTADOID END IMPUTADOID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT (IMPUTADOID) AS IMPUTADOID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IMP.IMPUTADOID, CI.PERIODO\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID=EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_IMPUTADO IMP\n"
                + "      ON CI.CARPETAID = IMP.CARPETAID AND CI.ENTIDADID = IMP.ENTIDADID AND CI.PERIODO = IMP.PERIODO\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'   ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDelitosPlantillaCarpeta(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CANTIDADDELITOS IS NULL THEN 0 ELSE CANTIDADDELITOS END CANTIDADDELITOS\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, SUM(CANTIDADDELITOS) AS CANTIDADDELITOS \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CANTIDADDELITOS\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'   ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CANTIDADDELITOS")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDelitosPlantillaDelitos(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN DELITOID IS NULL THEN 0 ELSE DELITOID END DELITOID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(DELITOID) AS DELITOID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DEL.DELITOID, CI.PERIODO\n"
                + "    FROM RES_TR_SENAP_DELITOS DEL\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON CI.CARPETAID = DEL.CARPETAID AND CI.ENTIDADID = DEL.ENTIDADID AND CI. CARPETAID = DEL.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'  ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("DELITOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosVictimasPlantillaCarpeta(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CANTIDADVICTIMAS IS NULL THEN 0 ELSE CANTIDADVICTIMAS END CANTIDADVICTIMAS\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, SUM(CANTIDADVICTIMAS) AS CANTIDADVICTIMAS \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CANTIDADVICTIMAS\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'  ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CANTIDADVICTIMAS")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosVictimasPlantillaVictimas(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN VICTIMAID IS NULL THEN 0 ELSE VICTIMAID END VICTIMAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(VICTIMAID) AS VICTIMAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, VIC.VICTIMAID, CI.PERIODO\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID=EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_VICTIMAS VIC\n"
                + "      ON CI.CARPETAID = VIC.CARPETAID AND CI.ENTIDADID = VIC.ENTIDADID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "'  ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("VICTIMAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // ETAPA CARPETA DE INVESTIGACION 
    public ArrayList ConteosPCIEtapaInvestInicial(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CARPETAID, CI.PERIODO, CI.CATETAPAPROCESALID\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID=EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "' \n"
                + "    AND CATETAPAPROCESALID = 1      ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosPCINoIdentificado(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, CI.CARPETAID, CI.PERIODO, CI.CATETAPAPROCESALID\n"
                + "    FROM RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID=EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE CI.ENTIDADID='" + entidad + "' and CI.PERIODO='" + Periodo + "' \n"
                + "    AND CATETAPAPROCESALID = 9      ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // TOTAL DE DETERMINACIONES
    public ArrayList ConteosDeterminacionTotalDet(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'      ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // ESTATUS
    public ArrayList ConteosDeterminacionEstatusTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATESTATUSCARPETAID IN (1,2)   ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionEstatusConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN (\n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATESTATUSCARPETAID = 1    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionEstatusTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATESTATUSCARPETAID = 2    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // TOTAL DE SENTIDO DE LA DETRMINACION
    public ArrayList ConteosDeterminacionSentidoTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID IN (1,2,3,4,5,6,7,8,9,10,11,99) AND DET.CATESTATUSCARPETAID IN (1,2)    ) \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // EJERCICIO DE LA ACCION PENAL
    public ArrayList ConteosDeterminacionEAPTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 1  AND DET.CATESTATUSCARPETAID IN (1,2)    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionEAPConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 1  AND DET.CATESTATUSCARPETAID = 1    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionEAPTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 1  AND DET.CATESTATUSCARPETAID = 2    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // No ejercicio de la acción penal por la actualización de alguna causal de sobreseimiento
    public ArrayList ConteosDeterminacionNOEAPTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 2  AND DET.CATESTATUSCARPETAID IN (1,2)    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionNOEAPConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 2  AND DET.CATESTATUSCARPETAID = 1    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionNOEAPTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 2  AND DET.CATESTATUSCARPETAID = 2    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Archivo temporal
    public ArrayList ConteosDeterminacionATTOTAL(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 3  AND DET.CATESTATUSCARPETAID IN (1,2)    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionATConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 3  AND DET.CATESTATUSCARPETAID = 1     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionATTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 3  AND DET.CATESTATUSCARPETAID = 2     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Abstenerse de investigar cuando se encuentre extinguida la acción penal o la responsabilidad penal de la
    public ArrayList ConteosDeterminacionAIEAPTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 4  AND DET.CATESTATUSCARPETAID IN (1,2)     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAIEAPConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 4  AND DET.CATESTATUSCARPETAID = 1      ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAIEAPTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 4  AND DET.CATESTATUSCARPETAID = 2     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Abstenerse de investigar cuando las conductas no constituyan delito
    public ArrayList ConteosDeterminacionAICCNCDTotal(String entidad, String Periodo) throws SQLException  {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 5  AND DET.CATESTATUSCARPETAID IN (1,2)     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAICCNCDConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 5  AND DET.CATESTATUSCARPETAID = 1     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAICCNCDTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 5  AND DET.CATESTATUSCARPETAID = 2     ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Abstenerse de investigar no identificado
    public ArrayList ConteosDeterminacionAINITotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 6  AND DET.CATESTATUSCARPETAID IN (1,2)      ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAINIConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 6  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAINITramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 6  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Criterios de oportunidad en los que se decretó la extinción de la acción penal
    public ArrayList ConteosDeterminacionCODEAPTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n" +
"CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n" +
"FROM ANIO_CONTEOS P\n" +
"LEFT JOIN ( \n" +
"  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n" +
"  FROM (\n" +
"    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n" +
"    FROM RES_TR_SENAP_DETERMINACION DET\n" +
"    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n" +
"      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n" +
"    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n" +
"      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n" +
"    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n" +
"    AND DET.CATSENDETID = 7  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n" +
"  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n" +
"  ORDER BY ANIO   )S\n" +
"ON P.ANIO = S.ANIO2 \n" +
"ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionCODEAPConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n" +
"CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n" +
"FROM ANIO_CONTEOS P\n" +
"LEFT JOIN ( \n" +
"  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n" +
"  FROM (\n" +
"    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n" +
"    FROM RES_TR_SENAP_DETERMINACION DET\n" +
"    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n" +
"      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n" +
"    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n" +
"      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n" +
"    WHERE DET.ENTIDADID='" + entidad + "' AND DET.PERIODO='" + Periodo + "'\n" +
"    AND DET.CATSENDETID = 7  AND DET.CATESTATUSCARPETAID = 1       ) \n" +
"  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n" +
"  ORDER BY ANIO   )S\n" +
"ON P.ANIO = S.ANIO2\n" +
"ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionCODEAPTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n" +
"CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n" +
"FROM ANIO_CONTEOS P\n" +
"LEFT JOIN ( \n" +
"  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n" +
"  FROM (\n" +
"    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n" +
"    FROM RES_TR_SENAP_DETERMINACION DET\n" +
"    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n" +
"      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n" +
"    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n" +
"      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n" +
"    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n" +
"    AND DET.CATSENDETID = 7  AND DET.CATESTATUSCARPETAID = 2       ) \n" +
"  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n" +
"  ORDER BY ANIO   )S\n" +
"ON P.ANIO = S.ANIO2\n" +
"ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Incompetencia
    public ArrayList ConteosDeterminacionIncompetenciaTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 8  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionIncompetenciaConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 8  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionIncompetenciaTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 8  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Acuerdos reparatorios aprobados por el Ministerio Público en los que se decretó la extinción de la acción penal
    public ArrayList ConteosDeterminacionARAMPDEACTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 9  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionARAMPDEACConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 9  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionARAMPDEACTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 9  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // Acumulación
    public ArrayList ConteosDeterminacionAcumulacionTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 10  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAcumulacionConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 10  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionAcumulacionTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 10  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // OTRO
    public ArrayList ConteosDeterminacionOtroTotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 11  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionOtroConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 11  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionOtroTramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 11  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // NO IDENTIFICADO
    public ArrayList ConteosDeterminacionNOITotal(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 99  AND DET.CATESTATUSCARPETAID IN (1,2)       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionNOIConcluida(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 99  AND DET.CATESTATUSCARPETAID = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosDeterminacionNOITramite(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CARPETAID IS NULL THEN 0 ELSE CARPETAID END CARPETAID\n"
                + "FROM ANIO_CONTEOS P\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CARPETAID) AS CARPETAID \n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHACARINVE) AS ANIO, CI.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, DET.CARPETAID, CI.PERIODO, DET.CATESTATUSCARPETAID, DET.CATSENDETID\n"
                + "    FROM RES_TR_SENAP_DETERMINACION DET\n"
                + "    INNER JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI\n"
                + "      ON DET.ENTIDADID = CI.ENTIDADID AND DET.PERIODO = CI.PERIODO AND DET.CARPETAID = CI.CARPETAID\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON CI.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE DET.ENTIDADID='" + entidad + "' and DET.PERIODO='" + Periodo + "'\n"
                + "    AND DET.CATSENDETID = 99  AND DET.CATESTATUSCARPETAID = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON P.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC ";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CARPETAID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // PROCESOS
    public ArrayList ConteosProcesos(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(PROCESOID) AS PROCESOID\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PROCESOID, P.PERIODO\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID=EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'    ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosCITienenProceso(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT '2023' Anio,COUNT(UNIQUE(PRO.CARPETAID))  total\n"
                + "FROM RES_TR_SENAP_PROCESO pro,RES_TR_SENAP_CARPETAINVESTIGACION CI \n"
                + "WHERE PRO.ENTIDADID=CI.ENTIDADID AND PRO.PERIODO=CI.PERIODO AND CI.CARPETAID=PRO.CARPETAID \n"
                + "  AND CI.ENTIDADID= '" + entidad + "' AND CI.PERIODO = '" + Periodo + "'\n"
                + "  AND EXTRACT(YEAR FROM CI.FECHACARINVE)='2023'\n"
                + "UNION ALL\n"
                + "SELECT  '2024' Anio,COUNT(UNIQUE(PRO.CARPETAID)) total\n"
                + "FROM RES_TR_SENAP_PROCESO PRO,RES_TR_SENAP_CARPETAINVESTIGACION CI  \n"
                + "WHERE PRO.ENTIDADID=CI.ENTIDADID AND PRO.PERIODO=CI.PERIODO AND CI.CARPETAID=PRO.CARPETAID\n"
                + "  AND PRO.ENTIDADID= '" + entidad + "' AND PRO.PERIODO = '" + Periodo + "' \n"
                + "  AND EXTRACT(YEAR FROM CI.FECHACARINVE)='2024' \n"
                + "  AND PRO.CARPETAID NOT IN (\n"
                + "    SELECT UNIQUE(PRO.CARPETAID)\n"
                + "    FROM RES_TR_SENAP_PROCESO PRO,RES_TR_SENAP_CARPETAINVESTIGACION CI \n"
                + "    WHERE PRO.ENTIDADID=CI.ENTIDADID AND PRO.PERIODO=CI.PERIODO AND CI.CARPETAID=PRO.CARPETAID \n"
                + "      AND CI.ENTIDADID= '" + entidad + "' AND CI.PERIODO = '" + Periodo + "'\n"
                + "      AND EXTRACT(YEAR FROM CI.FECHACARINVE)='2023')";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("total")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // AUDIENCIA INICIAL
    public ArrayList ConteosAudienciaInicialSI(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN HUBOCELEBRACIONAUDIENCIAINICIAL IS NULL THEN 0 ELSE HUBOCELEBRACIONAUDIENCIAINICIAL END HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(HUBOCELEBRACIONAUDIENCIAINICIAL) AS HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, P.HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND P.HUBOCELEBRACIONAUDIENCIAINICIAL = 1       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("HUBOCELEBRACIONAUDIENCIAINICIAL")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosAudienciaInicialNO(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN HUBOCELEBRACIONAUDIENCIAINICIAL IS NULL THEN 0 ELSE HUBOCELEBRACIONAUDIENCIAINICIAL END HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(HUBOCELEBRACIONAUDIENCIAINICIAL) AS HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, P.HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND P.HUBOCELEBRACIONAUDIENCIAINICIAL = 2       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("HUBOCELEBRACIONAUDIENCIAINICIAL")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosAudienciaInicialNOIdentificado(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN HUBOCELEBRACIONAUDIENCIAINICIAL IS NULL THEN 0 ELSE HUBOCELEBRACIONAUDIENCIAINICIAL END HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(HUBOCELEBRACIONAUDIENCIAINICIAL) AS HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, P.HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND P.HUBOCELEBRACIONAUDIENCIAINICIAL = 9       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("HUBOCELEBRACIONAUDIENCIAINICIAL")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosAudienciaInicialSINReferencia(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN HUBOCELEBRACIONAUDIENCIAINICIAL IS NULL THEN 0 ELSE HUBOCELEBRACIONAUDIENCIAINICIAL END HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(HUBOCELEBRACIONAUDIENCIAINICIAL) AS HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, P.HUBOCELEBRACIONAUDIENCIAINICIAL\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    INNER JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND P.HUBOCELEBRACIONAUDIENCIAINICIAL = 0       ) \n"
                + "  GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("HUBOCELEBRACIONAUDIENCIAINICIAL")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // INVESTIGACION COMPLEMENTARIA
    public ArrayList ConteosProcesosIC(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    where P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'     ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // FORMULACION DE LA IMPUTACION
    public ArrayList ConteosProcesosSIFI(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.FORMULACIONIMPUTACION = 1       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNOFI(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.FORMULACIONIMPUTACION = 2       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNOIdentificadaFI(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.FORMULACIONIMPUTACION = 9       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosSINReferenciaFI(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.FORMULACIONIMPUTACION = 0       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // RESOLUCION DE LA AUTO VINCULACION A PROCESO
    public ArrayList ConteosProcesosSIRAVP(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CATVINCPROSID IS NULL THEN 0 ELSE CATVINCPROSID END CATVINCPROSID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CATVINCPROSID) AS CATVINCPROSID\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, IC.CATVINCPROSID, IC.PROCESOID\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    LEFT JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    LEFT JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.PROCESOID = IC.PROCESOID AND P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO \n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.CATVINCPROSID = 1       ) \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CATVINCPROSID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNORAVP(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CATVINCPROSID IS NULL THEN 0 ELSE CATVINCPROSID END CATVINCPROSID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CATVINCPROSID) AS CATVINCPROSID\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, IC.CATVINCPROSID, IC.PROCESOID\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    LEFT JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    LEFT JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.PROCESOID = IC.PROCESOID AND P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO \n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.CATVINCPROSID = 2       ) \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CATVINCPROSID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNOIdentificadoRAVP(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CATVINCPROSID IS NULL THEN 0 ELSE CATVINCPROSID END CATVINCPROSID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CATVINCPROSID) AS CATVINCPROSID\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, IC.CATVINCPROSID, IC.PROCESOID\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    LEFT JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    LEFT JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.PROCESOID = IC.PROCESOID AND P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO \n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.CATVINCPROSID = 9       ) \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CATVINCPROSID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosSINReferenciaRAVP(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN CATVINCPROSID IS NULL THEN 0 ELSE CATVINCPROSID END CATVINCPROSID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  SELECT ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, COUNT(CATVINCPROSID) AS CATVINCPROSID\n"
                + "  FROM (\n"
                + "    SELECT EXTRACT(YEAR FROM FECHAINICIO) AS ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, P.PERIODO, IC.CATVINCPROSID, IC.PROCESOID\n"
                + "    FROM RES_TR_SENAP_PROCESO P\n"
                + "    LEFT JOIN TC_SENAP_ENTIDADFEDERATIVA EF \n"
                + "      ON P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    LEFT JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.PROCESOID = IC.PROCESOID AND P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO \n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'  \n"
                + "    AND IC.CATVINCPROSID = 0    ) \n"
                + "GROUP BY ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "ORDER BY ANIO   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("CATVINCPROSID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // FORMULACION DE LA ACUSACION
    public ArrayList ConteosProcesosSIFA(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN ACUSACION IS NULL THEN 0 ELSE ACUSACION END ACUSACION\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (ACUSACION) as ACUSACION \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO, IC.ACUSACION\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND IC.ACUSACION = 1       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ACUSACION")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNOFA(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN ACUSACION IS NULL THEN 0 ELSE ACUSACION END ACUSACION\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (ACUSACION) as ACUSACION \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO, IC.ACUSACION\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND IC.ACUSACION = 2       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ACUSACION")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosNOIdentificadoFA(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN ACUSACION IS NULL THEN 0 ELSE ACUSACION END ACUSACION\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (ACUSACION) as ACUSACION \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO, IC.ACUSACION\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND IC.ACUSACION = 9       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ACUSACION")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosProcesosSINReferenciaFA(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN ACUSACION IS NULL THEN 0 ELSE ACUSACION END ACUSACION\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (ACUSACION) as ACUSACION \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, IC.PROCESOID, P.PERIODO, IC.ACUSACION\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_INVESTIGACIONCOMPLEMENTARIA IC\n"
                + "      ON P.ENTIDADID = IC.ENTIDADID AND P.PERIODO = IC.PERIODO AND P.PROCESOID = IC.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND IC.ACUSACION = 0       ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("ACUSACION")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    // ETAPA INTERMEDIA
    public ArrayList ConteosEtapaIntermediaProcesos(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN PROCESOID IS NULL THEN 0 ELSE PROCESOID END PROCESOID\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (PROCESOID) as PROCESOID \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, EI.PROCESOID, P.PERIODO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_ETAPAINTERMEDIA EI\n"
                + "      ON P.ENTIDADID = EI.ENTIDADID AND P.PERIODO = EI.PERIODO AND P.PROCESOID = EI.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'     ) \n"
                + "group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("PROCESOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosEtapaSIAAJO(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN DICTAUTAPERJUICIO IS NULL THEN 0 ELSE DICTAUTAPERJUICIO END DICTAUTAPERJUICIO\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (DICTAUTAPERJUICIO) as DICTAUTAPERJUICIO \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, EI.PROCESOID, P.PERIODO, EI.DICTAUTAPERJUICIO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_ETAPAINTERMEDIA EI\n"
                + "      ON P.ENTIDADID = EI.ENTIDADID AND P.PERIODO = EI.PERIODO AND P.PROCESOID = EI.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND EI.DICTAUTAPERJUICIO = 1   ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("DICTAUTAPERJUICIO")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosEtapaNOAAJO(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN DICTAUTAPERJUICIO IS NULL THEN 0 ELSE DICTAUTAPERJUICIO END DICTAUTAPERJUICIO\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (DICTAUTAPERJUICIO) as DICTAUTAPERJUICIO \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, EI.PROCESOID, P.PERIODO, EI.DICTAUTAPERJUICIO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_ETAPAINTERMEDIA EI\n"
                + "      ON P.ENTIDADID = EI.ENTIDADID AND P.PERIODO = EI.PERIODO AND P.PROCESOID = EI.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND EI.DICTAUTAPERJUICIO = 2   ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("DICTAUTAPERJUICIO")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosEtapaNOIdentificadoAAJO(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN DICTAUTAPERJUICIO IS NULL THEN 0 ELSE DICTAUTAPERJUICIO END DICTAUTAPERJUICIO\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (DICTAUTAPERJUICIO) as DICTAUTAPERJUICIO \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, EI.PROCESOID, P.PERIODO, EI.DICTAUTAPERJUICIO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_ETAPAINTERMEDIA EI\n"
                + "      ON P.ENTIDADID = EI.ENTIDADID AND P.PERIODO = EI.PERIODO AND P.PROCESOID = EI.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND EI.DICTAUTAPERJUICIO = 9   ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("DICTAUTAPERJUICIO")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    public ArrayList ConteosEtapaSINReferenciaAAJO(String entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA,\n"
                + "CASE WHEN DICTAUTAPERJUICIO IS NULL THEN 0 ELSE DICTAUTAPERJUICIO END DICTAUTAPERJUICIO\n"
                + "FROM ANIO_CONTEOS AC\n"
                + "LEFT JOIN ( \n"
                + "  select ANIO AS ANIO2, ENTIDADID, NOMBREENTIDADFEDERATIVA, count (DICTAUTAPERJUICIO) as DICTAUTAPERJUICIO \n"
                + "  from (\n"
                + "    select EXTRACT(YEAR FROM FECHAINICIO) as ANIO, P.ENTIDADID, EF.NOMBREENTIDADFEDERATIVA, EI.PROCESOID, P.PERIODO, EI.DICTAUTAPERJUICIO\n"
                + "    from RES_TR_SENAP_PROCESO P\n"
                + "    inner join TC_SENAP_ENTIDADFEDERATIVA EF\n"
                + "      on P.ENTIDADID = EF.CATENTIDADFEDERATIVAID\n"
                + "    INNER JOIN RES_TR_SENAP_ETAPAINTERMEDIA EI\n"
                + "      ON P.ENTIDADID = EI.ENTIDADID AND P.PERIODO = EI.PERIODO AND P.PROCESOID = EI.PROCESOID\n"
                + "    WHERE P.ENTIDADID='" + entidad + "' and P.PERIODO='" + Periodo + "'   \n"
                + "    AND EI.DICTAUTAPERJUICIO = 0   ) \n"
                + "  group by ANIO, ENTIDADID, NOMBREENTIDADFEDERATIVA\n"
                + "  order by anio   )S\n"
                + "ON AC.ANIO = S.ANIO2 \n"
                + "ORDER BY ANIO ASC";
        System.out.println(sql);
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("DICTAUTAPERJUICIO")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

}
