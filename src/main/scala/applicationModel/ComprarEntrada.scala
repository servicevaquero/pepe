package applicationModel
import domain.Festival
import domain.Presentacion
import home.HomeFestivales
import org.uqbar.commons.utils.Observable
import home.HomePresentaciones
import java.util.ArrayList

@Observable
class ComprarEntrada extends Serializable {

  var festivalSeleccionado: Festival = home.HomeFestivales.createExample
  var presentacionSeleccionada: Presentacion = _
  var listaDePresentaciones: java.util.ArrayList[Presentacion] = _
  
  def search() = {   
	listaDePresentaciones = new ArrayList[Presentacion]	
	listaDePresentaciones = this.getPresentacionesDeUnFestival    
  }
  
  def getPresentacionesDeUnFestival : java.util.ArrayList[Presentacion] = {		
    var devolver : java.util.ArrayList[Presentacion] = new ArrayList[Presentacion]
    festivalSeleccionado.presentaciones.foreach(p => devolver.add(p))
    devolver
  	}

}