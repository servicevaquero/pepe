package home

import domain.Festival
import domain.Butaca
import domain.Presentacion
import domain.TipoDeCliente
import domain.Adulto
import domain.Menor
import domain.MenorDeDoce
import domain.Dama
import domain.Jubilado
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.utils.Observable

@Observable
object HomeFestivales extends CollectionBasedHome[Festival] {

  val descuentosA: List[TipoDeCliente] = List(Adulto, Menor, MenorDeDoce, Jubilado)
  val descuentosB: List[TipoDeCliente] = List(Dama, MenorDeDoce, Jubilado)
  val listaDeButacasReservadas : List[Butaca] = List(HomeButacas.get(1, 2, 'b'))
  val listaDeButacasVIP : List[Butaca] = List(HomeButacas.get(1, 1, 'b'))
  val listaDePresentacionesA: List[Presentacion] = List(HomePresentaciones.presentaciones.get(0), HomePresentaciones.presentaciones.get(2), HomePresentaciones.presentaciones.get(4))
  val listaDePresentacionesB: List[Presentacion] = List(HomePresentaciones.presentaciones.get(1), HomePresentaciones.presentaciones.get(3))
  
  this.create("Festival Primero", HomeButacas.butacasListaScala, listaDeButacasReservadas, listaDeButacasVIP, 0.5, listaDePresentacionesA, descuentosA)
  this.create("Festival Segundo", HomeButacas.butacasListaScala, listaDeButacasReservadas, listaDeButacasVIP, 0.5, listaDePresentacionesB, descuentosB)
  
  this.crearEntradas
  
  def crearEntradas{
    festivalesListaScala.foreach(unFestival => unFestival.crearEntradasParaCadaPresentacion)
  }

  def create(nombreDelFestival : String, unaListaDeButacas: List[Butaca], unaListaDeButacasReservadas: List[Butaca], unaListaDeButacasVIP: List[Butaca], unRecargo: Double, unaListaDePresentaciones: List[Presentacion], unaListaDeDescuentos: List[TipoDeCliente]): Unit = {
    this.create(new Festival(nombreDelFestival, unaListaDeButacas, unaListaDeButacasReservadas, unaListaDeButacasVIP, unRecargo, unaListaDePresentaciones, unaListaDeDescuentos))
  }

  def get(unNumeroDeButaca: Int): Festival = //DESNEGREAR
    festivales.find(unFestival => false).getOrElse(null)

  def coincide(expectedValue: Any, realValue: Any): Boolean = {
    if (expectedValue == null) {
      return true
    }
    if (realValue == null) {
      return false
    }
    return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase())
  }

  def festivales: java.util.List[Festival] = allInstances
  
   def festivalesListaScala : List[Festival] = {
    var listaDeFestivales : List[Festival] = List()
    allInstances.foreach(unFestival => listaDeFestivales = listaDeFestivales ++ List(unFestival))
    listaDeFestivales
  }

  override def getEntityType = classOf[Festival]

  override def createExample = new Festival("Example", List(), List(), List(), 0, List(), List())

  override def getCriterio(example: Festival) = null

}