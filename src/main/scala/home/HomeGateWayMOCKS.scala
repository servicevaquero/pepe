package home

import domain.GateWayMOCK
import domain.Tarjeta
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.utils.Observable

@Observable
object HomeGateWayMOCKS extends CollectionBasedHome[GateWayMOCK] {
  
  this.create(true, HomeTarjetas.tarjetasListaScala)

  def create(unEstado : Boolean, unaListaDeTarjetas : List[Tarjeta]): Unit = {
    this.create(new GateWayMOCK(unEstado, unaListaDeTarjetas))
  }

  def gateways: java.util.List[GateWayMOCK] = allInstances

  override def getEntityType = classOf[GateWayMOCK]

  override def createExample = new GateWayMOCK(true, HomeTarjetas.tarjetasListaScala)

  override def getCriterio(example: GateWayMOCK) = null

}