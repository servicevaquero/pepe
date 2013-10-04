package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Butaca(unNumeroDeButaca: Int, unNumeroDeFila: Int, unSector: Sector) extends Entity {

  var nroDeButaca: Int = unNumeroDeButaca
  var nroFila: Int = unNumeroDeFila
  var sector: Sector = unSector
    
  def precioBase = sector.getFila(nroFila).getPrecio

}