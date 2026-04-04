package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.Data;
import java.util.List;

@Data
public abstract class Plan {
    protected double montoBase;
    public abstract double calcularMonto(List<Dispositivo> dispositivos);
}
