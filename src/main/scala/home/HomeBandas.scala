package home

import domain.Banda
import domain.Categoria
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

object HomeBandas extends CollectionBasedHome[Banda] {

  def categoria(unTipoDeCategoria: Char): Categoria = {
    HomeCategorias.get(unTipoDeCategoria)
  }

  this.create(categoria('A'), "Blue Cold Flowers")
  this.create(categoria('B'), "Rhapsody of Fire")

  def create(unaCategoria: Categoria, unNombre: String): Unit = {
    this.create(new Banda(unaCategoria, unNombre))
  }

  def bandas: java.util.List[Banda] = allInstances

  def bandasListaScala: List[Banda] = {
    var listaDeBandas: List[Banda] = List()
    allInstances.foreach(unaBanda => listaDeBandas = listaDeBandas ++ List(unaBanda))
    listaDeBandas
  }

  def search(unNombre: String) = {
    bandas.filter(unaBanda => this.coincide(unNombre, unaBanda.nombre))
  }

  def coincide(expectedValue: Any, realValue: Any): Boolean = {
    if (expectedValue == null) {
      return true
    }
    if (realValue == null) {
      return false
    }
    return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase())
  }

  override def getEntityType = classOf[Banda]

  override def createExample = new Banda(HomeCategorias.createExample, "")

  override def getCriterio(example: Banda) = null

}