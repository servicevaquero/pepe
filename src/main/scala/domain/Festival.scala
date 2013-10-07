package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Festival(nombre : String, unasButacas : List[Butaca], unasButacasReservadas : List[Butaca], unasButacasVIP : List[Butaca], unRecargoVIP : Double, unasPresentaciones : List[Presentacion], unosDescuentosHabilitados : List[TipoDeCliente]) extends Entity {
  var butacas : List[Butaca] = unasButacas
  var butacasReservadas : List[Butaca] = unasButacasReservadas
  var butacasVIP : List[Butaca] = unasButacasVIP
  var presentaciones : List[Presentacion] = unasPresentaciones
  var descuentosHabilitados : List[TipoDeCliente] = unosDescuentosHabilitados 
  var recargoVIP : Double = unRecargoVIP
  val codigoReserva = "A la grande le puse Cuca"
  var nombreDelFestival : String = nombre
  
  def getNombre : String = nombreDelFestival
  
  def crearEntradasParaCadaPresentacion{
    presentaciones.foreach(unaPresentacion => unaPresentacion.creaTusEntradas(butacas, this))
  }
  
  def habilitarButaca(unaButaca : Butaca, codigoHabilitador : String){    
		validarCodigoHabilitador(codigoHabilitador)
  }
  
  def dameUnaEntradaConEstaButacaYEstaPresentacion(unaButaca : Butaca, unaPresentacion : Presentacion, unCodigo : String) : List[Entrada] = {
      validarPresentacion(unaPresentacion)
      devolverEntradaOEntradas(unaButaca, unaPresentacion, unCodigo)
  }  
  
  def devolverEntradaOEntradas(unaButaca : Butaca, unaPresentacion : Presentacion, unCodigo : String) : List[Entrada] = {
    
    var entradas : List[Entrada] = List()
    
    if(esVIP(unaButaca)){ //VIP
    	entradas = obtenerEntradasDeTodasLasPresentacionesParaEstaButaca(unaButaca)
    }    
    else if(butacasReservadas.contains(unaButaca)){ //FALTA ANALIZAR ESTO DE HABILITAR RESERVADAS
    		habilitarButaca(unaButaca, unCodigo)
    		entradas = unaPresentacion.dameUnaEntradaConButaca(unaButaca)
    	}
    	else entradas = unaPresentacion.dameUnaEntradaConButaca(unaButaca) //NORMALES
    
    validarDisponibilidad(entradas)
    return entradas
  }  

  //sirve para VIP
  def obtenerEntradasDeTodasLasPresentacionesParaEstaButaca(unaButaca : Butaca) : List[Entrada] = {
    var entradas : List[Entrada] = List()    
    presentaciones.foreach(unaPresentacion => entradas = entradas ++ unaPresentacion.dameUnaEntradaConButaca(unaButaca))
    entradas
  }
  
  def validarDisponibilidad(unasEntradas : List[Entrada]){
    if(unasEntradas.filter(unaEntrada => unaEntrada.getCliente != null).length > 0)
      throw new ExcepcionButacaOcupada("Ya esta ocupada")
  }
  
  def validarPresentacion(unaPresentacion : Presentacion){
    if (!presentaciones.contains(unaPresentacion))
      throw new ExcepcionPresentacionInvalida("Esa presentacion no es de este festival")
  }
  
  def validarCodigoHabilitador(unCodigo : String){
    if(unCodigo != codigoReserva)
      throw new ExcepcionCodigoInvalido("Codigo Invalido")
  }
  
  def getDescuentosHabiltiados = descuentosHabilitados
  def recargoButacaVIP = recargoVIP
  def esVIP(unaButaca : Butaca) = butacasVIP.contains(unaButaca)
}

	