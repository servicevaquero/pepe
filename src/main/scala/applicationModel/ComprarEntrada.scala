package applicationModel
import domain.Festival
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import home.HomePresentaciones
import java.util.ArrayList

@Observable
class ComprarEntrada(unGestorDeCompra: GestorDeCompra) extends Serializable {

  var gestorDeCompra: GestorDeCompra = unGestorDeCompra
  var festivalSeleccionado: Festival = home.HomeFestivales.createExample
  var presentacionSeleccionada: Presentacion = null
  var listaDePresentaciones: ArrayList[Presentacion] = null
  
  def search() = {
    listaDePresentaciones = new ArrayList[Presentacion]
    listaDePresentaciones = this.getPresentacionesDeUnFestival
  }

  def getPresentacionesDeUnFestival: ArrayList[Presentacion] = {
    var devolver: ArrayList[Presentacion] = new ArrayList[Presentacion]
    festivalSeleccionado.presentaciones.foreach(unaPresentacion => devolver.add(unaPresentacion))
    devolver
  }

  def sePuedeGenerarEntrada = gestorDeCompra.clienteSeleccionado
  
  def hayUnaPresentacionSeleccionada = {
    if (presentacionSeleccionada != null )
      new Object()
    else null
  }

}