package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Banda(unaCategoria: Categoria, unNombre: String) extends Entity {

  var categoria: Categoria = unaCategoria
  var nombre: String = unNombre

  def getCategoria: Categoria = categoria

}