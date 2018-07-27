package utn.ofa.java.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utn.ofa.java.app.rrhh.Contratado;
import utn.ofa.java.app.rrhh.Efectivo;
import utn.ofa.java.app.rrhh.Empleado;

public class EmpleadoDaoJDBC implements EmpleadoDao {

    private final String INSERT_EMPLEADO =
        "INSERT INTO EMPLEADOS (NOMBRE, CORREO, CUIL, " + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, " +
        "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_EMPLEADO =
        "UPDATE EMPLEADOS SET NOMBRE = ?, CORREO = ?, CUIL  = ?, FECHA_INGRESO  = ?, HS_TRABAJADAS  = ?, SUELDO_BASICO  = ?," +
        " COMISIONES  = ?, HS_MINIMAS = ?, COSTO_HORA  = ? WHERE ID = ?";
    private final String DELETE_EMPLEADO = "DELETE FROM EMPLEADOS WHERE ID = ?";
    private final String SELECT_ALL_EMPLEADO = "SELECT * FROM EMPLEADOS WHERE ID = ?";
    private final String SELECT_ALL_ID_EMPLEADO = "SELECT ID FROM EMPLEADOS";

    public EmpleadoDaoJDBC() {
        super();
    }

    @Override
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public int crear(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionMySQL.getConexion();
            PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreoElectronico());
            ps.setString(3, e.getCuil());
            ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
            ps.setInt(5, e.getHorasTrabajadas());
            if (e.esEfectivo()) {
                Efectivo empEf = (Efectivo) e;
                ps.setDouble(6, empEf.getSueldoBasico());
                ps.setDouble(7, empEf.getComisiones());
                ps.setInt(8, empEf.getHorasObligatorias());
                ps.setNull(9, Types.DOUBLE);
                ps.setInt(10, 1);
            }
            if (e.esContratado()) {
                Contratado c = (Contratado) e;
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setDouble(9, c.getCostoHora());
                ps.setInt(10, 2);
            }

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    ConexionMySQL.liberarConexion();
                }
            } catch (SQLException f) {
            }
        }
        return 0;
    }

    @Override
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public int actualizar(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionMySQL.getConexion();
            PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLEADO);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreoElectronico());
            ps.setString(3, e.getCuil());
            ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
            ps.setInt(5, e.getHorasTrabajadas());
            if (e.esEfectivo()) {
                Efectivo empEf = (Efectivo) e;
                ps.setDouble(6, empEf.getSueldoBasico());
                ps.setDouble(7, empEf.getComisiones());
                ps.setInt(8, empEf.getHorasObligatorias());
            }
            if (e.esContratado()) {
                Contratado c = (Contratado) e;
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);
                ps.setDouble(9, c.getCostoHora());
            }

            // Seteo el id
            ps.setInt(10, e.getId().intValue());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    ConexionMySQL.liberarConexion();
                }
            } catch (SQLException f) {
            }
        }
        return 0;
    }

    @Override
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public int eliminar(Empleado e) {
        Connection conn = null;
        try {
            conn = ConexionMySQL.getConexion();
            PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO);
            ps.setInt(1, e.getId().intValue());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    ConexionMySQL.liberarConexion();
                }
            } catch (SQLException f) {
            }
        }
        return 0;
    }

    @Override
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public Empleado buscarPorId(Integer id, boolean cerrarRS) {
        Empleado retorno = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexionMySQL.getConexion();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_EMPLEADO);
            ps.setInt(1, id.intValue());
            rs = ps.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int tipoEmpleado = rs.getInt("TIPO_EMPLEADO");
                if (tipoEmpleado == 2) {
                    Integer costoHora = rs.getInt("COSTO_HORA");
                    retorno = new Contratado();
                    ((Contratado) retorno).setCostoHora(costoHora);
                } else {
                    retorno = new Efectivo();
                    Integer horasMinimias = rs.getInt("TIPO_EMPLEADO");
                    Double sueldoBasico = rs.getDouble("SUELDO_BASICO");
                    Double comisiones = rs.getDouble("COMISIONES");
                    ((Efectivo) retorno).setHorasObligatorias(horasMinimias);
                    ((Efectivo) retorno).setSueldoBasico(sueldoBasico);
                    ((Efectivo) retorno).setComisiones(comisiones);
                }

                Integer horasTrabajadas = rs.getInt("HS_TRABAJADAS");
                String nombre = rs.getString("NOMBRE");
                String correo = rs.getString("CORREO");
                String cuil = rs.getString("CUIL");
                Date fecha = rs.getDate("FECHA_INGRESO");

                retorno.setCorreoElectronico(correo);
                retorno.setCuil(cuil);
                retorno.setFechaIngreso(fecha);
                retorno.setHorasTrabajadas(horasTrabajadas);
                retorno.setNombre(nombre);
                retorno.setId(id);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && cerrarRS) {
                    ConexionMySQL.liberarConexion();
                }
                if(rs != null && cerrarRS){
                    rs.close();
                }
            } catch (SQLException f) {
            }
        }
        return retorno;
    }

    @Override
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public List<Empleado> buscarTodos() {
        List<Empleado> retorno = new ArrayList<Empleado>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexionMySQL.getConexion();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_ID_EMPLEADO);
            rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("ID");
                retorno.add(this.buscarPorId(id, false));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    ConexionMySQL.liberarConexion();
                }
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException f) {
            }
        }

        return retorno;
    }
}
