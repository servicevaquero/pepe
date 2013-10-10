package home

import domain.Fila
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome

object HomeFilas extends CollectionBasedHome[Fila] {

  this.create(1, 35)
  this.create(2, 22)

  def create(unNumeroDeFila: Int, unPrecio: Double): Unit = {
    this.create(new Fila(unNumeroDeFila, unPrecio))
  }

  def filas: java.util.List[Fila] = allInstances
  
  def filasListaScala : List[Fila] = {
    var listaDeFilas : List[Fila] = List()
    allInstances.foreach(unaFila => listaDeFilas = listaDeFilas ++ List(unaFila))
    listaDeFilas
  }

  override def getEntityType = classOf[Fila]

  override def createExample = new Fila(0, 0)

  override def getCriterio(example: Fila) = null

}