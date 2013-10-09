package applicationModel
import org.uqbar.commons.model.UserException
import domain.Entrada
import domain.Cliente
import org.uqbar.commons.utils.Observable

@Observable
class ElegirClienteYEntrada() extends Serializable {

  var entradaSeleccionada: Entrada = null
  var clienteSeleccionado: Cliente = null
  var codigoTipeado: String = ""

  def validarEleccion(): Unit = {
    var mensajeDeError: String = ""
    if (clienteSeleccionado == null)
      mensajeDeError = mensajeDeError ++ "No se ha seleccionado ningún Cliente\n"
    if (entradaSeleccionada == null)
      mensajeDeError = mensajeDeError ++ "No se ha seleccionado ninguna Entrada\n"

    if (mensajeDeError != "")
      throw new UserException(mensajeDeError)
  }

}