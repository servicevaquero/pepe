package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Entrada(unFestival : Festival, unaPresentacion: Presentacion, unaButaca: Butaca) extends Entity {

  val festival : Festival = unFestival
  val presentacion: Presentacion = unaPresentacion
  val butaca: Butaca = unaButaca
  var cliente: Cliente = null
  
  
  def calcularPrecioSinDescuento = (butaca.precioBase + presentacion.valorExtraPorNoche) + agregarRecargos
  
  def agregarRecargos = {
    if(festival.esVIP(this.getButaca))
    	(butaca.precioBase + presentacion.valorExtraPorNoche) * festival.recargoButacaVIP
    else 0.0
  }
  
  def precioBase = butaca.precioBase
  def precioPresentacion = presentacion.valorExtraPorNoche
  
  def calcularPrecioConDescuento(entradasEscogidas : List[Entrada]) = {
    var descuentoMaximo : Double = 0    
    val tiposCliente : List[TipoDeCliente] = cliente.getTiposDeCliente
    val descuentosHabilitados = festival.getDescuentosHabiltiados
    val descuentosAplicables : List[TipoDeCliente] = descuentosHabilitados.intersect(tiposCliente)    
    
    if(descuentosAplicables.length > 0){
      descuentoMaximo = descuentosAplicables.map(unTipoCliente => unTipoCliente.getDescuentoEntrada(this, entradasEscogidas)).max
    }
    
    calcularPrecioSinDescuento - descuentoMaximo
  }
  
  def getFestival = festival
  def getPresentacion = presentacion
  def getCliente = cliente  
  def getButaca = butaca
  def setCliente(unCliente : Cliente){ cliente = unCliente }
}