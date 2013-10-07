package home
import domain.Presentacion
import domain.Festival
import domain.Banda

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomePresentaciones extends CollectionBasedHome[Presentacion] {
    
  this.create("01/01/2013", HomeBandas.bandasListaScala)
  this.create("01/02/2013", HomeBandas.bandasListaScala)

  def create(unaFecha: String, unaListaDeBandas : List[Banda]) : Unit = {
    this.create(new Presentacion(unaFecha, unaListaDeBandas))
  }

  def presentaciones : java.util.List[Presentacion] = allInstances
  
  def presentacionesListaScala : List[Presentacion] = {
    var listaDePresentaciones : List[Presentacion] = List()
    allInstances.foreach(unaPresentacion => listaDePresentaciones = listaDePresentaciones ++ List(unaPresentacion))
    listaDePresentaciones
  }

  override def getEntityType = classOf[Presentacion]

  override def createExample = new Presentacion("", HomeBandas.bandasListaScala)

  override def getCriterio(example: Presentacion) = null


}