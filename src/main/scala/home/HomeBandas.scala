package home

import domain.Banda
import domain.Categoria
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

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
  
  def bandasListaScala : List[Banda] = {
    var listaDeBandas : List[Banda] = List()
    allInstances.foreach(unaBanda => listaDeBandas = listaDeBandas ++ List(unaBanda))
    listaDeBandas
  }

  override def getEntityType = classOf[Banda]

  override def createExample = new Banda(HomeCategorias.createExample)

  override def getCriterio(example: Banda) = null

}