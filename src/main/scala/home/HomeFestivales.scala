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
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

object HomeFestivales extends CollectionBasedHome[Festival] {

  val descuentosA: List[TipoDeCliente] = List(Adulto, Menor, MenorDeDoce, Jubilado)
  val descuentosB: List[TipoDeCliente] = List(Dama, MenorDeDoce, Jubilado)
  val listaDeButacasReservadas : List[Butaca] = List(HomeButacas.get(1, 2, 'b'))
  val listaDeButacasVIP : List[Butaca] = List(HomeButacas.get(1, 1, 'b'))
  
  this.create(HomeButacas.butacasListaScala, listaDeButacasReservadas, listaDeButacasVIP, 0.5, HomePresentaciones.presentacionesListaScala, descuentosA)
  this.create(HomeButacas.butacasListaScala, listaDeButacasReservadas, listaDeButacasVIP, 0.5, HomePresentaciones.presentacionesListaScala, descuentosB)

  def create(unaListaDeButacas: List[Butaca], unaListaDeButacasReservadas: List[Butaca], unaListaDeButacasVIP: List[Butaca], unRecargo: Double, unaListaDePresentaciones: List[Presentacion], unaListaDeDescuentos: List[TipoDeCliente]): Unit = {
    this.create(new Festival(unaListaDeButacas, unaListaDeButacasReservadas, unaListaDeButacasVIP, unRecargo, unaListaDePresentaciones, unaListaDeDescuentos))
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

  override def getEntityType = classOf[Festival]

  override def createExample = new Festival(null, null, null, 0, null, null)

  override def getCriterio(example: Festival) = null

}