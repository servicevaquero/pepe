package applicationModel

import java.util.ArrayList
import org.uqbar.commons.utils.Observable
import collection.JavaConversions._
import domain.Banda
import domain.Festival
import home.HomeBandas

@Observable
class VerBandasPorFestival extends Serializable {
  
	var nombre : String = _
	var resultados : java.util.List[Banda] = _
	var festivalSeleccionado: Festival = _

	def search() = { 
		resultados = new ArrayList[Banda]
		resultados = HomeBandas.search(nombre)
	}

	def clear() = {
		nombre = null
	}
	
}