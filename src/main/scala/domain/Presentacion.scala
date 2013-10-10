package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Presentacion(unaFecha: String, unaListaDeBandas : List[Banda], unNombre: String) extends Entity {

  val fecha: String = unaFecha
  val listaDeBandas: List[Banda] = unaListaDeBandas
  var listaDeEntradas: List[Entrada] = List()
  var nombre: String = unNombre
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
  
  def listaDeEntradasJava : java.util.ArrayList[Entrada] = {
    var javaList : java.util.ArrayList[Entrada] = new java.util.ArrayList[Entrada]
    listaDeEntradas.foreach(unaEntrada => javaList.add(unaEntrada))
    javaList    
  }

  def valorExtraPorNoche: Double = getCategoria.getExtraPorNoche
  def getEntradas : List[Entrada] = listaDeEntradas   
  
}

