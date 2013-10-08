package applicationModel
import domain.Festival
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import domain.Entrada
import java.util.ArrayList

@Observable
class GestorDeCompra extends Serializable {

  var entradaSeleccionada: Entrada = _
  var entradasEscogidas: java.util.ArrayList[Entrada] = _

  def search() = {
    entradasEscogidas = new ArrayList[Entrada]
    //listaDeEntradas =     
  }

  def listaNULLorNotNULL = {
    if (entradasEscogidas.size() > 0)
      new Object
    else null
  }

}