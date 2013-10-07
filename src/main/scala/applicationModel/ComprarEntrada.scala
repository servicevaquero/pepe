package applicationModel
import domain.Festival
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import home.HomePresentaciones
import java.util.ArrayList

@Observable
class ComprarEntrada extends Serializable {

  var festivalSeleccionado: Festival = HomeFestivales.festivales.get(0)
  var presentacionSeleccionada: Presentacion = _
  var listaDePresentaciones: java.util.List[Presentacion] = _
  
  def search() = {
    print(this.festivalSeleccionado.toString())
	listaDePresentaciones = new ArrayList[Presentacion]
    festivalSeleccionado.presentaciones.foreach(unaPresentacion => listaDePresentaciones.add(unaPresentacion))
    listaDePresentaciones
  }

}