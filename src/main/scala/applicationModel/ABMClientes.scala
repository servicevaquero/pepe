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
class ABMClientes(unCliente: Cliente) extends Serializable {

  var dniInicial: Int = unCliente.dni
  var dni: Int = unCliente.dni
  var nombre: String = unCliente.nombre
  var edad: Int = unCliente.edad
  var sexo: Char = unCliente.sexo

  def modificar() {
    var unClienteModificado = Cliente.apply(dni, edad, sexo, nombre)
    home.HomeClientes.modificarCliente(dniInicial, unClienteModificado)
  }

}