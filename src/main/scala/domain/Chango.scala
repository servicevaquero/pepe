package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Chango(unGateWay: GateWay) extends Entity {
  var entradasEscogidas: List[Entrada] = List()
  var gateWayDePago: GateWay = unGateWay
  val montoASuperar = 1000

  def agregarEntradaDeEsteFestivalPresentacionYButacaParaEsteCliente(unFestival: Festival, unaPresentacion: Presentacion, unaButaca: Butaca, unCodigo: String, unCliente: Cliente) {
    var entradaOEntradas = unFestival.dameUnaEntradaConEstaButacaYEstaPresentacion(unaButaca, unaPresentacion, unCodigo)
    entradaOEntradas.foreach(unaEntrada => unaEntrada.setCliente(unCliente))
    agregarEntradas(entradaOEntradas)
  }

  def agregarEntrada(unaEntrada: Entrada, unCodigo: String, unCliente: Cliente) {
    var entradaOEntradas = unaEntrada.getFestival.dameUnaEntradaConEstaButacaYEstaPresentacion(unaEntrada.getButaca, unaEntrada.getPresentacion, unCodigo)
    entradaOEntradas.foreach(unaEntrada => unaEntrada.setCliente(unCliente))
    agregarEntradas(entradaOEntradas)
  }

  def agregarEntradas(unasEntradas: List[Entrada]) {
    entradasEscogidas = entradasEscogidas ++ unasEntradas
  }

  def calcularPrecio = {
    var precioFinal: Double = 0
    var festivalesEnElChango: List[Festival] = obtenerLosDiferentesFestivales

    festivalesEnElChango.foreach(unFestival =>
      precioFinal += calcularPrecioFinalPorFestivalConDescuento(entradasEscogidas.filter(unaEntrada => unaEntrada.getFestival == unFestival)))
    precioFinal
  }

  def calcularPrecioTotalPorFestivalSinDescuento(entradasDelFestival: List[Entrada]) = {
    var precio: Double = 0

    entradasDelFestival.foreach(unaEntrada => precio += unaEntrada.calcularPrecioConDescuento(entradasDelFestival))
    precio
  }

  def calcularPrecioFinalPorFestivalConDescuento(unasEntradas: List[Entrada]) = {
    var precio: Double = calcularPrecioTotalPorFestivalSinDescuento(unasEntradas)

    if (precio > montoASuperar)
      precio * 0.9
    else precio
  }

  def obtenerLosDiferentesFestivales: List[Festival] = {
    var festivalesEnElChango: List[Festival] = List()

    entradasEscogidas.foreach(unaEntrada =>
      if (!festivalesEnElChango.contains(unaEntrada.getFestival))
        festivalesEnElChango = festivalesEnElChango ++ List(unaEntrada.getFestival))
    festivalesEnElChango
  }

  def realizarPagoConTarjeta(unaTarjeta: Tarjeta) {
    val montoAPagar: Double = calcularPrecio
    gateWayDePago.ingresarPago(unaTarjeta, montoAPagar)
  }

}