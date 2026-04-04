package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCorporativo extends Plan
{
    private double descuento;

    public void setDescuento(double descuento)
    {
        this.descuento = Math.max(0, descuento);
        if(descuento >= 1) this.descuento /= 100;
    }
    @Override
    public double calcularMonto(Set<Dispositivo> dispositivos) {
        return montoBase * (1 - descuento);
    }
}
