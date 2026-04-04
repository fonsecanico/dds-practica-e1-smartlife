package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@NoArgsConstructor
public class PlanComercio extends Plan
{
    @Override
    public double calcularMonto(Set<Dispositivo> dispositivos) {
        double montoDispositivos = dispositivos.stream().mapToDouble(Dispositivo ::getCosto).sum();
        return montoBase + montoDispositivos;
    }
}
