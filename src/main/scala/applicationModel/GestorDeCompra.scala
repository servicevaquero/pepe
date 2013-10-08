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
  var entradaSeleccionada: Entrada = _
  var entradasElegidas : ArrayList[Entrada] = _
  
  def agregarEntradas(unaEntrada : Entrada, unCodigo : String, unCliente : Cliente){
    unChanguito.agregarEntrada(unaEntrada, unCodigo, unCliente)
    setEntradasEscogidas
  }
  
  def setEntradasEscogidas {
    var listaTemporal : ArrayList[Entrada] = new ArrayList[Entrada]
    unChanguito.entradasEscogidas.foreach(unaEntrada => listaTemporal.add(unaEntrada))
    entradasElegidas = listaTemporal
  }
  
  def listaNULLorNotNULL = {
    if (entradasElegidas.size() > 0){
      new Object
      print("\n\n\n\nHOLA")
    }
    else{
      null
    }
  }

}