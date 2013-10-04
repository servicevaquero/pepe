package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Sector(unTipoDeSector: Char, unaListaDeFilas: List[Fila]) extends Entity{

  var tipDeSector: Char = unTipoDeSector
  var listaDeFilas: List[Fila] = unaListaDeFilas

  def getFila(nroFila: Int) : Fila = listaDeFilas.filter(_.getNroFila == nroFila).last

}