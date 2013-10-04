import org.scalatest._
import domain.Categoria
import domain.Chango
import domain.Fila
import domain.GateWay
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

class testGeneral extends FunSuite with BeforeAndAfter {
  
  var fila1: Fila = _
  var fila2: Fila = _
  var listaDeFilas: List[Fila] = _
  var sectorA: Sector = _
  var sectorB: Sector = _
  var butaca1Fila1SectorA: Butaca = _
  var butaca1Fila2SectorA: Butaca = _
  var butaca1Fila3SectorA: Butaca = _
  var butaca1Fila4SectorA: Butaca = _
  var butaca1Fila1SectorB: Butaca = _
  var butaca1Fila2SectorB: Butaca = _
  var festival: Festival = _
  var presentacionA: Presentacion = _
  var presentacionB: Presentacion = _
  var presentacionC: Presentacion = _
  var listaDeBandas: List[Banda] = _
  var listaDeButacas: List[Butaca] = _
  var listaDeVIP: List[Butaca] = _
  var recargoVIP: Double = _
  var listaDeReservadas: List[Butaca] = _
  var bandaA: Banda = _
  var bandaB: Banda = _
  var categoria1: Categoria = _
  var categoria2: Categoria = _
  var tarjetas : List[Tarjeta] = _
  var tarjetaA: Tarjeta = _
  var tarjetaB: Tarjeta = _
  var gateWay: GateWay = _
  var chango: Chango = _

  before {
    fila1 = new Fila(1, 35)
    fila2 = new Fila(2, 22)
    listaDeFilas = List(fila1, fila2)
    sectorA = new Sector('a', listaDeFilas)
    sectorB = new Sector('b', listaDeFilas)
    butaca1Fila1SectorA = new Butaca(1, 1, sectorA)
    butaca1Fila2SectorA = new Butaca(1, 2, sectorA)
    butaca1Fila1SectorB = new Butaca(1, 1, sectorB)
    butaca1Fila2SectorB = new Butaca(1, 2, sectorB)
    categoria1 = new Categoria('A', 0)
    categoria2 = new Categoria('B', 50)
    bandaA = new Banda(categoria1)
    bandaB = new Banda(categoria2)
    listaDeButacas = List(butaca1Fila1SectorA, butaca1Fila2SectorA, butaca1Fila3SectorA, butaca1Fila4SectorA, butaca1Fila1SectorB, butaca1Fila2SectorB)
    listaDeReservadas = List(butaca1Fila2SectorB)
    listaDeVIP = List(butaca1Fila1SectorB)
    recargoVIP = 0.5	
    listaDeBandas = List(bandaA, bandaB)
    presentacionA = new Presentacion(20130502, listaDeBandas)
    presentacionB = new Presentacion(20131212, listaDeBandas)
    presentacionC = new Presentacion(1, List())
    
    festival = new Festival(listaDeButacas, listaDeReservadas, listaDeVIP, recargoVIP, List(presentacionA, presentacionB), List(Adulto, Menor, MenorDeDoce, Jubilado))
    
    tarjetaA = new Tarjeta("Sanchez", "José", 1, 3000.0)
    tarjetaB = new Tarjeta("García","Clotildea Teodosia", 2, 100.0)
    tarjetas = List(tarjetaA, tarjetaB)
    
    gateWay = new GateWayMOCK(true, tarjetas)
    chango = new Chango(gateWay)
    
  }

  test("Escena 1: ChangoMaster comprando - Toma: 1") { // DESCUENTO MENOR DE 12
	festival.crearEntradasParaCadaPresentacion
    val carla = Cliente.apply(11111111, 11, 'F')
    assert(carla.getTiposDeCliente.contains(MenorDeDoce))
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festival, presentacionA, butaca1Fila1SectorA, "", carla)
    assert(chango.calcularPrecio === 85)    // hasta acá no tiene descuento porque la considera que esta SOLA
    val carlonchasFather = Cliente.apply(22222222, 80, 'M')
    assert(carlonchasFather.getTiposDeCliente.contains(Jubilado))
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festival, presentacionA, butaca1Fila2SectorA, "", carlonchasFather)
    assert(chango.calcularPrecio === 136.2)
    
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 2863.8)
    
    val thrown = intercept[ExcepcionTarjetaRechazada] {
		chango.realizarPagoConTarjeta(tarjetaB)
	}
    assert(tarjetaB.getSaldoDisponible == 100) // Es pobre, no le alcanza la tarasca y le dice Saldo insuficiente
    assert(thrown.getMessage() == "Saldo insuficiente")//Es lo que yo quería
	
  }
  
    test("Escena 1: ChangoMaster comprando - Toma: 2") {
	festival.crearEntradasParaCadaPresentacion
    val carla = Cliente.apply(11111111, 17, 'F')
    assert(carla.getTiposDeCliente.contains(Menor))
    assert(carla.getTiposDeCliente.contains(Dama))
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festival, presentacionA, butaca1Fila1SectorA, "", carla)

    assert(chango.calcularPrecio === 85)
    // hasta acá no tiene descuento porque no supera los minimos para aplicar el descuento de Menor
    val carlonchasFather = Cliente.apply(22222222, 80, 'M')
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festival, presentacionA, butaca1Fila2SectorA, "", carlonchasFather)
    assert(chango.calcularPrecio === 153.7)
    
    gateWay.setEstadoDeConexion(false) //apago para que no haga transacciones todavía
   
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 3000)
    chango.realizarPagoConTarjeta(tarjetaB)
    assert(tarjetaB.getSaldoDisponible == 100)
    
    val thrown = intercept[ExcepcionTarjetaRechazada] {
    gateWay.setEstadoDeConexion(true) // enciendo, entonces tiene que realizar las transacciones pendientes
	}
	assert(thrown.getMessage() == "Saldo insuficiente")//Es lo que yo quería
    assert(tarjetaA.getSaldoDisponible == 2846.3)
    assert(tarjetaB.getSaldoDisponible == 100) // Es pobre, no le alcanza la tarasca y le dice Saldo insuficiente
  }
    
  test("Escena 1: ChangoMaster comprando - Toma: 3") {
	festival.crearEntradasParaCadaPresentacion
    val carla = Cliente.apply(11111111, 47, 'F')
    assert(carla.getTiposDeCliente.contains(Adulto))

    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festival, presentacionA, butaca1Fila1SectorA, "", carla)
    assert(chango.calcularPrecio === 85)
    
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 2915)
    chango.realizarPagoConTarjeta(tarjetaB)
    assert(tarjetaB.getSaldoDisponible == 15)   
  }


  test("Escena 1: ChangoMaster comprando - Toma: 4") { // DESCUENTO DAMA
    
    var festivalConDama = new Festival(listaDeButacas, listaDeReservadas, listaDeVIP, recargoVIP, List(presentacionA, presentacionB), List(Dama, Adulto, Menor, Jubilado))
    festivalConDama.crearEntradasParaCadaPresentacion
    
    val carla = Cliente.apply(11111111, 47, 'F')
    assert(carla.getTiposDeCliente.contains(Adulto))
    assert(carla.getTiposDeCliente.contains(Dama))
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festivalConDama, presentacionA, butaca1Fila1SectorA, "", carla)    
    assert(chango.calcularPrecio === 78)
    
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 2922)
    chango.realizarPagoConTarjeta(tarjetaB)
    assert(tarjetaB.getSaldoDisponible == 22)

  }

  test("Escena 1: ChangoMaster comprando - Toma: 5") { //comprando Reservadas
	
    var festivalSinDescuentos = new Festival(listaDeButacas, List(butaca1Fila1SectorA), listaDeVIP, recargoVIP, List(presentacionA, presentacionB), List())
    festivalSinDescuentos.crearEntradasParaCadaPresentacion    
    val carla = Cliente.apply(11111111, 11, 'F')
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festivalSinDescuentos, presentacionA, butaca1Fila1SectorA, "A la grande le puse Cuca", carla)
    assert(chango.calcularPrecio === 85)
    
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 2915)
    chango.realizarPagoConTarjeta(tarjetaB)
    assert(tarjetaB.getSaldoDisponible == 15)
  }
  
  test("Escena 1: ChangoMaster comprando - Toma: 6") { //comprando VIP

    var festivalSinDescuentos = new Festival(listaDeButacas, List(), List(butaca1Fila1SectorA), recargoVIP, List(presentacionA, presentacionB), List())
    festivalSinDescuentos.crearEntradasParaCadaPresentacion
    val carla = Cliente.apply(11111111, 11, 'F')
    
    chango.agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(festivalSinDescuentos, presentacionA, butaca1Fila1SectorA, "", carla)
    assert(chango.calcularPrecio === 255) // es 85 por cada butaca, 2 presentacion ---> 170 + 50% de recargo ----> 255
    
    chango.realizarPagoConTarjeta(tarjetaA)
    assert(tarjetaA.getSaldoDisponible == 2745)
    val thrown = intercept[ExcepcionTarjetaRechazada] {
    chango.realizarPagoConTarjeta(tarjetaB)
    }
    assert(thrown.getMessage() == "Saldo insuficiente")//Es lo que yo quería
    assert(tarjetaB.getSaldoDisponible == 100) // Es pobre, no le alcanza la tarasca y le dice Saldo insuficiente
  }

}