package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
/*
@Scope("prototype") me permite tener varias instancias de dispositivo con la annotation Autowired
*/
@Scope("prototype")
@Data
@NoArgsConstructor
@AllArgsConstructor
/*
@EqualsAndHashCode(onlyExplicitlyIncluded = true) me permite que, al comparar objetos para determinar
la igualdad, sólo compare los atributos que yo le marco
*/
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dispositivo
{
    // le pongo un ID al Dispositivo
    @EqualsAndHashCode.Include
    private String id = UUID.randomUUID().toString();

    private double costo;

    public void setCosto(double costo)
    {
        this.costo = Math.max(0, costo);
    }
}
