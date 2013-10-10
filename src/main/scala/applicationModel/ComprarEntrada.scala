package applicationModel
import domain.Festival
import domain.Presentacion
import domain.Entrada
import org.uqbar.commons.utils.Observable
import java.util.ArrayList

@Observable
class ComprarEntrada(unaEntrada: Entrada) extends Serializable {

  var festivalSeleccionado: Festival = home.HomeFestivales.createExample
  var presentacionSeleccionada: Presentacion = null
  var listaDePresentaciones: ArrayList[Presentacion] = null
  var entradaEscogida: Entrada = unaEntrada
  
  def search() = {
    listaDePresentaciones = new ArrayList[Presentacion]
    listaDePresentaciones = this.getPresentacionesDeUnFestival
  }

  def getPresentacionesDeUnFestival: ArrayList[Presentacion] = {
    var devolver: ArrayList[Presentacion] = new ArrayList[Presentacion]
    festivalSeleccionado.presentaciones.foreach(unaPresentacion => devolver.add(unaPresentacion))
    devolver
  }
  
  def hayUnaPresentacionSeleccionada = {
    if (presentacionSeleccionada != null )
      new Object()
    else null
  }

}