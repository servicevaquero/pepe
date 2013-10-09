package applicationModel
import domain.Presentacion
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
    presentacionEscogida.getEntradas.foreach(unaEntrada => if (unaEntrada.cliente == null) listaTemporal.add(unaEntrada))
    entradasDisponibles = listaTemporal
  }

  def hayUnaEntradaElegida = {
    if (entradaEscogida == null)
      null
    else new Object()
  }
}