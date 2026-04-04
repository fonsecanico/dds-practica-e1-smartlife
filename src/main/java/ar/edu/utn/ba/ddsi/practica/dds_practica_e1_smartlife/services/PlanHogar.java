package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@NoArgsConstructor
public class PlanHogar extends Plan {
    @Override
    public double calcularMonto(Set<Dispositivo> dispositivos) {
        return montoBase;
    }
}
