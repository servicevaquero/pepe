package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Presentacion(unaFecha: String, unaListaDeBandas : List[Banda]) extends Entity {

  val fecha: String = unaFecha
  val listaDeBandas: List[Banda] = unaListaDeBandas
  var listaDeEntradas: List[Entrada] = List()
  def getFecha = fecha
  def getCategoriaString : Char = this.getCategoria.getTipoCategoria
  
  def getCategoria: Categoria = {	
    val categoriaMaxima: Char = listaDeBandas.map(banda => banda.getCategoria.getTipoCategoria).max	
    val banda: Banda = listaDeBandas.filter(banda => banda.getCategoria.getTipoCategoria == categoriaMaxima).head    
    banda.getCategoria    
  }
  
  def creaTusEntradas(unasButacas : List[Butaca], unFestival : Festival){
    unasButacas.foreach(unaButaca => listaDeEntradas = listaDeEntradas ++ List(new Entrada(unFestival, this, unaButaca)))
  }
  
  def dameUnaEntradaConButaca(unaButaca : Butaca) = {
    listaDeEntradas.filter(unaEntrada => unaEntrada.getButaca == unaButaca)
  }

  def valorExtraPorNoche: Double = getCategoria.getExtraPorNoche
  def getEntradas : List[Entrada] = listaDeEntradas   
  
}

