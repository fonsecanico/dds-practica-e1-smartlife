package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente
{
    private List<Dispositivo> dispositivos;
    private Plan plan;

    public double calcularMonto() {
        return plan.calcularMonto(dispositivos);
    }
}
