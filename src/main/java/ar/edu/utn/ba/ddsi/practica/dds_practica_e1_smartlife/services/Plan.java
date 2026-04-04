package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.Data;
import java.util.Set;

@Data
public abstract class Plan {
    protected double montoBase;
    public abstract double calcularMonto(Set<Dispositivo> dispositivos);
    public void setMontoBase(double montoBase)
    {
        this.montoBase = Math.max(0, montoBase);
    }
}
