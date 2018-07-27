package utn.ofa.java.app.test;

import org.junit.Test;
import static org.junit.Assert.*;

import utn.ofa.java.app.rrhh.Efectivo;


public class EfectivoTest {
    public EfectivoTest() {
    }

    /**
     * @see utn.ofa.java.app.rrhh.Efectivo#salario()
     */
    @Test
    public void testSalario() {
        System.out.println("salario");
        Efectivo empleado = new Efectivo();
        empleado.setHorasTrabajadas(42);
        empleado.setHorasObligatorias(40);
        empleado.setComisiones(2000.0);
        empleado.setSueldoBasico(30000.0);
        Double expResult = 30000.0 + 2000.0 + 3000.0;
        Double result = empleado.salario();
        assertEquals(expResult, result);
    }
}
