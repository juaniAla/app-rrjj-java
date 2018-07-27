package utn.ofa.java.app.dao;

import java.util.List;

import utn.ofa.java.app.rrhh.Empleado;

public interface EmpleadoDao {
    
    public int crear(Empleado e);
    public int actualizar(Empleado e);
    public int eliminar(Empleado e);
    public Empleado buscarPorId(Integer id, boolean cerrarRS);
    public List<Empleado> buscarTodos();
    
}
