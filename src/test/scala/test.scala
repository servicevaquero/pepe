import org.scalatest._
import domain.Categoria
import domain.Fila
import domain.Entrada
import domain.MenorDeDoce
import domain.Menor
import domain.Jubilado
import domain.Dama
import domain.Adulto
import domain.Presentacion
import domain.ExcepcionTarjetaRechazada
import domain.Sector
import domain.Festival
import domain.Butaca
import domain.Banda
import domain.Cliente
import domain.Tarjeta
import domain.GateWayMOCK

class test extends FunSuite with BeforeAndAfter {

  var fila1: Fila = _
  var fila2: Fila = _
  var listaDeFilas: List[Fila] = _
  var sectorA: Sector = _
  var butaca2Fila1: Butaca = _
  var festival: Festival = _
  var presentacion: Presentacion = _
  var listaDeBandas: List[Banda] = _
  var listaDeButacas: List[Butaca] = _
  var bandaA: Banda = _
  var bandaB: Banda = _
  var categoria1: Categoria = _
  var categoria2: Categoria = _
  var entradaA: Entrada = _
  var entradaB: Entrada = _
  var entradaC: Entrada = _

  before {
    fila1 = new Fila(1, 35)
    fila2 = new Fila(2, 22)
    listaDeFilas = List(fila1, fila2)
    sectorA = new Sector('a', listaDeFilas)
    butaca2Fila1 = new Butaca(2, 1, sectorA)
    categoria1 = new Categoria('A', 0)
    categoria2 = new Categoria('B', 50)
    bandaA = new Banda(categoria1)
    bandaB = new Banda(categoria2)
    listaDeButacas = List(butaca2Fila1)
    listaDeBandas = List(bandaA, bandaB)
    presentacion = new Presentacion(20130502, listaDeBandas)
    festival = new Festival(listaDeButacas, List(), List(), 0.5, List(presentacion), List(Adulto, Menor, Dama, Jubilado))
    entradaA = new Entrada(festival, presentacion, butaca2Fila1)
    entradaB = new Entrada(festival, presentacion, butaca2Fila1)
    entradaC = new Entrada(festival, presentacion, butaca2Fila1)
  }

  test("Averiguar el precio base") {

    val precioBase = butaca2Fila1.precioBase

    assert(precioBase === 35)

  }

  test("Averiguar el descuento por cliente") {

    val listaEntradas : List[Entrada] = List(entradaA,entradaB,entradaC)
    var unaEntrada : Entrada = entradaA
    
    val descuentoAdulto = (Adulto).getDescuentoEntrada.apply(unaEntrada, listaEntradas)
    //val descuentoDama = tipoDeCliente.getMapaDeDescuentos("Dama").apply(unaEntrada, listaEntradas)
    val descuentoJubilado = (Jubilado).getDescuentoEntrada.apply(unaEntrada, listaEntradas)
    val descuentoMenor = (Menor).getDescuentoEntrada.apply(unaEntrada, listaEntradas)
    val descuentoMenorDeDoce = (MenorDeDoce).getDescuentoEntrada.apply(unaEntrada, listaEntradas)

    assert(descuentoAdulto === 0)
    //assert(descuentoDama === 0) ----> Deshabilitado porque requiere fijarse las demas entradas de la presentacion y no estan asignadas
    assert(descuentoJubilado === 0.15 * butaca2Fila1.precioBase)
    assert(descuentoMenorDeDoce === 0.5 * butaca2Fila1.precioBase)
    
    //solo estan habilitados adulto, dama y jubilado
    
    var unCliente = Cliente.apply(23000000, 78, 'M') // Jubilado :D
    unaEntrada.setCliente(unCliente)
    val valorConDescuentoJubilado = unaEntrada.calcularPrecioConDescuento(listaEntradas)
    assert(valorConDescuentoJubilado === unaEntrada.calcularPrecioSinDescuento - (0.15 * unaEntrada.precioBase))
    
    unCliente = Cliente.apply(23000000, 60, 'M') // Adulto :(
    unaEntrada.setCliente(unCliente)
    val valorConDescuentoAdulto = unaEntrada.calcularPrecioConDescuento(listaEntradas)
    assert(valorConDescuentoAdulto === unaEntrada.calcularPrecioSinDescuento - (0 * unaEntrada.precioBase))
  }

  test("Averiguar el valor extra por noche") {

    assert(presentacion.valorExtraPorNoche === 50)
  }

  test("Averiguar el valor de una entrada") {

    var entrada = new Entrada(festival, presentacion, butaca2Fila1)
    val precioBase = butaca2Fila1.precioBase
    val precioEntrada = precioBase + presentacion.valorExtraPorNoche

    assert(entrada.calcularPrecioSinDescuento === precioEntrada)
  }
  
  // -----> Sacado porque se testea en TestGeneral con manejo de Excepciones
  test("Viendo como responde el gateway") {
    var tarjetas : List[Tarjeta] = List()
    var tarjetaA = new Tarjeta("Sanchez", "José", 1, 3000.0)
    var tarjetaB = new Tarjeta("García","Clotildea Teodosia", 2, 100.0)
    tarjetas = tarjetas ++ List(tarjetaA, tarjetaB)
    var gateWay = new GateWayMOCK(true,tarjetas)
    gateWay.ingresarPago(tarjetaA, 200) // Realizado Exitosamente
    val thrownA = intercept[ExcepcionTarjetaRechazada] {
    gateWay.ingresarPago(tarjetaB, 200) // ----> java.lang.Exception: Saldo insuficiente
    }
	assert(thrownA.getMessage() == "Saldo insuficiente")//Es lo que yo quería
    
    assert(tarjetaA.getSaldoDisponible === 2800)
    assert(tarjetaB.getSaldoDisponible === 100)
    
    gateWay.setEstadoDeConexion(false)
    gateWay.ingresarPago(tarjetaA, 200) //todos imprimen por consola: ESTADO PENDIENTE
    gateWay.ingresarPago(tarjetaA, 200)
    gateWay.ingresarPago(tarjetaB, 70)
    gateWay.ingresarPago(tarjetaB, 50) // este no va a ser realizado porque sería saldo -20 --> java.lang.Exception: Saldo insuficiente
    //se fija que no haya hecho descuentos, porque esta OFFLINE
    assert(tarjetaA.getSaldoDisponible === 2800)
    assert(tarjetaB.getSaldoDisponible === 100)
    
    val thrownB = intercept[ExcepcionTarjetaRechazada] {
    gateWay.setEstadoDeConexion(true)
    }
    assert(thrownB.getMessage() == "Saldo insuficiente")//Es lo que yo quería
	

    //se fija que al activarse realice todos los pendientes
    assert(tarjetaA.getSaldoDisponible === 2400)
    assert(tarjetaB.getSaldoDisponible === 30)
    
    //se pone OFFLINE y después ONLINE para corroborar que los valores no se alteran, ya que no quedan pendientes
    gateWay.setEstadoDeConexion(false)
    
    gateWay.setEstadoDeConexion(true)
    assert(tarjetaA.getSaldoDisponible === 2400)
    assert(tarjetaB.getSaldoDisponible === 30)

  }
  
}
