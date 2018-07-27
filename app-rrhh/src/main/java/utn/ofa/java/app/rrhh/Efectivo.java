package utn.ofa.java.app.rrhh;

public class Efectivo extends Empleado {

    private Integer horasObligatorias = null;
    private Double sueldoBasico = null;
    private Double comisiones = null;
    
    public Efectivo() {
        super();
    }
    
    public void setHorasObligatorias(Integer horasObligatorias) {
        this.horasObligatorias = horasObligatorias;
    }

    public Integer getHorasObligatorias() {
        return horasObligatorias;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setComisiones(Double comisiones) {
        this.comisiones = comisiones;
    }

    public Double getComisiones() {
        return comisiones;
    }

    @Override
    public Double salario() {
        Double salario;
        if (super.getHorasTrabajadas().intValue() > horasObligatorias) {
            salario = sueldoBasico + comisiones +  (sueldoBasico.doubleValue()/20)*(super.getHorasTrabajadas().intValue() - horasObligatorias.intValue());
        } else {
            salario = sueldoBasico + comisiones;
        }
        return salario;
    }

    @Override
    public boolean esEfectivo() {
        return true;
    }

    @Override
    public boolean esContratado() {
        return false;
    }
}
