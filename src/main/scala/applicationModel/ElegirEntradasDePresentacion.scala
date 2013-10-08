package applicationModel
import domain.Presentacion
import domain.Entrada
import java.util.ArrayList
import org.uqbar.commons.utils.Observable

@Observable
class ElegirEntradasDePresentacion(unaPresentacion : Presentacion) extends Serializable {
  val presentacionEscogida : Presentacion = unaPresentacion
  var entradaEscogida : Entrada = _
  var entradasDisponibles : ArrayList[Entrada] = new ArrayList[Entrada]
  this.setListaDisponibles
  
  def setListaDisponibles {
    var listaTemporal : ArrayList[Entrada] = new ArrayList[Entrada]
    presentacionEscogida.getEntradas.foreach(unaEntrada => if(unaEntrada.cliente == null) listaTemporal.add(unaEntrada))
    entradasDisponibles = listaTemporal
  }
}