package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
trait GateWay extends Entity{
	var estadoDeConexion : Boolean
	var tarjetasConocidas : List[Tarjeta]
	var pendientesDePago : List[Pago]
	
	def setEstadoDeConexion(unEstado : Boolean)
	def ingresarPago(unaTarjeta : Tarjeta, monto : Double)
}