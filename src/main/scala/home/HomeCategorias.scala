package home

import domain.Categoria
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

object HomeCategorias extends CollectionBasedHome[Categoria] {

  this.create('A', 0)
  this.create('B', 50)

  def create(unTipoDeCategoria: Char, unPrecio: Double): Unit = {
    this.create(new Categoria(unTipoDeCategoria, unPrecio))
  }

  def categorias: java.util.List[Categoria] = allInstances

  def get(unTipoDeCategoria: Char): Categoria =
    categorias.find(categoria => categoria.getTipoCategoria == unTipoDeCategoria).getOrElse(null)

  override def getEntityType = classOf[Categoria]

  override def createExample = new Categoria('\0', 0)

  override def getCriterio(example: Categoria) = null

}