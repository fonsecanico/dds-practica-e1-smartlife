package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.HashSet;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente
{
    private Set<Dispositivo> dispositivos = new HashSet<>();
    private Plan plan;

    public double calcularMonto()
    {
        return plan.calcularMonto(dispositivos);
    }
    public void agregarDispositivo(Dispositivo dispositivo)
    {
        this.dispositivos.add(dispositivo);
    }
    public void removerDispositivo(Dispositivo dispositivo)
    {
        if(dispositivos.contains(dispositivo)) this.dispositivos.remove(dispositivo);
    }
}
