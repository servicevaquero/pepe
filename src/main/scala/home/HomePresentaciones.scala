package home
import domain.Presentacion
import domain.Banda

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomePresentaciones extends CollectionBasedHome[Presentacion] {
    
  this.create(20130101, HomeBandas.bandasListaScala)
  this.create(20120101, HomeBandas.bandasListaScala)

  def create(unaFecha: Int, unaListaDeBandas : List[Banda]) : Unit = {
    this.create(new Presentacion(unaFecha, unaListaDeBandas))
  }

  def presentaciones : java.util.List[Presentacion] = allInstances
  
  def presentacionesListaScala : List[Presentacion] = {
    var listaDePresentaciones : List[Presentacion] = List()
    allInstances.foreach(unaPresentacion => listaDePresentaciones = listaDePresentaciones ++ List(unaPresentacion))
    listaDePresentaciones
  }

  override def getEntityType = classOf[Presentacion]

  override def createExample = new Presentacion(0, HomeBandas.bandasListaScala)

  override def getCriterio(example: Presentacion) = null


}