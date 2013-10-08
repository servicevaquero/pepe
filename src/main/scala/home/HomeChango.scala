package home
import domain.Chango
import domain.GateWay

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import org.uqbar.commons.utils.Observable

@Observable
object HomeChango extends CollectionBasedHome[Chango] {

  this.create(HomeGateWayMOCKS.gateways.get(0))
  
  def create(unGateWay : GateWay): Unit = {
    this.create(new Chango(unGateWay))
  }

  def changos: java.util.List[Chango] = allInstances

  override def getEntityType = classOf[Chango]

  override def createExample = new Chango(HomeGateWayMOCKS.createExample)

  override def getCriterio(example: Chango) = null
}