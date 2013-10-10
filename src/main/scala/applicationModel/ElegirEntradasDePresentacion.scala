package applicationModel
import domain.Presentacion
import domain.Festival
import domain.Entrada
import java.util.ArrayList
import org.uqbar.commons.utils.Observable

@Observable
class ElegirEntradasDePresentacion(unaPresentacion: Presentacion, unElector: ElegirClienteYEntrada) extends Serializable {
  val presentacionEscogida: Presentacion = unaPresentacion
  var elector: ElegirClienteYEntrada= unElector
  var entradaEscogida: Entrada = unElector.entradaSeleccionada
  var entradasDisponibles: ArrayList[Entrada] = new ArrayList[Entrada]
  this.setListaDisponibles

  def setEntradaEscogida {
    elector.entradaSeleccionada = entradaEscogida
  }
  
  def setListaDisponibles {
    var listaTemporal: ArrayList[Entrada] = new ArrayList[Entrada]
    var festivalDeLaPresentacion: Festival = presentacionEscogida.getEntradas.head.getFestival
    presentacionEscogida.getEntradas.foreach(unaEntrada => if (muestroEstaEntrada(unaEntrada, festivalDeLaPresentacion)) listaTemporal.add(unaEntrada))
    entradasDisponibles = listaTemporal
  }

  def muestroEstaEntrada(unaEntrada: Entrada, unFestival: Festival): Boolean ={
    unaEntrada.cliente == null && (!unFestival.butacasReservadas.contains(unaEntrada.butaca) || !unFestival.codigoIncorrecto(unElector.codigoTipeado))
  }
  
}