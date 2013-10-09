package applicationModel
import domain.Festival
import domain.Chango
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import domain.Entrada
import java.util.ArrayList
import domain.Cliente

@Observable
class GestorDeCompra extends Serializable {

  var unChanguito: Chango = home.HomeChango.createExample
  var entradasElegidas: ArrayList[Entrada] = _

  var entradaSeleccionada: Entrada = null
  var clienteSeleccionado: Cliente = null
  var codigoTipeado: String = ""

  def agregarEntradas() {
    unChanguito.agregarEntrada(entradaSeleccionada, codigoTipeado, clienteSeleccionado)
    setEntradasEscogidas
  }

  def setEntradasEscogidas {
    var listaTemporal: ArrayList[Entrada] = new ArrayList[Entrada]
    unChanguito.entradasEscogidas.foreach(unaEntrada => listaTemporal.add(unaEntrada))
    entradasElegidas = listaTemporal
  }

  def listaNULLorNotNULL = {
    if (entradasElegidas.size() > 0) {
      new Object
    } else {
      null
    }
  }

  def tenesNulls: Boolean = entradaSeleccionada == null || clienteSeleccionado == null

}