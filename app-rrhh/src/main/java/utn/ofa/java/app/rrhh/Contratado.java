package utn.ofa.java.app.rrhh;

public class Contratado extends Empleado {

    private Integer costoHora = null;

    public Contratado() {
        super();
    }

    @Override
    public Double salario() {
        return costoHora.doubleValue() * super.getHorasTrabajadas();
    }

    @Override
    public boolean esEfectivo() {
        return false;
    }

    @Override
    public boolean esContratado() {
        return true;
    }


    public void setCostoHora(Integer costoHora) {
        this.costoHora = costoHora;
    }

    public Integer getCostoHora() {
        return costoHora;
    }
}
