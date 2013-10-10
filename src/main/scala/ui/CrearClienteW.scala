package ui
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.commons.utils.ApplicationContext
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import collection.JavaConversions._
import domain.Cliente
import applicationModel.ABMClientes
import home.HomeClientes

class CrearClienteW(owner: WindowOwner, unCliente: Cliente) extends EditarClienteW(owner, unCliente) {

  override def executeTask() = {
    //home.HomeClientes.create(getModelObject.dni, getModelObject.edad, getModelObject.sexo, getModelObject.nombre)
    super.executeTask()
  }

  override def aceptar() {
    getModelObject.crear()
    this.accept()
  }

}