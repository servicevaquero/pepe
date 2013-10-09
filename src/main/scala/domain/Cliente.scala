package domain;
import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable

@Observable
class Cliente(unDni: Int, tiposDeCliente : List[TipoDeCliente], unNombre:String) extends Entity {
  var dni : Int = unDni
  var nombre : String = unNombre
  def sosDama : Boolean = tiposDeCliente.contains(Dama)
  
  def sosMenorDeDoce : Boolean = tiposDeCliente.contains(MenorDeDoce)
  
  def getTiposDeCliente : List[TipoDeCliente] = tiposDeCliente  
}



object Cliente {
  
  def apply(dni: Int, edad: Int, sexo: Char, nombre: String) =  new Cliente(dni, tiposDeCliente(edad, sexo),nombre)
  
  def tiposDeCliente(edad: Int, sexo: Char): List[TipoDeCliente] = {
    
    var listaDeTiposDeCliente: List[TipoDeCliente] = List()

    if (edad < 18)
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(Menor)
    
    if (edad < 12)
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(MenorDeDoce)
    
    if (edad >= 65)
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(Jubilado)
    else if(edad >= 60 && sexo == 'F')
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(Jubilado)
      
    if(edad >= 18 && edad < 65)
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(Adulto)
    
    if(sexo == 'F')
      listaDeTiposDeCliente = listaDeTiposDeCliente ++ List(Dama)
      
    return listaDeTiposDeCliente
    
  }

}