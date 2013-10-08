package controller

import domain.Entrada
import com.uqbar.commons.collections.Transformer

class EntradaCategoriaTransformer extends Transformer[Entrada, String] {
	override def transform(unaEntrada: Entrada) : String = {
	  unaEntrada.getButaca.sector.getTipo.toString
	}
}

class EntradaNumeroDeFilaTransformer extends Transformer[Entrada, String] {
	override def transform(unaEntrada: Entrada) : String = {
	  unaEntrada.getButaca.nroFila.toString
	}
}

class EntradaNumeroDeButacaTransformer extends Transformer[Entrada, String] {
	override def transform(unaEntrada: Entrada) : String = {
	  unaEntrada.getButaca.nroDeButaca.toString
	}
}