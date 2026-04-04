package ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife;

import ar.edu.utn.ba.ddsi.practica.dds_practica_e1_smartlife.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DdsPracticaE1SmartlifeApplicationTests {

	@Autowired
	private Dispositivo dispositivoBase;
	@Autowired
	private Dispositivo dispositivoCelular;
	@Autowired
	private Dispositivo dispositivoTelevisor;
	@Autowired
	private Dispositivo dispositivoTelefonoFijo;
	@Autowired
	private FacturaMensual facturaMensual;
	@Autowired
	private Cliente cliente;
	@Autowired
	private PlanHogar planHogar;
	@Autowired
	private PlanComercio planComercio;
	@Autowired
	private PlanCorporativo planCorporativo;

	private List<Plan> planes;
	private Set<Dispositivo> dispositivos;

	@BeforeEach
    public void setUp()
	{
		double costoBaseDispositivo = 0, montoBasePlan = 0;

		this.planes = List.of(planHogar, planComercio, planCorporativo);
		this.planes.forEach(plan -> plan.setMontoBase(montoBasePlan));

		this.cliente.getDispositivos().clear();
		this.cliente.setPlan(null);

		// double en Java no puede ser null, lamentablemente
		this.dispositivoBase.setCosto(costoBaseDispositivo);


		// resulta que a Lombok no le gusta si uso Set.of porque me identifica duplicados al tener los mismos atributos
		// en este caso el costo siendo 0, asi que bueno tengo dos alternativas por lo que vi
		// o le saco @Data a la clase Dispositivo y le pongo @Getter y @Setter
		// o bueno, tiro arrays as list total yo se que cada dispositivo es una instancia distinta
		this.dispositivos = new HashSet<>(Arrays.asList(dispositivoCelular, dispositivoTelevisor, dispositivoTelefonoFijo));
		this.dispositivos.forEach(dispositivo -> dispositivo.setCosto(costoBaseDispositivo));

	}

	@Test
	void contextLoads() {
	}
	/*
	-------------------------------
	---> Tests del Dispositivo <---
	-------------------------------
	*/
	@Test
	public void dispositivoExiste()
	{
		assertNotNull(dispositivoBase);
	}
	@Test
	public void dispositivoTieneCostoCero()
	{
		double costoDispositivo = 0, valorEsperado = 0;

		dispositivoBase.setCosto(costoDispositivo);

		assertThat(dispositivoBase.getCosto()).isEqualTo(valorEsperado);
	}
	@Test
	public void dispositivoTieneCostoPositivo()
	{
		double costoDispositivo = 100, valorAComparar = 0;

		dispositivoBase.setCosto(costoDispositivo);

		assertThat(dispositivoBase.getCosto()).isGreaterThan(valorAComparar);
	}
	@Test
	public void dispositivoTieneCostoCien()
	{
		double costoDispositivo = 100, valorEsperado = 100;

		dispositivoBase.setCosto(costoDispositivo);

		assertThat(dispositivoBase.getCosto()).isEqualTo(valorEsperado);
	}
	@Test
	public void dispositivoCostoNoEsNegativo()
	{
		double costoDispositivo = -100, valorAComparar = 0;

		dispositivoBase.setCosto(costoDispositivo);

		assertThat(dispositivoBase.getCosto()).isGreaterThanOrEqualTo(valorAComparar);
	}
	@Test
	public void dispositivoCostoNegativoEsCero()
	{
		double costoDispositivo = -100, valorAComparar = 0;

		dispositivoBase.setCosto(costoDispositivo);

		assertThat(dispositivoBase.getCosto()).isEqualTo(valorAComparar);
	}
	/*
	---------------------------
	---> Tests del Cliente <---
	---------------------------
	*/
	@Test
	public void clienteExiste()
	{
		assertNotNull(cliente);
	}
	@Test
	public void clienteTienePlanHogar()
	{
		cliente.setPlan(planHogar);

		assertThat(cliente.getPlan()).isEqualTo(planHogar);
	}
	@Test
	public void clienteTienePlanCorporativo()
	{
		cliente.setPlan(planCorporativo);

		assertThat(cliente.getPlan()).isEqualTo(planCorporativo);
	}
	@Test
	public void clienteTienePlanComercio()
	{
		cliente.setPlan(planComercio);

		assertThat(cliente.getPlan()).isEqualTo(planComercio);
	}
	@Test
	public void clienteAgregaUnDispositivo()
	{
		long dispositivosEsperados = 1;

		cliente.agregarDispositivo(dispositivoCelular);

		assertThat(cliente.getDispositivos().stream().count()).isEqualTo(dispositivosEsperados);
	}
	@Test
	public void clienteRemueveUnDispositivo()
	{
		long dispositivosEsperados = 0;

		cliente.agregarDispositivo(dispositivoCelular);
		cliente.removerDispositivo(dispositivoCelular);

		assertThat(cliente.getDispositivos().stream().count()).isEqualTo(dispositivosEsperados);
	}
	@Test
	public void clienteAgregaMuchosDispositivos()
	{
		long dispositivosEsperados = 3;

		cliente.agregarDispositivo(dispositivoCelular);
		cliente.agregarDispositivo(dispositivoTelevisor);
		cliente.agregarDispositivo(dispositivoTelefonoFijo);

		assertThat(cliente.getDispositivos().stream().count()).isEqualTo(dispositivosEsperados);
	}
	@Test
	public void clienteRemueveMuchosDispositivos()
	{
		long dispositivosEsperados = 0;

		cliente.agregarDispositivo(dispositivoCelular);
		cliente.agregarDispositivo(dispositivoTelevisor);
		cliente.agregarDispositivo(dispositivoTelefonoFijo);

		cliente.removerDispositivo(dispositivoCelular);
		cliente.removerDispositivo(dispositivoTelevisor);
		cliente.removerDispositivo(dispositivoTelefonoFijo);

		assertThat(cliente.getDispositivos().stream().count()).isEqualTo(dispositivosEsperados);
	}
	@Test
	public void clienteRemueveDispositivoInexistente()
	{
		cliente.agregarDispositivo(dispositivoCelular);

		assertDoesNotThrow(() -> cliente.removerDispositivo(dispositivoTelevisor));
	}
	@Test
	public void clienteRemueveDeColeccionDispositivosVacia()
	{
		assertDoesNotThrow(() -> cliente.removerDispositivo(dispositivoBase));
	}
	@Test
	public void clienteNoAgregaDuplicados()
	{
		long dispositivosEsperados = 1;

		cliente.agregarDispositivo(dispositivoBase);
		cliente.agregarDispositivo(dispositivoBase);

		assertThat(cliente.getDispositivos().stream().count()).isEqualTo(dispositivosEsperados);
	}
	@Test
	public void clienteCalcularMontoPlanHogarDevuelveMontoBase()
	{
	}
	@Test
	public void clienteCalcularMontoPlanComercioCeroDispositivosDevuelveMontoBase()
	{
	}
	@Test
	public void clienteCalcularMontoPlanComercioUnDispositivoDevuelveSuma()
	{
	}
	@Test
	public void clienteCalcularMontoPlanComercioMuchosDispositivosDevuelveSuma()
	{
	}
	/*
	-----------------------------
	---> Tests de los Planes <---
	-----------------------------
	*/
	@Test
	public void planTieneMontoBaseCero()
	{
		double montoPlan = 0, montoEsperado = 0;

		planes.forEach(plan -> plan.setMontoBase(montoPlan));

		assertThat(planes).allSatisfy(plan -> assertThat(plan.getMontoBase()).isEqualTo(montoEsperado));
	}
	@Test
	public void planTieneMontoBasePositivo()
	{
		double montoPlan = 100, montoAComparar = 0;

		planes.forEach(plan -> plan.setMontoBase(montoPlan));

		assertThat(planes).allSatisfy(plan -> assertThat(plan.getMontoBase()).isGreaterThan(montoAComparar));
	}
	@Test
	public void planTieneMontoBaseCien()
	{
		double montoPlan = 100, montoEsperado = 100;

		planes.forEach(plan -> plan.setMontoBase(montoPlan));

		assertThat(planes).allSatisfy(plan -> assertThat(plan.getMontoBase()).isEqualTo(montoEsperado));
	}
	@Test
	public void planMontoBaseNoEsNegativo()
	{
		double montoPlan = -100, montoAComparar = 0;

		planes.forEach(plan -> plan.setMontoBase(montoPlan));

		assertThat(planes).allSatisfy(plan -> assertThat(plan.getMontoBase()).isGreaterThanOrEqualTo(montoAComparar));
	}
	@Test
	public void planMontoBaseNegativoEsCero()
	{
		double montoPlan = -100, montoAComparar = 0;

		planes.forEach(plan -> plan.setMontoBase(montoPlan));

		assertThat(planes).allSatisfy(plan -> assertThat(plan.getMontoBase()).isEqualTo(montoAComparar));
	}
	@Test
	public void planHogarCalcularMontoDevuelveMontoBase()
	{
		double montoPlan = 100, montoEsperado  = 100;

		planHogar.setMontoBase(montoPlan);

		assertThat(planHogar.calcularMonto(this.dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planComercioCalcularMontoCeroDispositivosDevuelveMontoBase()
	{
		double montoPlan = 100, montoEsperado  = 100;
		Set<Dispositivo> dispositivosVacios = new HashSet<>();

		planComercio.setMontoBase(montoPlan);

		assertThat(planComercio.calcularMonto(dispositivosVacios)).isEqualTo(montoEsperado);
	}
	@Test
	public void planComercioCalcularMontoUnDispositivoDevuelveSuma()
	{
		double montoPlan = 100, costoDispositivo = 10, montoEsperado  = 110;

		dispositivoBase.setCosto(costoDispositivo);

		Set<Dispositivo> unSoloDispositivo = new HashSet<>();
		unSoloDispositivo.add(dispositivoBase);
		planComercio.setMontoBase(montoPlan);

		assertThat(planComercio.calcularMonto(unSoloDispositivo)).isEqualTo(montoEsperado);
	}
	@Test
	public void planComercioCalcularMontoMuchosDispositivosDevuelveSuma()
	{
		// montoEsperado es 130 porque son 3 dispositivos que tienen costo 10 (30) + montoPlan (100)
		double montoPlan = 100, costoDispositivo = 10, montoEsperado  = 130;

		this.dispositivos.forEach(dispositivo -> dispositivo.setCosto(costoDispositivo));
		planComercio.setMontoBase(montoPlan);

		assertThat(planComercio.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoCeroDevuelveMontoBase()
	{
		double montoPlan = 100, descuento = 0, montoEsperado = 100;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoNegativoDevuelveMontoBase()
	{
		double montoPlan = 100, descuento = -50, montoEsperado = 100;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoUnoDevuelveNoventaYNueve()
	{
		double montoPlan = 100, descuento = 1, montoEsperado = 99;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoCincuentaDevuelveMitadMontoBase()
	{
		double montoPlan = 100, descuento = 50, montoEsperado = 50;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoNoventaYNueveDevuelveUno()
	{
		/*
		resulta que si usas isEqualTo me dio este error

			expected: 1.0
 			but was: 1.0000000000000009

 		que tiraba no?
 		resulta que con double pueden sobrar bits, asi que
 		en vez de isEqualTo uso isCloseTo y within para establecer un margen minimo de error
		*/
		double montoPlan = 100, descuento = 99, montoEsperado = 1;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isCloseTo(montoEsperado, within(0.0001));
	}
	@Test
	public void planCorporativoCalcularMontoDescuentoCienDevuelveCero()
	{
		double montoPlan = 100, descuento = 100, montoEsperado = 0;

		planCorporativo.setMontoBase(montoPlan);
		planCorporativo.setDescuento(descuento);

		assertThat(planCorporativo.calcularMonto(dispositivos)).isEqualTo(montoEsperado);
	}
	/*
	-------------------------------------
	---> Tests de la Factura Mensual <---
	-------------------------------------
	*/
	@Test
	public void facturaMensualCalcularMontoClienteHogarDevuelveMontoBase()
	{
		double montoBase = 100, montoEsperado = 100;

		planHogar.setMontoBase(montoBase);
		cliente.setPlan(planHogar);
		facturaMensual.setCliente(cliente);

		assertThat(facturaMensual.calcularMonto()).isEqualTo(montoEsperado);

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoCeroDevuelveMontoBase()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoNegativoDevuelveMontoBase()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoUnoDevuelveNoventaYNueve()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoCincuentaDevuelveMitadMontoBase()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoNoventaYNueveDevuelveUno()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteCorporativoDescuentoCienDevuelveCero()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteComercioCeroDispositivosDevuelveMontoBase()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteComercioUnDispositivo()
	{

	}
	@Test
	public void facturaMensualCalcularMontoClienteComercioMuchosDispositivos()
	{

	}

}
