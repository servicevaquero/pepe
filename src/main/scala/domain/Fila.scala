package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

class Fila(unNumeroDeFila: Int, unPrecio: Double) extends Entity {

  var nroFila: Int = unNumeroDeFila
  var precio: Double = unPrecio
  
  def getNroFila = nroFila
  
  def getPrecio = precio
  
}