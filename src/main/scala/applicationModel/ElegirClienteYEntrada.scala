package applicationModel
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable

@Observable
class ElegirClienteYEntrada(unGestorDeCompra: GestorDeCompra) extends Serializable {
 
  var codigoDeReserva : String = unGestorDeCompra.codigoTipeado
  
  def setCodigoDeReserva(){
    unGestorDeCompra.codigoTipeado = codigoDeReserva
  }
  
  def validarEleccion(): Unit = {
    var mensajeDeError: String = ""
    if (unGestorDeCompra.clienteSeleccionado == null)
      mensajeDeError = mensajeDeError ++ "No se ha seleccionado ningún Cliente\n"
    if (unGestorDeCompra.entradaSeleccionada == null)
      mensajeDeError = mensajeDeError ++ "No se ha seleccionado ninguna Entrada\n"
      
      if(mensajeDeError != "")
        throw new UserException(mensajeDeError)
  }
 
}