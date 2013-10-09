package home
import domain.Cliente

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeClientes extends CollectionBasedHome[Cliente]{  
  
	this.create(1, 48, 'F', "Josefina Acronizarious")
    this.create(2, 67, 'M', "Elso Rete")
    this.create(3, 11, 'M', "Elber Dulero")
    this.create(4, 17, 'M', "Eltu Llido")
        
  def create(unDNI: Int, unaEdad: Int, unSexo: Char, unNombre: String): Unit = {
    this.create(Cliente.apply(unDNI, unaEdad, unSexo, unNombre))
  }

  def clientes: java.util.List[Cliente] = allInstances
  
  def clientesListaScala : List[Cliente] = {
    var listaDeClientes : List[Cliente] = List()
    allInstances.foreach(unCliente => listaDeClientes = listaDeClientes ++ List(unCliente))
    listaDeClientes
  }

  override def getEntityType = classOf[Cliente]

  override def createExample : Cliente = Cliente.apply(0, 0, 'M', "")

  override def getCriterio(example: Cliente) = null


}