package applicationModel
import domain.Entrada
import java.util.ArrayList
import org.uqbar.commons.utils.Observable

@Observable
class AnularEntrada extends Serializable {
  var entradasNoDisponibles: ArrayList[Entrada] = new ArrayList[Entrada]
  var entradaEscogida: Entrada = _
  this.setEntradasNoDisponibles

  def setEntradasNoDisponibles() {
    var listaTemporal: ArrayList[Entrada] = new ArrayList[Entrada]
    home.HomeFestivales.festivalesListaScala.foreach(unFestival =>
      unFestival.presentaciones.foreach(unaPresentacion =>
        unaPresentacion.getEntradas.foreach(unaEntrada =>
          if (unaEntrada.cliente != null) listaTemporal.add(unaEntrada))))
    entradasNoDisponibles = listaTemporal
  }

  def anularEntradaEscogida() {
    entradaEscogida.cliente = null
  }

}