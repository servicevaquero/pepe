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
class ABMClientes() extends Serializable {
  var clienteSeleccionado : Cliente = _
  var clientes : java.util.List[Cliente] = home.HomeClientes.clientes
	
}