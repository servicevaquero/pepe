package applicationModel

import java.util.ArrayList
import org.uqbar.commons.utils.Observable
import collection.JavaConversions._
import domain.Cliente
import home.HomeClientes

@Observable
class SeleccionarCliente(unElector: ElegirClienteYEntrada) extends Serializable {
  
	var dni : Integer = _
	var nombre : String = _
	var resultados : java.util.List[Cliente] = _
	var clienteSeleccionado : Cliente = unElector.clienteSeleccionado

	def search() = { 
		resultados = new ArrayList[Cliente]
		resultados = HomeClientes.search(dni, nombre)
	}

	def clear() = {
		dni = null
		nombre = null
	}
	
	def setCliente() = {
	  unElector.clienteSeleccionado = clienteSeleccionado
	}
	
}