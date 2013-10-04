package domain;

class TipoDeCliente {
  var descuentoParaEntrada : (Entrada, List[Entrada]) => Double = null  
  def getDescuentoEntrada = descuentoParaEntrada
}

object Adulto extends TipoDeCliente{
  descuentoParaEntrada = (unaEntrada : Entrada, listaDeEntradas : List[Entrada]) => { 0.0 }  
}

object Jubilado extends TipoDeCliente{
  descuentoParaEntrada = (unaEntrada : Entrada, listaDeEntradas : List[Entrada]) => { unaEntrada.precioBase * 0.15 }
}

object Menor extends TipoDeCliente{
  descuentoParaEntrada = (unaEntrada : Entrada, listaDeEntradas : List[Entrada]) => {
    val valorEntrada = unaEntrada.precioBase
    if (valorEntrada > 100)
      0.2 * valorEntrada
    else if (valorEntrada > 50)
    		10
    	else
    		0
  }
}

object MenorDeDoce extends TipoDeCliente{
  descuentoParaEntrada = (unaEntrada : Entrada, listaDeEntradas : List[Entrada]) => {    
    def estaAcompaniado(unaPresentacion : Presentacion, unasEntradas : List[Entrada]) = {
    	unasEntradas.filter(otraEntrada => otraEntrada.getPresentacion == unaPresentacion).length > 0
    }    
    val presentacion = unaEntrada.getPresentacion
    val listaDeOtrasEntradas = listaDeEntradas.diff(List(unaEntrada))
    if(estaAcompaniado(presentacion, listaDeOtrasEntradas))
         unaEntrada.precioBase * 0.5
    else 0
  }
}

object Dama extends TipoDeCliente{
  descuentoParaEntrada = (unaEntrada : Entrada, listaDeEntradas : List[Entrada]) => {
    val limiteParaElDescuento = 0.2
    val presentacion = unaEntrada.getPresentacion
    def aplicaDescuento(unaPresentacion : Presentacion) : Boolean = {
    	val entradas = unaPresentacion.getEntradas
    	val entradasCompradasPorMujeres: Double = entradas.filter(entrada => entrada.getCliente != null && entrada.getCliente.sosDama).length    
    	val entradasTotales: Double = entradas.length
    	(entradasCompradasPorMujeres/entradasTotales) < limiteParaElDescuento
    }    

    if(aplicaDescuento(presentacion))
    	unaEntrada.precioBase * 0.2
    else 0.0
  }
}