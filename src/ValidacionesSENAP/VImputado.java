/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValidacionesSENAP;

import Pantallas_SENAP.IntegraTMP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.inegi.conexion.SENAP.ConOracle;

/**
 *
 * @author LAURA.MEDINAJ
 */
//VALIDACIONES DE LA TALA IMPUTADODELITOS
public class VImputado {

    ConOracle conexion = new ConOracle();
    ResultSet resul;
    String sql = "", sql2 = "";
    ArrayList<String[]> Array;
    public static String Query = "";

    //ReglaDMED. EDADMOMENTOSUCEDIERONHECHOS-(Edad al momento que sucedieron los hechos) No debe ser Menor a -1
    public ArrayList EDADMENOR1(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT * FROM RES_TR_SENAP_IMPUTADO\n"
                + "WHERE EDADMOMENTOSUCEDIERONHECHOS < -1  AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo  Edad al momento que sucedieron los hechos (columna K) EDADMOMENTOSUCEDIERONHECHOS debido que no pueden registrar con valor cero o nulo.
    public ArrayList EDADNOTNULL(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, IMPUTADOID, CARPETAID, PERIODO, EDADMOMENTOSUCEDIERONHECHOS\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where (EDADMOMENTOSUCEDIERONHECHOS = 0 or EDADMOMENTOSUCEDIERONHECHOS is null)\n"
                + "AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. La relación entre los campos  CatEntidadFederativa(Entidad DOMICILIO) y CATMUNICIPIOID (Municipio DOMICILIO) de la plantilla Imputado, no coinciden con la relación de los campos CATENTIDADFEDERATIVAID y CATMUNICIPIODEMARCACIONTERRITORIALID del catálogo SENAP_MUNICIPIODEMARCACIONTERRITOR
    public ArrayList RelacionMunicipioDomicilio(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (\n"
                + "select UBICACIONID,CATDEMARCACIONID, CATENTFEDID,CATSUBPROREFID, tc.CATMUNICIPIODEMARCACIONTERRITORIALID as catalogo, ub.periodo, ub.ENTIDADID from RES_tr_senap_direccionubicacion ub\n"
                + "left join TC_SENAP_MUNICIPIODEMARCACIONTERRITOR tc on tc.CATMUNICIPIODEMARCACIONTERRITORIALID=ub.CATDEMARCACIONID and tc.catentidadfederativaid=ub.CATENTFEDID)\n"
                + "where catalogo is null  AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "') and  CATSUBPROREFID in (3)";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("UBICACIONID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. La relación entre los campos  CatEntidadFederativa(Entidad OCURRENCIA DEL DELITO) y CATMUNICIPIOID (Municipio OCURRENCIA DEL DELITO) de la plantilla Imputado, no coinciden con la relación de los campos CATENTIDADFEDERATIVAID y CATMUNICIPIODEMARCACIONTERRITORIALID del catálogo SENAP_MUNICIPIODEMARCACIONTERRITOR
    public ArrayList RelacionMunicipioOcurrencia(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (\n"
                + "select UBICACIONID,CATDEMARCACIONID, CATENTFEDID,CATSUBPROREFID, tc.CATMUNICIPIODEMARCACIONTERRITORIALID as catalogo, ub.periodo, ub.ENTIDADID from RES_tr_senap_direccionubicacion ub\n"
                + "left join TC_SENAP_MUNICIPIODEMARCACIONTERRITOR tc on tc.CATMUNICIPIODEMARCACIONTERRITORIALID=ub.CATDEMARCACIONID and tc.catentidadfederativaid=ub.CATENTFEDID)\n"
                + "where catalogo is null  AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "') and  CATSUBPROREFID in (6)";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("UBICACIONID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. La relación entre los campos  CatEntidadFederativa(Entidad DE NACIMIENTO) y CATMUNICIPIOID (Municipio DE NACIMIENTO) de la plantilla Imputado, no coinciden con la relación de los campos CATENTIDADFEDERATIVAID y CATMUNICIPIODEMARCACIONTERRITORIALID del catálogo SENAP_MUNICIPIODEMARCACIONTERRITOR
    public ArrayList RelacionMunicipioNacimiento(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select * from (\n"
                + "select IMPUTADOID, CARPETAID,UBICACIONID,DIRECCIONUBICACIONID,CATMUNICIPIOID, CATENTIDADNACIMIENTOID, tc.CATMUNICIPIODEMARCACIONTERRITORIALID as catalogo, imp.periodo, imp.ENTIDADID from RES_tr_senap_imputado imp\n"
                + "left join TC_SENAP_MUNICIPIODEMARCACIONTERRITOR tc on tc.CATMUNICIPIODEMARCACIONTERRITORIALID=imp.CATMUNICIPIOID and tc.catentidadfederativaid=imp.CATENTIDADNACIMIENTOID)\n"
                + "where catalogo is null and (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar EDADMOMENTOSUCEDIERONHECHOS-Edad al momento que sucedieron los hechos ya qye es mayor a 106 años
    public ArrayList EDADHECHOS(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT IMPUTADOID,EDADMOMENTOSUCEDIERONHECHOS FROM RES_TR_SENAP_IMPUTADO\n"
                + "WHERE EDADMOMENTOSUCEDIERONHECHOS>106 and (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    CatTipoPersonaImputadaID ----------------***
    //ReglaNegocio 7.14. Si en el campo Tipo de persona imputada (columna G), es seleccionada la opción “Persona física”, deberá estar inscrita información en todos los campos de esta plantilla, con excepción del campo, Tipo de persona moral (columna H)
    public ArrayList TipoPerImputada_PerFisica(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,CATTIPOPERSONAIMPUTADAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where CATTIPOPERSONAIMPUTADAID=1\n"
                + "and CATTIPOPERSONAMORALID is null and \n"
                + "(\n"
                + "\n"
                + "NOMBREIMPUTADO is null or ALIASAPODO is null or CURP is null or\n"
                + "FECHANACIMIENTO is null or EDADMOMENTOSUCEDIERONHECHOS is null or\n"
                + "CATSEXOID is null or CATSITUACIONCONYUGALID is null or\n"
                + "MEDIAFILIACION is null or CATNACIONALIDADID is null or\n"
                + "CATSITUACIONMIGRATORIAID is null or CATPAISNACIMIENTOID is null or\n"
                + "CATENTIDADNACIMIENTOID is null or CATMUNICIPIOID is null or\n"
                + "CATPAISRESIDENCIAHABITUALID is null or UBICACIONID is null or \n"
                + "NUMEROTELEFONICO is null or HABLAESPANIOL is null or \n"
                + "HABLALENGUAEXTRANJERA is null or CATTIPOLENGUAEXTRANJERAID is null or \n"
                + "HABLALENGUAINDIGENA is null or CATTIPOLENGUAINDIGENAID is null or\n"
                + "UTILIZOTRADUCTORINTERPRETE is null or PRESENTADISCAPACIDAD is null or\n"
                + "CATTIPODISCAPACIDADID is null or MEDIOTECDISCAPACIDAD is null or \n"
                + "PERTENECEPOBLACIONINDIGENA is null or CATTIPOPOBLACIONINDIGENAID is null or\n"
                + "PERTENECEPOBLACIONLGBTTTI is null or CATTIPOLGBTTTIID is null or \n"
                + "PERTENECEPOBLACIONSITUACIONCALLE is null or SABELEERESCRIBIR is null or \n"
                + "CATNIVELESCOLARIDADID is null or CATOCUPACIONID is null or \n"
                + "RANGOINGRESOMENSUALNETO is null or IMPUTADOPERTENECEGRUPODELICTIVO is null or \n"
                + "GRUPODELICTIVO is null or CATCOMISIONDELITOID is null or \n"
                + "ANTECEDENTESPENALES is null or CATESTADOPSICOFISICOID is null or \n"
                + "CONTODEFENSOR is null or CATTIPODEFENSORID is null or \n"
                + "CATFORMAPROCESOID is null or FECHADETENCION is null or \n"
                + "HORADETENCION is null or CATTIPODETENCIONID is null or \n"
                + "CATAUTORIDADCARGODETENCIONID is null or IMPUTADOFUELIBERADO is null or \n"
                + "FECHAAUDIENCIACONTROLDETENCION is null or CALIFICOLEGALDETENCION is null or \n"
                + "CARPETAID_FISCALIA is null or DIRECCIONUBICACIONID is null\n"
                + ") AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.14. Si en el campo Tipo de persona imputada (columna G), es seleccionadaa la opción “Persona física”, deberá estar inscrita información en todos los campos de esta plantilla, Tipo de persona moral (columna H) debe de estar vacío
    public ArrayList NoTipoPerImputada_PerFisica(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,CATTIPOPERSONAIMPUTADAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where CATTIPOPERSONAIMPUTADAID=1\n"
                + "and CATTIPOPERSONAMORALID is not null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.15. Si en el campo Tipo de persona imputada (columna G), es seleccionada la opción “Persona moral”, se deberá permitir el registro sólo de los campos Tipo de persona moral (columna H), ¿Contó con persona defensora? (columna BE  ) y Tipo de persona defensora (columna BF)  
    public ArrayList TipoPerImputada_PerMoral(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,CATTIPOPERSONAIMPUTADAID\n"
                + "FROM  RES_TR_SENAP_IMPUTADO\n"
                + "where CATTIPOPERSONAIMPUTADAID=2 and \n"
                + "(CATTIPOPERSONAMORALID is null or \n"
                + "CONTODEFENSOR is null or CATTIPODEFENSORID is null )AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")
                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    EdadMomentoSucedieronHechos ----------------***
    //ReglaNegocio 7.3. El campo Edad al momento que sucedieron los hechos (columna K) deberá ser menor o igual a la edad calculada a partir del campo Fecha de nacimiento (columna J) con respecto a la fecha actual
    public ArrayList EdadHechos(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID,IMPUTADOID,CARPETAID,PERIODO, EDADMOMENTOSUCEDIERONHECHOS, años_actual from (\n"
                + "SELECT ENTIDADID, IMPUTADOID, CARPETAID, PERIODO,\n"
                + "EDADMOMENTOSUCEDIERONHECHOS,\n"
                + "EXTRACT (YEAR FROM SYSDATE)\n"
                + "-\n"
                + "EXTRACT (YEAR FROM FECHANACIMIENTO)  as años_actual \n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO) where EDADMOMENTOSUCEDIERONHECHOS>años_actual AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    HablaLenguaExtranjera ----------------***
    //ReglaNegocio 7.4. Si en el campo ¿Habla lengua extranjera? (columna AI) es seleccionada la opción “Sí”, el campo Tipo de lengua extranjera (columna AJ) no debe de estar vacío
    public ArrayList HablaLenguaExtranjera(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "HABLALENGUAEXTRANJERA,CATTIPOLENGUAEXTRANJERAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where HABLALENGUAEXTRANJERA=1 and CATTIPOLENGUAEXTRANJERAID is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.4. Si el campo ¿Habla lengua extranjera? (columna AI) es diferente de la opción “Sí”, el campo Tipo de lengua extranjera (columna AJ) no debe de contener información
    public ArrayList NoHablaLenguaExtranjera(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "HABLALENGUAEXTRANJERA,CATTIPOLENGUAEXTRANJERAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where HABLALENGUAEXTRANJERA<>1 and CATTIPOLENGUAEXTRANJERAID is not null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    HablaLenguaIndigena ----------------***
    //ReglaNegocio 7.5. Si en el campo ¿Habla lengua indígena? (columna AK) es selecconada la opción “Sí”, el campo Tipo de lengua indígena (columna AL) no puede venir vacio
    public ArrayList HablaLenguaIndigena(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "HABLALENGUAINDIGENA,CATTIPOLENGUAINDIGENAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where HABLALENGUAINDIGENA=1 and CATTIPOLENGUAINDIGENAID is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.5. Si en el campo ¿Habla lengua indígena? (columna AK) es selecconada la opción “Sí”, el campo Tipo de lengua indígena (columna AL) no deberá venir con valor
    public ArrayList NoHablaLenguaIndigena(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "HABLALENGUAINDIGENA,CATTIPOLENGUAINDIGENAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where HABLALENGUAINDIGENA<>1 and CATTIPOLENGUAINDIGENAID is not null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    PresentaDiscapacidad ----------------***
    //ReglaNegocio 7.6. Si en el campo ¿Presenta alguna condición de discapacidad? (columna AN) es selecconada la opción “Sí”,  los campos Condición de discapacidad (columna AO) y ¿Utilizó intérprete y/o medio tecnológico por discapacidad? (columna AP) no pueden estar vacíos.
    public ArrayList PresentaDiscapacidad(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PRESENTADISCAPACIDAD,CATTIPODISCAPACIDADID,MEDIOTECDISCAPACIDAD\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where PRESENTADISCAPACIDAD=1 and (CATTIPODISCAPACIDADID is null or MEDIOTECDISCAPACIDAD is null) AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.6. Si el campo ¿Presenta alguna condición de discapacidad? (columna AN) es diferente a la opción “SÍ”,  los campos Condición de discapacidad (columna AO) y ¿Utilizó intérprete y/o medio tecnológico por discapacidad? (columna AP) no deberán contener información
    public ArrayList NoPresentaDiscapacidad(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PRESENTADISCAPACIDAD,CATTIPODISCAPACIDADID,MEDIOTECDISCAPACIDAD\n"
                + "FROM RES_TR_SENAP_IMPUTADO\n"
                + "where PRESENTADISCAPACIDAD<>1 and \n"
                + "(CATTIPODISCAPACIDADID is not null  or MEDIOTECDISCAPACIDAD is not null)\n"
                + "AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    PertenecePoblacionIndigena ----------------***
    //ReglaNegocio 7.7. Si en el campo ¿Pertenece a alguna población indígena? (columna AQ) es seleccionada la opción “Sí”, el campo Población indígena a la que pertenece (columna AR) no puede estar vacío
    public ArrayList PerteneceIndigena(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PERTENECEPOBLACIONINDIGENA,CATTIPOPOBLACIONINDIGENAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where PERTENECEPOBLACIONINDIGENA=1 and CATTIPOPOBLACIONINDIGENAID is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.7. Si el campo ¿Pertenece a alguna población indígena? (columna AQ) es diferente a la opción “SÍ”, el campo Población indígena a la que pertenece (columna AR) no puede contener información
    public ArrayList NoPerteneceIndigena(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PERTENECEPOBLACIONINDIGENA,CATTIPOPOBLACIONINDIGENAID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where PERTENECEPOBLACIONINDIGENA<>1 and CATTIPOPOBLACIONINDIGENAID is not null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    PertenecePoblacionLGBTTTI ----------------***
    //ReglaNegocio 7.8. Si el campo Pertenece a alguna población LGBTTTI (columna AS) es requisitado con la opción “Si”, el campo Población LGBTTTI a la que pertenece (columna AT) deberá ser requisitado.
    public ArrayList PerteneceLGBTTTI(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PERTENECEPOBLACIONLGBTTTI,CATTIPOLGBTTTIID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where PERTENECEPOBLACIONLGBTTTI=1 and CATTIPOLGBTTTIID is null\n"
                + "AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.8. Si el campo Pertenece a alguna población LGBTTTI (columna AS) es diferente a la opción “Si”, el campo Población LGBTTTI a la que pertenece (columna AT) no podra traer valor.
    public ArrayList NoPerteneceLGBTTTI(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "PERTENECEPOBLACIONLGBTTTI,CATTIPOLGBTTTIID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where PERTENECEPOBLACIONLGBTTTI<>1 and CATTIPOLGBTTTIID is not null \n"
                + "AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    ImputadoPerteneceGrupoDelictivo ----------------***
    //ReglaNegocio 7.9. Si en el campo ¿El imputado manifestó pertenecer a un probable grupo delictivo? (columna AZ) es seleccionada la opción “Sí”, el campo Nombre del grupo al que pertenece (columna BA) no puede estar vacío
    public ArrayList PerteneceGrupoDelictivo(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "IMPUTADOPERTENECEGRUPODELICTIVO,GRUPODELICTIVO\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where IMPUTADOPERTENECEGRUPODELICTIVO=1 and GRUPODELICTIVO is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.9. Si el campo ¿El imputado manifestó pertenecer a un probable grupo delictivo? (columna AZ) es diferente de la opción “Si”, el campo Nombre del grupo al que pertenece (columna BA) no puede contener información
    public ArrayList NoPerteneceGrupoDelictivo(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "IMPUTADOPERTENECEGRUPODELICTIVO,GRUPODELICTIVO\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where IMPUTADOPERTENECEGRUPODELICTIVO<>1 and GRUPODELICTIVO is not null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    ContoDefensor ----------------***
    //ReglaNegocio 7.10. Si en el campo ¿Contó con persona defensora? (columna BE)  es seleccionada la opción “Sí”, el campo Tipo de defensor (columna BF) no puede estar vacío
    public ArrayList ContoDefensor(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "CONTODEFENSOR,CATTIPODEFENSORID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where CONTODEFENSOR=1 and CATTIPODEFENSORID is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaNegocio 7.10. Si el campo ¿Contó con persona defensora? (columna BE) es diferente de la opción “Sí”, el campo Tipo de defensor (columna BF) no puede contener información
    public ArrayList NoContoDefensor(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,CARPETAID,PERIODO,\n"
                + "CONTODEFENSOR,CATTIPODEFENSORID\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO\n"
                + "where CONTODEFENSOR=1 and CATTIPODEFENSORID is null AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    FechaDetencion ----------------***
    //ReglaNegocio 7.11. Para los asuntos con detenido (Fecha). Si el campo Forma de inicio de la carpeta de investigación (columna Z) de la plantilla Carpeta de investigación tiene el valor “Con detenido” y la Fecha de la detención (columna BH) de la plantilla Imputado-Delito tiene un valor válido, entonces esta deberá ser menor o igual a la Fecha de apertura de la carpeta de investigación (columna X) de la plantilla Carpeta de investigación
    public ArrayList FechaDetencion(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT IMP.ENTIDADID,IMP.IMPUTADOID,IMP.CARPETAID,IMP.PERIODO,\n"
                + "IMP.FECHADETENCION,\n"
                + "IMP.HORADETENCION,\n"
                + "CI.CATFORMINICARINVEID,\n"
                + "CI.FECHACARINVE\n"
                + "FROM RES_TR_SENAP_IMPUTADO IMP LEFT JOIN RES_TR_SENAP_CARPETAINVESTIGACION CI \n"
                + "ON CI.ENTIDADID=IMP.ENTIDADID AND IMP.CARPETAID=CI.CARPETAID AND IMP.PERIODO=CI.PERIODO\n"
                + "where CI.CATFORMINICARINVEID=1 AND IMP.FECHADETENCION IS NOT NULL \n"
                + "AND IMP.FECHADETENCION>CI.FECHACARINVE \n"
                + "AND (IMP.ENTIDADID='" + Entidad + "' AND IMP.PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    HoraDetencion ----------------***
    //ReglaNegocio 7.12. Si el campo Forma de inicio de la carpeta de investigación (columna Z) de la plantilla Carpeta de investigación tiene el valor “Con detenido” y la Hora de detención (columna BI) de la plantilla Imputado-Delito tiene un valor válido, entonces esta deberá ser menor a la Hora de apertura de la carpeta (columna Y) de la plantilla Carpeta de investigación en el entendido de que la Fecha de detención (columna BH) sea igual a la Fecha de apertura de la carpeta de investigación (columna X) de la plantilla Carpeta de investigación
    public ArrayList HoraDetencion(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT IMP.ENTIDADID,IMP.IMPUTADOID,IMP.CARPETAID,IMP.PERIODO,\n"
                + "IMP.FECHADETENCION,\n"
                + "IMP.HORADETENCION,\n"
                + "CI.CATFORMINICARINVEID,\n"
                + "CI.FECHACARINVE,\n"
                + "ci.HORAPECARINVE\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO IMP LEFT JOIN \n"
                + "RES_TR_SENAP_CARPETAINVESTIGACION CI ON CI.ENTIDADID=IMP.ENTIDADID AND IMP.CARPETAID=CI.CARPETAID AND IMP.PERIODO=CI.PERIODO\n"
                + "where CI.CATFORMINICARINVEID=1 \n"
                + "AND IMP.FECHADETENCION=CI.FECHACARINVE and IMP.HORADETENCION>ci.HORAPECARINVE AND (IMP.ENTIDADID='" + Entidad + "' AND IMP.PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //-----campo:    FechaAudienciaControlDetencion ----------------***
    //ReglaNegocio 7.13. La Fecha de audiencia de control de la detención (columna BY) deberá ser mayor o igual a la Fecha de apertura de la carpeta de investigación (columna X) de la plantilla Carpeta de investigación
    public ArrayList FechaAudienciaControlDetencion(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT IMP.ENTIDADID,IMP.IMPUTADOID,IMP.CARPETAID,IMP.PERIODO,\n"
                + "IMP.FECHADETENCION,\n"
                + "IMP.HORADETENCION,\n"
                + "IMP.FECHAAUDIENCIACONTROLDETENCION,\n"
                + "CI.CATFORMINICARINVEID,\n"
                + "CI.FECHACARINVE,\n"
                + "ci.HORAPECARINVE\n"
                + "FROM \n"
                + "RES_TR_SENAP_IMPUTADO IMP LEFT JOIN \n"
                + "RES_TR_SENAP_CARPETAINVESTIGACION CI ON CI.ENTIDADID=IMP.ENTIDADID AND IMP.CARPETAID=CI.CARPETAID AND IMP.PERIODO=CI.PERIODO\n"
                + "where IMP.FECHAAUDIENCIACONTROLDETENCION<CI.FECHACARINVE\n"
                + "AND IMP.FECHAAUDIENCIACONTROLDETENCION<>'01-01-1900' AND IMP.FECHAAUDIENCIACONTROLDETENCION<>'09-09-1899' \n"
                + "AND (IMP.ENTIDADID='" + Entidad + "' AND IMP.PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VNoticiaCriminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. La FECHANACIMIENTO (Fecha de nacimiento) no debe ser mayor a la fecha Actual 
    public ArrayList FECHANACIMIENTOMayor(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT ENTIDADID,IMPUTADOID,PERIODO\n"
                + "FROM RES_TR_SENAP_IMPUTADO\n"
                + "WHERE FECHANACIMIENTO > SYSDATE  AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VVictimas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar la FECHANACIMIENTO (Fecha de nacimiento) ya que es mayor a 109 años de Edad
    public ArrayList FECHANACIMIENTEDADOMayor(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "SELECT * FROM(\n"
                + "SELECT ENTIDADID,IMPUTADOID,PERIODO,\n"
                + "EXTRACT (YEAR FROM SYSDATE) \n"
                + "- \n"
                + "EXTRACT (YEAR FROM FECHANACIMIENTO)  as años_actual,FECHANACIMIENTO\n"
                + "FROM RES_TR_SENAP_IMPUTADO)\n"
                + "WHERE años_actual > 110 AND (FECHANACIMIENTO<>'01/01/1900')  AND (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "'  )";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VVictimas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Fecha de nacimiento  FECHANACIMIENTO debido que a que no puede venir nulo.
    public ArrayList Fechadenacimiento122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, FECHANACIMIENTO\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where FECHANACIMIENTO is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Edad al momento que sucedieron los hechos  EDADMOMENTOSUCEDIERONHECHOS debido que a que no puede venir nulo.
    public ArrayList Edadnotnull(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, EDADMOMENTOSUCEDIERONHECHOS\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where EDADMOMENTOSUCEDIERONHECHOS is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Sexo CATSEXOID debido a que no se puede seleccionar una opción diferente a "Hombre" (1), "Mujer" (2) y "No identificado" (3).
    public ArrayList Edad122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, CATSEXOID\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where CATSEXOID not in (1,2,9) or CATSEXOID is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Situación conyugal CATSITUACIONCONYUGALID debido a que no se puede seleccionar una opción diferente a "Casado(a)" (1), "Unión Libre" (2), "Divorciado(a)" (3), "Separado(a)" (4), "Soltero(a)" (5), "Concubinato" (6), "Sociedad de convivencia" (7), "Viudo(a)" (8), "Otro" (9) y "No identificado" (10).
    public ArrayList CATSITUACIONCONYUGALID122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, CATSITUACIONCONYUGALID\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where CATSITUACIONCONYUGALID not in (1,2,3,4,5,6,7,8,9,99) or CATSITUACIONCONYUGALID is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Nacionalidad CATNACIONALIDADID debido a que no se puede venir nulo
    public ArrayList CATNACIONALIDADID122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, CATNACIONALIDADID\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where CATNACIONALIDADID in (0) or CATNACIONALIDADID is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Nivel de escolaridad CATNIVELESCOLARIDADID debido a que no se puede seleccionar una opción diferente a "Ninguno" (1), "Preescolar o primaria" (2), "Secundaria" (3), "Preparatoria" (4), "Carrera técnica o comercial" (5), "Licenciatura" (6), "Maestría" (7), "Doctorado" (8) y "No identificado" (9).
    public ArrayList escolaridad122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, CATNIVELESCOLARIDADID\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where CATNIVELESCOLARIDADID not in (1,2,3,4,5,6,7,8,99) or CATNIVELESCOLARIDADID is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Antecedentes penales ANTECEDENTESPENALES debido a que no se puede seleccionar una opción diferente a "Si" (1), "No" (2) y "No identificado" (3).
    public ArrayList ANTECEDENTESPENALES122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, ANTECEDENTESPENALES\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where ANTECEDENTESPENALES not in (1,2,9) or ANTECEDENTESPENALES is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }

    //ReglaDMED. Favor de verificar el campo Tipo de persona imputada CATTIPOPERSONAIMPUTADAID debido a que no se puede seleccionar una opción diferente a "Persona física" (1), "Persona moral" (2) y "No identificado" (3).
    public ArrayList CATTIPOPERSONAIMPUTADAID122024(String Entidad, String Periodo) throws SQLException {
        conexion.Conectar();
        Array = new ArrayList();
        sql = "select ENTIDADID, PERIODO, CARPETAID, IMPUTADOID, CATTIPOPERSONAIMPUTADAID\n"
                + "from RES_TR_SENAP_IMPUTADO\n"
                + "where CATTIPOPERSONAIMPUTADAID not in (1,2,9) or CATTIPOPERSONAIMPUTADAID is null"
                + " and  (ENTIDADID='" + Entidad + "' AND PERIODO='" + Periodo + "')";
        System.out.println(sql);
        Query = sql;
        resul = conexion.consultar(sql);
        try {
            while (resul.next()) {
                Array.add(new String[]{
                    resul.getString("IMPUTADOID")

                });
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(VCarpetaInvetigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Array;
    }
}
