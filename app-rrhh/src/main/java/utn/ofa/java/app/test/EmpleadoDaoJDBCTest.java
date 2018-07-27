package utn.ofa.java.app.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import utn.ofa.java.app.dao.EmpleadoDaoJDBC;
import utn.ofa.java.app.rrhh.Contratado;
import utn.ofa.java.app.rrhh.Efectivo;
import utn.ofa.java.app.rrhh.Empleado;

public class EmpleadoDaoJDBCTest {
    public EmpleadoDaoJDBCTest() {
    }

    /**
     * @see utn.ofa.java.app.dao.EmpleadoDaoJDBC#crear(utn.ofa.java.app.rrhh.Empleado)
     */
    @Ignore
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public void testCrear() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        EmpleadoDaoJDBC eDao = new EmpleadoDaoJDBC();
        Efectivo e = new Efectivo();
        e.setNombre("Juani Alarcon");
        e.setCorreoElectronico("juani@juani.com");
        e.setCuil("20-31419011-5");
        try {
            e.setFechaIngreso(formatter.parse("01/07/1990"));
        } catch (ParseException f) {
        }
        e.setHorasTrabajadas(30);
        e.setSueldoBasico(30000.0);
        e.setComisiones(1000.0);
        e.setHorasObligatorias(30);
        assertTrue("Hubo un error al intentar dar de alta el empleado " + e.getNombre(), eDao.crear(e) > 0);

        Contratado contratado = new Contratado();
        contratado.setNombre("Juani Alarcon");
        contratado.setCorreoElectronico("juani@juani.com");
        contratado.setCuil("20-31419011-5");
        try {
            contratado.setFechaIngreso(formatter.parse("01/07/1990"));
        } catch (ParseException f) {
        }
        contratado.setHorasTrabajadas(30);
        contratado.setCostoHora(100);
        assertTrue("Hubo un error al intentar dar de alta el empleado " + contratado.getNombre(),
                   eDao.crear(contratado) > 0);
    }

    /**
     * @see utn.ofa.java.app.dao.EmpleadoDaoJDBC#actualizar(utn.ofa.java.app.rrhh.Empleado)
     */
    @Ignore
    public void testActualizar() {
        EmpleadoDaoJDBC test = new EmpleadoDaoJDBC();
        Empleado empleado = test.buscarPorId(4, true);
        // Cambio el nombre y Apellido
        empleado.setNombre("Alarcon Juan Ignacio");
        test.actualizar(empleado);
        assertTrue("Error al actualizar el empleado 4 ", test.actualizar(empleado) > 0);
    }

    /**
     * @see utn.ofa.java.app.dao.EmpleadoDaoJDBC#eliminar(utn.ofa.java.app.rrhh.Empleado)
     */
    @Ignore
    public void testEliminar() {
        EmpleadoDaoJDBC test = new EmpleadoDaoJDBC();
        Empleado empleado = test.buscarPorId(5, true);
       assertTrue("Error al eliminar el empleado 5 ", test.eliminar(empleado) > 0);
    }

    /**
     * @see utn.ofa.java.app.dao.EmpleadoDaoJDBC#buscarPorId(Integer)
     */
    @Ignore
    public void testBuscarPorId() {
        EmpleadoDaoJDBC test = new EmpleadoDaoJDBC();        
        assertTrue("No se encuetra el empleado 1", test.buscarPorId(1, true) != null);
    }

    /**
     * @see utn.ofa.java.app.dao.EmpleadoDaoJDBC#buscarTodos()
     */
    @Test
    public void testBuscarTodos() {
        EmpleadoDaoJDBC test = new EmpleadoDaoJDBC();
        assertTrue("No se encuetran empleados ", test.buscarTodos().size() > 0);
    }
}
