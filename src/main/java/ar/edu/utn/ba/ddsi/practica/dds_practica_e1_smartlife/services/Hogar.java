package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Hogar extends Plan {
    @Override
    public double calcularMonto(List<Dispositivo> dispositivos) {
        return montoBase;
    }
}
