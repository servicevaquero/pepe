package applicationModel
import domain.Presentacion
import domain.Entrada
import java.util.ArrayList
import org.uqbar.commons.utils.Observable

@Observable
class ElegirEntradasDePresentacion(unaPresentacion: Presentacion, unGestorDeCompra:GestorDeCompra) extends Serializable {
  val presentacionEscogida: Presentacion = unaPresentacion
  var gestorDeCompra: GestorDeCompra = unGestorDeCompra
  var entradaEscogida: Entrada = gestorDeCompra.entradaSeleccionada
  var entradasDisponibles: ArrayList[Entrada] = new ArrayList[Entrada]
  this.setListaDisponibles

  def setEntradaEscogida {
    gestorDeCompra.entradaSeleccionada = entradaEscogida
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