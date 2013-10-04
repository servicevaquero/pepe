package home
import domain.Butaca
import domain.Sector

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeButacas extends CollectionBasedHome[Butaca] {

  this.create(new Butaca(1, 1, sector('a')))
  this.create(new Butaca(1, 2, sector('a')))
  this.create(new Butaca(1, 1, sector('b')))
  this.create(new Butaca(1, 2, sector('b')))

  def create(unNumeroDeButaca: Int, unNumeroDeFila: Int, unSector: Sector): Unit = {
    this.create(new Butaca(unNumeroDeButaca, unNumeroDeFila, unSector))
  }

  def sector(unTipoDeSector: Char): Sector = {
    HomeSectores.get(unTipoDeSector)
  }

  def get(unNumeroDeButaca: Int, unNumeroDeFila: Int, unTipoDeSector: Char): Butaca =
    butacas.find(unaButaca => unaButaca.nroDeButaca == unNumeroDeButaca && unaButaca.nroFila == unNumeroDeFila && unaButaca.sector == sector(unTipoDeSector)).getOrElse(null)

  def coincide(expectedValue: Any, realValue: Any): Boolean = {
    if (expectedValue == null) {
      return true
    }
    if (realValue == null) {
      return false
    }
    return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase())
  }

  def butacasListaScala: List[Butaca] = {
    var listaDeButacas: List[Butaca] = List()
    allInstances.foreach(unaButaca => listaDeButacas = listaDeButacas ++ List(unaButaca))
    listaDeButacas
  }

  def butacas: java.util.List[Butaca] = allInstances

  override def getEntityType = classOf[Butaca]

  override def createExample = new Butaca(0, 0, HomeSectores.createExample)

  override def getCriterio(example: Butaca) = null

}