package home
import domain.Sector
import domain.Fila
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeSectores extends CollectionBasedHome[Sector] {
  
  this.create('a', HomeFilas.filasListaScala)
  this.create('b', HomeFilas.filasListaScala)

  def create(unaLetraDeSector : Char, unaListaDeFilas: List[Fila]): Unit = {
    this.create(new Sector(unaLetraDeSector, unaListaDeFilas))
  }

  def sectores : java.util.List[Sector] = allInstances

  override def getEntityType = classOf[Sector]

  override def createExample = new Sector('\0', null)

  override def getCriterio(example: Sector) = null

}