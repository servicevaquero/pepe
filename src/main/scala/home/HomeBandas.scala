package home
import domain.Banda
import domain.Categoria

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeBandas extends CollectionBasedHome[Banda] {

  def categoria(unTipoDeCategoria: Char): Categoria = {
    HomeCategorias.get(unTipoDeCategoria)
  }
  
  this.create(categoria('A'))
  this.create(categoria('B'))

  def create(unaCategoria : Categoria) : Unit = {
    this.create(new Banda(unaCategoria))
  }

  def bandas : java.util.List[Banda] = allInstances

  override def getEntityType = classOf[Banda]

  override def createExample = new Banda(HomeCategorias.createExample)

  override def getCriterio(example: Banda) = null

}