package home
import domain.Cliente

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeClientes extends CollectionBasedHome[Cliente] {

  this.create(1, 48, 'F', "Josefina Acronizarious")
  this.create(2, 67, 'M', "Elso Rete")
  this.create(3, 11, 'M', "Elber Dulero")
  this.create(4, 17, 'M', "Eltu Llido")

  def create(unDNI: Int, unaEdad: Int, unSexo: Char, unNombre: String): Unit = {
    this.create(Cliente.apply(unDNI, unaEdad, unSexo, unNombre))
  }

  def clientes: java.util.List[Cliente] = allInstances

  def clientesListaScala: List[Cliente] = {
    var listaDeClientes: List[Cliente] = List()
    allInstances.foreach(unCliente => listaDeClientes = listaDeClientes ++ List(unCliente))
    listaDeClientes
  }

  override def validateCreate(cliente: Cliente): Unit = {
    validarClientesDuplicados(cliente)
  }

  def validarClientesDuplicados(cliente: Cliente): Unit = {
    if (this.get(cliente.dni) != null) {
      throw new UserException("Ya existe un cliente con el dni: " + cliente.dni)
    }
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

  def get(dni: Int) = clientes.find(_.dni == dni).getOrElse(null)

  override def getEntityType = classOf[Cliente]

  override def createExample: Cliente = Cliente.apply(0, 0, 'M', "")

  override def getCriterio(example: Cliente) = null

}