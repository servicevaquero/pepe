package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Categoria(unTipoDeCategoria: Char, unPrecio: Double) extends Entity {

  var tipoCategoria: Char = unTipoDeCategoria
  var precio: Double = unPrecio

  def getExtraPorNoche: Double = precio

  def getTipoCategoria: Char = tipoCategoria

}