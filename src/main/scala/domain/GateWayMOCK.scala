package domain;

import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class GateWayMOCK(unEstado : Boolean, unaListaDeTarjetas : List[Tarjeta]) extends GateWay{
	var estadoDeConexion : Boolean = unEstado
	var tarjetasConocidas : List[Tarjeta] = unaListaDeTarjetas
	var pendientesDePago : List[Pago] = List()
	
	//ademas de settear la conexion, si esta pasa de OFF a ON, agarra todas las pendientes y las ejecuta
	@Override
	def setEstadoDeConexion(unEstado : Boolean){
	  estadoDeConexion = unEstado
	  if(estadoDeConexion)
		  ejecutarPendientesDePago
	  }
	
	@Override
	def ingresarPago(unaTarjeta: Tarjeta, monto : Double) {	  
	  val unPago = new Pago(unaTarjeta, monto)
	  
	  if(!estadoDeConexion)
	    registrarComoPendiente(unPago)
	  else realizarPago(unPago)	  
	}
	
	def registrarComoPendiente(unPago : Pago){
	  pendientesDePago = pendientesDePago ++ List(unPago)
	  //println("Estado Pendiente")
	}
	
	def ejecutarPendientesDePago{
	  if(pendientesDePago.length > 0){
		  val unPago = pendientesDePago.head
		  pendientesDePago = pendientesDePago.tail
		  try{
			  realizarPago(unPago)
		  }
		  finally{
		  ejecutarPendientesDePago
		  }
	  }
	}
	
	def realizarPago(unPago : Pago){	  
		  validarTarjeta(unPago)
		  unPago.getTarjeta.debitar(unPago.getMonto)
		  //println("Pago realizado exitosamente")
	}
	
	def validarTarjeta(unPago : Pago){
	  
	   val tarjetaAValidar = tarjetasConocidas.filter(tarjeta => tarjeta.getNumero == unPago.getNumero).head
	   
	   if(tarjetaAValidar == null)
	     throw new ExcepcionTarjetaRechazada("Numero de tarjeta invalido")
	   
	   if(tarjetaAValidar.getNombre != unPago.getNombre || tarjetaAValidar.getApellido != unPago.getApellido)
	     throw new ExcepcionTarjetaRechazada("Apellido y/o nombre incorrectos")
	   
	   if(tarjetaAValidar.getSaldoDisponible < unPago.getMonto)
	     throw new ExcepcionTarjetaRechazada("Saldo insuficiente")  
	}

}

class Pago(unaTarjeta : Tarjeta, unMonto : Double){
  
  var tarjeta = unaTarjeta
  var monto = unMonto
  
  def getApellido = tarjeta.getApellido
  def getNombre = tarjeta.getNombre
  def getNumero = tarjeta.getNumero
  def getMonto = monto
  def getTarjeta = tarjeta
}

@Observable
class Tarjeta(unApellido : String, unNombre : String, unNumero : Int, unSaldo : Double) extends Entity{
  
  var apellido = unApellido
  var nombre = unNombre
  var numero = unNumero
  var saldoDisponible = unSaldo
  
  def getApellido = apellido
  def getNombre = nombre
  def getNumero = numero
  def getSaldoDisponible = saldoDisponible
  def debitar(unMonto : Double){ saldoDisponible -= unMonto }
}