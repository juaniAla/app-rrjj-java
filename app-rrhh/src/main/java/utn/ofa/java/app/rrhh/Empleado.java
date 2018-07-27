package utn.ofa.java.app.rrhh;

import java.util.Date;

public abstract class Empleado {
    
    private Integer id;
    private Integer horasTrabajadas;
    private String nombre;
    private String correoElectronico;
    private String cuil;
    private Date fechaIngreso;
    
    public abstract Double salario();
    public abstract boolean esEfectivo();
    public abstract boolean esContratado();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
}
