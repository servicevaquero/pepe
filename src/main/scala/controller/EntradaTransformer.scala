package controller

import domain.Entrada
import com.uqbar.commons.collections.Transformer

class EntradaSectorTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.getButaca.sector.getTipo.toString
  }
}

class EntradaNumeroDeFilaTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.getButaca.nroFila.toString
  }
}

class EntradaNumeroDeButacaTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.getButaca.nroDeButaca.toString
  }
}

class EntradaNombreClienteTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.getCliente.nombre
  }
}

class EntradaDNIClienteTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.getCliente.dni.toString
  }
}

class EntradaFechaPresentacionTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.presentacion.fecha
  }
}

class EntradaPresentacionTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.presentacion.nombre
  }
}

class EntradaFestivalTransformer extends Transformer[Entrada, String] {
  override def transform(unaEntrada: Entrada): String = {
    unaEntrada.festival.nombreDelFestival
  }
}

