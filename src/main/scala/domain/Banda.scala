package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Banda(unaCategoria: Categoria) extends Entity {

  var categoria: Categoria = unaCategoria

  def getCategoria: Categoria = categoria

}