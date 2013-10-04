package home
import domain.Tarjeta

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeTarjetas extends CollectionBasedHome[Tarjeta] {

  this.create("Sanchez", "José", 1, 3000.0)
  this.create("García","Clotildea Teodosia", 2, 100.0)

  def create(unApellido : String, unNombre : String, unNumero : Int, unSaldo : Double): Unit = {
    this.create(new Tarjeta(unApellido, unNombre, unNumero, unSaldo))
  }

  def tarjetas: java.util.List[Tarjeta] = allInstances
  
  def tarjetasListaScala : List[Tarjeta] = {
    var listaDeTarjetas : List[Tarjeta] = List()
    allInstances.foreach(unaTarjeta => listaDeTarjetas = listaDeTarjetas ++ List(unaTarjeta))
    listaDeTarjetas
  }

  override def getEntityType = classOf[Tarjeta]

  override def createExample = new Tarjeta("","",0,0)

  override def getCriterio(example: Tarjeta) = null

}