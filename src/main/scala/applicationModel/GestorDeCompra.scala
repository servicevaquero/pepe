package applicationModel

import domain.ExcepcionButacaOcupada
import domain.ExcepcionCodigoInvalido
import org.uqbar.commons.model.UserException
import domain.Chango
import org.uqbar.commons.utils.Observable
import domain.Entrada
import java.util.ArrayList

@Observable
class GestorDeCompra extends Serializable {

  var unChanguito: Chango = home.HomeChango.createExample
  var entradasElegidas: ArrayList[Entrada] = null
  var elector: ElegirClienteYEntrada = null  
  var precio: Double = 0.0

  def dameUnElector : ElegirClienteYEntrada = {
    elector = new ElegirClienteYEntrada
    elector
  }

  def setPrecio() {
    precio = unChanguito.calcularPrecio
  }

  def chequearElector(){
    if(elector.clienteSeleccionado != null && elector.entradaSeleccionada != null)	
      try{
      agregarEntradas()
      } catch{ // NO DEBERIA PASAR JAMAS, perooooooooooooooooooooooooo... XD
      case butacaOcupada: (ExcepcionButacaOcupada) => throw new UserException(butacaOcupada.getMessage())
      case codigoInvalido: (ExcepcionCodigoInvalido) => throw new UserException(codigoInvalido.getMessage())
    } 
  }
  
  def agregarEntradas() {
    unChanguito.agregarEntrada(elector.entradaSeleccionada, elector.codigoTipeado, elector.clienteSeleccionado)
    setEntradasEscogidas
    setPrecio
  }

  def reload() {
    this.chequearElector()
    var temporalEntradasElegidas: ArrayList[Entrada] = entradasElegidas    
    entradasElegidas = new ArrayList[Entrada]
    entradasElegidas = temporalEntradasElegidas
  }

  def setEntradasEscogidas {
    var listaTemporal: ArrayList[Entrada] = new ArrayList[Entrada]
    unChanguito.entradasEscogidas.foreach(unaEntrada => listaTemporal.add(unaEntrada))
    entradasElegidas = listaTemporal
  }

  def limpiarEleccion {
    entradasElegidas = null
  } 

}