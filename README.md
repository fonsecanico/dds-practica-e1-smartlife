# <center>Cálculo de Suscripción Mensual - SmartLife</center>

## _<u>Contexto</u>_

SmartLife ofrece su plataforma como servicio a distintos tipos de clientes (hogares y comercios).

Para utilizar el ecosistema SmartLife, cada cliente debe abonar una <b>cuota mensual</b>. 
Sin embargo, el monto a pagar depende del tipo de cliente y del plan contratado.

Se identificaron las siguientes situaciones:
1. Los hogares pagan una tarifa base fija.
2. Los comercios pagan una tarifa base más un adicional por cada dispositivo conectado.
3. Los clientes corporativos pagan una tarifa personalizada con descuento porcentual.
4. A futuro podrían incorporarse nuevos tipos de planes.
5. El Sistema debe poder calcular cuánto debe pagar un cliente en el mes.
---
## _<u>Consigna</u>_
Teniendo en cuenta que:
* El método principal del presente módulo debe ser el método “double calcularMonto()” de la clase **FacturaMensual**
* Un cliente tiene un plan asociado y puede tener registrados varios dispositivos
* El monto mensual depende exclusivamente del tipo de plan.

Y teniendo como restricción que solamente se podrán utilizar, además de la clase **FacturaMensual**, la clase **Cliente** y **Plan** (se puede usar herencia o interfaces si se considera necesario); se pide:

1. Diseñar una solución en el Paradigma Orientado a Objetos y comunicar la solución mediante un Diagrama de Clases.
2. Codificar la solución en Java.
3. Crear los tests unitarios que considere necesario para mostrar el correcto funcionamiento del módulo.
---
## _<u>Diagrama de Clases</u>_

![Diagrama de Clases](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/fonsecanico/dds-practica-e1-smartlife/main/src/smartlife-class-diagram.puml)

---
## _<u>Decisiones tomadas</u>_

|Ambito de decision|Componente/s Impactado/s|Decisión|Otras alternativas|Justificación|
| ------------- | ------------- | ------------- | ------------- | ------------- |
|Modelado de Dominio|Plan y sus clases hijas|Es clase abstracta|Hacerla como interfaz|Remarcar la dependencia en la relación entre el Plan y los tipos de planes. También la idea es anticiparse a requerimientos futuros cuyo comportamiento pueda ser común a todos los planes|
|Modelado de Estructuras de Datos|Cliente, Plan y sus clases hijas|La colección de dispositivos es un Set|Implementar una Lista, Array u otra colección similar|Tener un primer filtro ante el posible agregado de dispositivos duplicados en el Cliente
|Modelado de Dominio|Cliente, Plan y sus clases hijas|Tener en el Cliente almacenados los dispositivos|Tener en el Plan Comercio almacenados los dispositivos|Tiene más sentido que el Cliente sea el responsable de manejar sus dispositivos y además se anticipa a que los Planes, en un futuro, puedan utilizar los dispositivos para modificar su costo|
|Modelado de Datos|Plan Corporativo|No se puede tener un monto negativo de descuento|No preocuparme por este asunto|Si al sistema ingresase un monto negativo, el monto final calculado será superior al del plan, por lo que es un error grave|
|Modelado de Datos|Plan Corporativo|Normalizar el descuento|No preocuparme por este asunto|Con el fin de que el cálculo de descuento sea siempre correcto, se realiza una comprobación en la que, si el monto es inferior a 1, se asume que es un porcentaje de descuento (EJ: 0.59 sería 59% de descuento). En cambio, si es superior, se lo toma como un número y se lo divide por 100 para normalizarlo (EJ: 20 sería el 20% de descuento y el sistema lo toma como 0.2) |
