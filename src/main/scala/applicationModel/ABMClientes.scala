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
class ABMClientes(unCliente : Cliente) extends Serializable {
  var clienteSeleccionado : Cliente = unCliente
	
}