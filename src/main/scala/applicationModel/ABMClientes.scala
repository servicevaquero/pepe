package applicationModel
import domain.Festival
import org.uqbar.commons.model.UserException
import domain.Chango
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import domain.Entrada
import java.util.ArrayList
import domain.Cliente

@Observable
class ABMClientes(unCliente: Cliente) extends Serializable {

  var dniInicial: Int = unCliente.dni
  var dni: Int = unCliente.dni
  var nombre: String = unCliente.nombre
  var edad: Int = unCliente.edad
  var sexo: Char = unCliente.sexo

  def modificar() {
    var unClienteModificado = Cliente.apply(dni, edad, sexo, nombre)
    this.validarCliente
    home.HomeClientes.modificarCliente(dniInicial, unClienteModificado)
  }

  def validarCliente() {
    var mensajeDeError: String = ""
      
    if (sexo != 'F' && sexo != 'M')
      mensajeDeError = mensajeDeError ++ "El sexo solo puede ser 'F' o 'M'\n"
    if (edad < 0)
      mensajeDeError = mensajeDeError ++ "La edad no puede ser negativa\n"
    if (dni <= 0)
      mensajeDeError = mensajeDeError ++ "El DNI no puede ser negativo o 0\n"
    if (nombre.isEmpty())
      mensajeDeError = mensajeDeError ++ "El nombre no puede ser vacío\n"

    if (!mensajeDeError.isEmpty())
      throw new UserException(mensajeDeError)
  }

  def crear() {
    validarCliente()
    home.HomeClientes.create(dni, edad, sexo, nombre)
  }

}