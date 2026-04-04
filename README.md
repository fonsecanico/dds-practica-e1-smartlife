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
## _<u>Consigna</u>_
Teniendo en cuenta que:
* El método principal del presente módulo debe ser el método “double calcularMonto()” de la clase **FacturaMensual**
* Un cliente tiene un plan asociado y puede tener registrados varios dispositivos
* El monto mensual depende exclusivamente del tipo de plan.

Y teniendo como restricción que solamente se podrán utilizar, además de la clase **FacturaMensual**, la clase **Cliente** y **Plan** (se puede usar herencia o interfaces si se considera necesario); se pide:

1. Diseñar una solución en el Paradigma Orientado a Objetos y comunicar la solución mediante un Diagrama de Clases.
2. Codificar la solución en Java.
3. Crear los tests unitarios que considere necesario para mostrar el correcto funcionamiento del módulo.
