package applicationModel
import domain.Chango
import org.uqbar.commons.utils.Observable
import domain.ExcepcionTarjetaRechazada
import org.uqbar.commons.model.UserException
import domain.Tarjeta

@Observable
class Pagar(unChango: Chango) extends Serializable {
  
  var unaTarjeta : Tarjeta = home.HomeTarjetas.allInstances().get(0)
  var precio : Double = unChango.calcularPrecio

  def realizarPagoConTarjeta() {
    try{
    print("\n\n\nSaldo Disponible: " ++ unaTarjeta.getSaldoDisponible.toString ++ "\n\n\n")
    unChango.realizarPagoConTarjeta(unaTarjeta)
    print("\n\n\nSaldo Disponible: " ++ unaTarjeta.getSaldoDisponible.toString ++ "\n\n\n")
  }catch {
    case tarjetaRechazada: ExcepcionTarjetaRechazada => throw new UserException(tarjetaRechazada.getMessage())
    }
  }

}