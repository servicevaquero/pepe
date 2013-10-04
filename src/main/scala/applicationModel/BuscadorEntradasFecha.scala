package applicationModel

import domain._
import java.util.ArrayList

@org.uqbar.commons.utils.Observable
class BuscadorEntradasFecha extends Serializable {

	var cliente : Cliente = _
	var fechaDesde : Int = _
	var fechaHasta : Int = _
	var resultados : java.util.List[Entrada] = _

	def search() = { 
		resultados = new ArrayList[Entrada]
		resultados = HomeCelulares.search(numero, nombre)
	}

	def clear() = {
		cliente = null
		fechaDesde = null
		fechaHasta = null
	}

}