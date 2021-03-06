package home

import domain.Sector
import domain.Fila
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

object HomeSectores extends CollectionBasedHome[Sector] {

  this.create('a', HomeFilas.filasListaScala)
  this.create('b', HomeFilas.filasListaScala)

  def create(unaLetraDeSector: Char, unaListaDeFilas: List[Fila]): Unit = {
    this.create(new Sector(unaLetraDeSector, unaListaDeFilas))
  }

  def sectores: java.util.List[Sector] = allInstances

  def get(unTipoDeSector: Char): Sector =
    sectores.find(unSector => unSector.getTipo == unTipoDeSector).getOrElse(null)

  override def getEntityType = classOf[Sector]

  override def createExample = new Sector('\0', null)

  override def getCriterio(example: Sector) = null

}