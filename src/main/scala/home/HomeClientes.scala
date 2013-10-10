package home

import domain.Cliente
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException

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

  def modificarCliente(unDNI: Int, unClienteModificado: Cliente) {
    //print("\n\n\n\nHasta aca anda 1\n\n\n\n")
    var unCliente = this.get(unDNI)
    //print("\n\n\n\nHasta aca anda 2\n\n\n\n")
    unCliente.dni = unClienteModificado.dni
    //print("\n\n\n\nHasta aca anda 3\n\n\n\n")
    unCliente.nombre = unClienteModificado.nombre
    //print("\n\n\n\nHasta aca anda 4\n\n\n\n")
    unCliente.edad = unClienteModificado.edad
    unCliente.sexo = unClienteModificado.sexo
  }

  def search(unDNI: Integer, unNombre: String = null) = {
    clientes.filter { unCliente => this.coincide(unDNI, unCliente.dni) && this.coincide(unNombre, unCliente.nombre) }
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

  override def validateCreate(cliente: Cliente): Unit = {
    validarClientesDuplicados(cliente)
  }

  def validarClientesDuplicados(cliente: Cliente): Unit = {
    if (this.get(cliente.dni) != null) {
      throw new UserException("Ya existe un cliente con el dni: " + cliente.dni)
    }
  }

  def get(dni: Int) = clientes.find(_.dni == dni).getOrElse(null)

  override def getEntityType = classOf[Cliente]

  override def createExample: Cliente = Cliente.apply(0, 0, '\0', "nombre del cliente")

  override def getCriterio(example: Cliente) = null

}