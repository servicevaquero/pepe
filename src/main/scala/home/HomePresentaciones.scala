package home
import domain.Presentacion
import domain.Festival
import domain.Banda

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

object HomePresentaciones extends CollectionBasedHome[Presentacion] {
    
  this.create("01/01/2013", HomeBandas.bandasListaScala, "Dreamers")
  this.create("01/02/2014", HomeBandas.bandasListaScala, "Sleepers")
  this.create("05/01/2013", HomeBandas.bandasListaScala, "Kunvia billeteeera")
  this.create("19/02/2014", HomeBandas.bandasListaScala, "La malotes de Arena")
  this.create("09/01/2013", HomeBandas.bandasListaScala, "Die bitch")

  def create(unaFecha: String, unaListaDeBandas : List[Banda], unNombre: String) : Unit = {
    this.create(new Presentacion(unaFecha, unaListaDeBandas, unNombre))
  }

  def presentaciones : java.util.List[Presentacion] = allInstances
  
  def presentacionesListaScala : List[Presentacion] = {
    var listaDePresentaciones : List[Presentacion] = List()
    allInstances.foreach(unaPresentacion => listaDePresentaciones = listaDePresentaciones ++ List(unaPresentacion))
    listaDePresentaciones
  }

  override def getEntityType = classOf[Presentacion]

  override def createExample = new Presentacion("", List(HomeBandas.createExample), "")

  override def getCriterio(example: Presentacion) = null


}